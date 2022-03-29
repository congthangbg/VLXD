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
import { GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, NOTIFY, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR, VILLAGE_API } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
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
  const { open, setOpen, dataEdit, setDataEdit,handleSearch } = props;
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit({})
  };



  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      id: dataEdit ? dataEdit.id : '',
      villageName: dataEdit ? dataEdit.villageName : '',
    },
    validationSchema: Yup.object({
      villageName: Yup
        .string()
        .max(255)
        .trim()
        .required(NOTIFY.NOT_BLANK),
    }),
    onSubmit: (values, { resetForm }) => {
      axiosInstance.post(VILLAGE_API.SAVE_UPDATE, values)
        .then(response => {
          handleSearch();
          toastifyAlert.success(SAVE_SUCCESS)
        })
        .catch(err => {
          console.log("ee", err);
          toastifyAlert.error(SAVE_ERROR)
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
            Thêm mới thôn
          </BootstrapDialogTitle>
          <DialogContent dividers>

            <TextField
              error={Boolean(formik.touched.villageName && formik.errors.villageName)}
              fullWidth
              helperText={formik.touched.villageName && formik.errors.villageName}
              label="Tên thôn"
              margin="normal"
              name="villageName"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              value={formik.values.villageName}
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
