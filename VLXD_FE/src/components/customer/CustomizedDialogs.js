import * as React from 'react';
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import { Autocomplete, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
import FormikAutocomplete from './FormikAutocomplete';
import toastifyAlert from '../component/toastify-message/toastify';
import { ToastContainer } from 'react-toastify';
import { useRouter } from 'next/router';
import login401 from 'src/hook/login401';
import useCallVillage from 'src/hook/useCallVillage';
import useCallCustomerSave from 'src/hook/useCallCustomerSave';

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
  '& .MuiDialogContent-root': {
    padding: theme.spacing(2),
  },
  '& .MuiDialogActions-root': {
    padding: theme.spacing(1),
  },
}));


const BootstrapDialogTitle = (props) => {
  const { children, open, onClose, ...other } = props;
  return (
    <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
      {children}
      {onClose ? (
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: 'absolute',
            right: 8,
            top: 8,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </DialogTitle>
  );
};

BootstrapDialogTitle.propTypes = {
  children: PropTypes.node,
  onClose: PropTypes.func.isRequired,
};

export default function CustomizedDialogs(props) {
  const router = useRouter();
  const { open, setOpen, setCheck, dataEdit, setDataEdit } = props;
  const data = useCallVillage();
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit({})
    setCheck(false)
  };



  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      id: dataEdit ? dataEdit.id : '',
      name: dataEdit ? dataEdit.name : '',
      phone: dataEdit ? dataEdit.phone : '',
      villageId: dataEdit && dataEdit.village ? dataEdit.village : '',
      address: dataEdit ? dataEdit.address : '',
    },
    validationSchema: Yup.object({
      name: Yup
        .string()
        .max(255)
        .required(
          'Bạn chưa nhập họ tên !'),
      phone: Yup
        .number(),
      // .required(
      //   'Bạn chưa nhập số điện thoại'),
      villageId: Yup
        .object()
        .required(
          'Bạn chưa chọn thôn !'),
      address: Yup
        .string()
        .max(255)
    }),
    onSubmit: (values, { resetForm }) => {
      const newData = {
        ...values,
        villageId: values.villageId.id
      }
      axiosInstance.post(SAVE_UPDATE_CUSTOMER, newData)
        .then(response => {
          setCheck(true)
          toastifyAlert.success(TB_SAVE_UPDATE_CUSTOMER)
        })
        .catch(err => {
          console.log("ee", err);
          toastifyAlert.error(TB_SAVE_UPDATE_CUSTOMER_ERR)
          setCheck(false)
        })
      handleClose();
      resetForm();

    }
  });
  return (
    <div>
      <BootstrapDialog
        onClose={handleClose}
        aria-labelledby="customized-dialog-title"
        open={open}
        maxWidth='md'
        fullWidth={true}
      >
        <form onSubmit={formik.handleSubmit}>
          <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose}>
            Thêm khách hàng
          </BootstrapDialogTitle>
          <DialogContent dividers>

            <TextField
              error={Boolean(formik.touched.name && formik.errors.name)}
              fullWidth
              helperText={formik.touched.name && formik.errors.name}
              label="Họ tên"
              margin="normal"
              name="name"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              value={formik.values.name}
              variant="outlined"
            />
            <TextField
              error={Boolean(formik.touched.phone && formik.errors.phone)}
              fullWidth
              helperText={formik.touched.phone && formik.errors.phone}
              label="Số điện thoại"
              margin="normal"
              name="phone"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              value={formik.values.phone}
              variant="outlined"
            />
            <Autocomplete
              id="villageId"
              name="villageId"
              options={data}
              // groupBy={ option => option.state }
              getOptionLabel={option => option.villageName}
              onChange={(event, value) => formik.setFieldValue("villageId", value)}
              // onSelect={(event, value) => console.log(event.target.value)}
              value={formik.values && formik.values.villageId ? formik.values.villageId : undefined}
              style={{ width: 300 }}
              renderInput={params => (
                <TextField
                  {...params}
                  onChange={formik.handleChange}
                  margin="normal"
                  label="Thôn"
                  fullWidth
                  error={Boolean(formik.touched.villageId && formik.errors.villageId)}
                  helperText={formik.touched.villageId && formik.errors.villageId}
                />
              )}
            />
            <TextField
              error={Boolean(formik.touched.address && formik.errors.address)}
              fullWidth
              helperText={formik.touched.address && formik.errors.address}
              label="Địa chỉ chi tiết"
              margin="normal"
              name="address"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              value={formik.values.address}
              variant="outlined"
            />




          </DialogContent>
          <DialogActions  >
            <Button type="reset" onClick={() => handleClose()} style={{ fontSize: 20, marginRight: 10, fontFamily: "Times New Roman", color: "black" }} color="error" size="large" variant="contained" autoFocus  >
              Hủy
            </Button>
            <Button type="submit" style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }} color="secondary" size="large" variant="contained" autoFocus  >
              Lưu lại
            </Button>
          </DialogActions>
        </form>
      </BootstrapDialog>
    </div>
  );
}
