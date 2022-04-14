import CloseIcon from '@mui/icons-material/Close';
import { Autocomplete, Grid, TextField } from '@mui/material';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import IconButton from '@mui/material/IconButton';
import { styled } from '@mui/material/styles';
import { useFormik } from 'formik';
import { useRouter } from 'next/router';
import PropTypes from 'prop-types';
import * as React from 'react';
import CurrencyFormat from 'react-currency-format';
import * as Yup from 'yup';
import { NOTIFY, PRODUCT, SAVE_ERROR, SAVE_SUCCESS } from '../component/MessageContants';
import toastifyAlert from '../component/toastify-message/toastify';
import axiosInstance from '../config/axiosConfig';

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
  const { open, setOpen, dataEdit, setDataEdit, dataUnit, dataType ,handleSearch} = props;
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit({})
  };
  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      id: dataEdit ? dataEdit.id : '',
      name: dataEdit ? dataEdit.name : '',
      price: dataEdit ? dataEdit.price : '',
      image: dataEdit ? dataEdit.image : '',
      unitId: dataEdit ? dataEdit.unit : '',
      typeId: dataEdit ? dataEdit.productType : '',
    },
    validationSchema: Yup.object({
      name: Yup
        .string()
        .trim()
        .required(NOTIFY.NOT_BLANK),
      price: Yup
        .string()
        .trim()
        .required(NOTIFY.NOT_BLANK),
      typeId: Yup
        .object().nullable()
        .required(NOTIFY.P_TYPE),
      unitId: Yup
        .object().nullable()
        .required(NOTIFY.UNIT),
    }),
    onSubmit: (values, { resetForm }) => {
      const newData ={
        ...values,
        typeId:values.typeId.id,
        unitId:values.unitId.id,
      }
      axiosInstance.post(PRODUCT.SAVE_UPDATE, newData)
        .then(response => {
          toastifyAlert.success(SAVE_SUCCESS)
          handleSearch();
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
            Thêm sản phẩm
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <Autocomplete
                  id="typeId"
                  name="typeId"
                  options={dataType &&dataType.data ? dataType.data : []}
                  // groupBy={ option => option.state }
                  getOptionLabel={option => option.typeName}
                  onChange={(event, value) => formik.setFieldValue("typeId", value)}
                  // onSelect={(event, value) => console.log(event.target.value)}
                  value={formik.values && formik.values.typeId ? formik.values.typeId : undefined}
                  // style={{ width: 300 }}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Loại sản phẩm"
                      fullWidth
                      error={Boolean(formik.touched.typeId && formik.errors.typeId)}
                      helperText={formik.touched.typeId && formik.errors.typeId}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={6}>
                <Autocomplete
                  id="unitId"
                  name="unitId"
                  options={dataUnit && dataUnit.data ? dataUnit.data : []}
                  // groupBy={ option => option.state }
                  getOptionLabel={option => option.unitName}
                  onChange={(event, value) => formik.setFieldValue("unitId", value)}
                  // onSelect={(event, value) => console.log(event.target.value)}
                  value={formik.values && formik.values.unitId ? formik.values.unitId : undefined}
                  // style={{ width: 300 }}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Đơn vị tính"
                      fullWidth
                      error={Boolean(formik.touched.unitId && formik.errors.unitId)}
                      helperText={formik.touched.unitId && formik.errors.unitId}
                    />
                  )}
                />
              </Grid>
            </Grid>
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <TextField
                  error={Boolean(formik.touched.name && formik.errors.name)}
                  fullWidth
                  helperText={formik.touched.name && formik.errors.name}
                  label="Tên sản phẩm"
                  margin="normal"
                  name="name"
                  onBlur={formik.handleBlur}
                  onChange={formik.handleChange}
                  value={formik.values.name}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={6}>
                Giá :  <CurrencyFormat
                  style={{
                    marginTop: 18, width: 300, height: 50,
                    borderRadius: 5, borderWidth: 1, variant: 'outlined',
                    paddingLeft: 10, fontSize: 15,
                  }}
                  value={formik.values.price}
                  thousandSeparator={true}
                  //  suffix={' VND'}
                  onValueChange={(values) => {
                    const { formattedValue, value } = values;
                    // formattedValue = $2,223
                    // value ie, 2223
                    formik.setFieldValue("price", value)
                  }}
                /> VND
                {Boolean(formik.touched.price && formik.errors.price) ? <p style={{ color: '#D14343', fontSize: 12, marginLeft: 40, marginTop: 5 }} >{formik.errors.price}</p> : ''}
              </Grid>
            </Grid>
            <TextField
              error={Boolean(formik.touched.image && formik.errors.image)}
              fullWidth
              helperText={formik.touched.image && formik.errors.image}
              label="Link hình ảnh"
              margin="normal"
              name="image"
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              value={formik.values.image}
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
