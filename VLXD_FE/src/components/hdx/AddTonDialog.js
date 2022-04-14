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
import { Autocomplete, Grid, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, NOTIFY, phoneRegExp, PRODUCT, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
import toastifyAlert from '../component/toastify-message/toastify';
import { ToastContainer } from 'react-toastify';
import { useRouter } from 'next/router';
import login401 from 'src/hook/login401';
import CurrencyFormat from 'react-currency-format';
import AddProduct from 'src/components/product/CustomizedDialogs';


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

export default function AddTonDialog(props) {
  const router = useRouter();
  const [openAddSp, setOpenAddSp] = React.useState(false)
  const { open, setOpen, dataEdit,
    setDataEdit, dataProductType, dataProduct
    , handAddTon, dataUnit, getProduct, setDataProduct } = props;
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit({})
    // setOpenCus(false)
  };


  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      id: dataEdit ? dataEdit.id : '',
      productType: dataEdit && dataEdit.productType ? dataEdit.productType : (dataProductType && dataProductType.data ? dataProductType.data[2]:""),
      product: dataEdit ? dataEdit.product : '',
      price: dataEdit ? dataEdit.price : '',
      quantity: dataEdit ? dataEdit.quantity : '',
      width: dataEdit ? dataEdit.width : '',
      height: dataEdit && dataEdit.height ? dataEdit.height :'1.080',
    },
    validationSchema: Yup.object({
      product: Yup
        .object().nullable()
        .required(NOTIFY.PRODUCT),
      quantity: Yup
        .number().nullable()
        .typeError(NOTIFY.NUMBER)
        // .trim()
        .required(NOTIFY.NOT_BLANK),
      width: Yup
        .number().nullable()
        .typeError(NOTIFY.NUMBER)
        // .trim()
        .required(NOTIFY.NOT_BLANK),
      height: Yup
        .number().nullable()
        .typeError(NOTIFY.NUMBER)
        // .trim()
        .required(NOTIFY.NOT_BLANK),
      price: Yup
        .number().nullable()
        .typeError(NOTIFY.NUMBER)
        // .trim()
        .required(NOTIFY.NOT_BLANK),
    }),
    onSubmit: (values, { resetForm }) => {
      handAddTon(values)
      handleClose();
      resetForm();

    }
  });
  React.useEffect(() => {
    if (formik.values.product == null) {
      formik.setFieldValue("price", '')
    } else {
      formik.setFieldValue("price", formik.values.product.price)
    }
    if( formik.values.product && formik.values.product.unit){
      formik.values.product.unit.unitName == "Md"? formik.setFieldValue("height",'1') :  formik.setFieldValue("height",'1.080') 
      formik.setFieldValue("price", formik.values.product.price)
    }
  }, [formik.values.product])

  React.useEffect(() => {
    if (formik.values.productType !== undefined) {
      const typeId = formik.values.productType ? formik.values.productType.id : null
      if (typeId !== null) {
        axiosInstance.get(PRODUCT.GET_PRODUCT_BY_TYPE_ID + '?typeId=' + typeId)
          .then(response => {
            setDataProduct(response)
          })
          .catch(err => {
            console.log(err);
            login401(err && err.response && err.response.status)
          })
      } else {
        axiosInstance.get(PRODUCT.GET_ALL)
          .then(response => {
            setDataProduct(response)
          })
          .catch(err => {
            console.log(err);
            login401(err && err.response && err.response.status)
          })
      }

    }
  }, [formik.values.productType,open])
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
            Thêm tôn vào giỏ
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <Grid container spacing={2}>
              <Grid item xs={4}>
                <Autocomplete
                  size="small"
                  id="productType"
                  name="productType"
                  options={dataProductType && dataProductType.data ? dataProductType.data : []}
                  getOptionLabel={option => option.typeName}
                  defaultValue={dataProductType && dataProductType.data ? dataProductType.data[2] : []}
                  onChange={(event, value) => formik.setFieldValue("productType", value)}
                  value={formik.values && formik.values.productType ? formik.values.productType : undefined}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Loại sản phẩm"
                      fullWidth
                    />
                  )}
                />
              </Grid>
              <Grid item xs={6}>
                <Autocomplete
                  size="small"
                  id="product"
                  name="product"
                  options={dataProduct && dataProduct.data ? dataProduct.data : []}
                  getOptionLabel={option => option.name}
                  onChange={(event, value) => formik.setFieldValue("product", value)}
                  value={formik.values && formik.values.product ? formik.values.product : undefined}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Sản phẩm"
                      fullWidth
                      error={Boolean(formik.touched.product && formik.errors.product)}
                      helperText={formik.touched.product && formik.errors.product}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={2}>
                {formik && formik.values && formik.values.product ?
                  <p style={{ marginTop: 20 }}>ĐVT : {formik.values.product.unit.unitName}</p> :
                  <Button type="button" onClick={() => setOpenAddSp(true)} style={{ marginTop: 17 }}
                    color="error" size="small" variant="contained" autoFocus  >
                    Thêm SP
                  </Button>
                }

              </Grid>
              <Grid item xs={4}>
                <TextField
                  id="outlined-size-small"
                  size="small"
                  fullWidth
                  label="Chiều rộng"
                  margin="normal"
                  name="height"
                  onBlur={formik.handleBlur}
                  onChange={formik.handleChange}
                  value={formik.values.height ? formik.values.height :'1.080'}
                  variant="outlined"
                  error={Boolean(formik.touched.height && formik.errors.height)}
                  helperText={formik.touched.height && formik.errors.height}
                />
              </Grid>
              <Grid item xs={4}>
                <TextField
                  id="outlined-size-small"
                  size="small"
                  fullWidth
                  label="Chiều dài"
                  margin="normal"
                  name="width"
                  onBlur={formik.handleBlur}
                  onChange={formik.handleChange}
                  value={formik.values.width}
                  variant="outlined"
                  error={Boolean(formik.touched.width && formik.errors.width)}
                  helperText={formik.touched.width && formik.errors.width}
                />
              </Grid>
              
              <Grid item xs={4}>
                <TextField
                  id="outlined-size-small"
                  size="small"
                  fullWidth
                  label="Số lượng"
                  margin="normal"
                  name="quantity"
                  onBlur={formik.handleBlur}
                  onChange={formik.handleChange}
                  value={formik.values.quantity}
                  variant="outlined"
                  error={Boolean(formik.touched.quantity && formik.errors.quantity)}
                  helperText={formik.touched.quantity && formik.errors.quantity}
                />
              </Grid>
              <Grid item xs={6}>
                Giá :  <CurrencyFormat
                  style={{
                    marginTop: 16, width: 230, height: 38,
                    borderRadius: 5, borderWidth: 1, variant: 'outlined',
                    paddingLeft: 10, fontSize: 15,
                  }}
                  value={formik.values.price ? formik.values.price : (formik.values.product ? formik.values.product.price : '')}
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



          </DialogContent>
          <DialogActions  >
            <div>
            <Button type="reset" onClick={() => handleClose()}
              style={{ fontSize: 20, marginRight: 10, fontFamily: "Times New Roman", color: "black" }}
              color="error" size="small" variant="contained" autoFocus  >
              Hủy
            </Button>
            <Button type="submit"
              style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }}
              color="secondary" size="small" variant="contained" autoFocus  >
              Lưu lại
            </Button>
            </div>
          </DialogActions>
        </form>
      </BootstrapDialog>
      <AddProduct
        // dataEdit={dataEdit}
        setDataEdit={() => console.log("sss")}
        open={openAddSp}
        setOpen={setOpenAddSp}
        handleSearch={getProduct}
        dataUnit={dataUnit}
        dataType={dataProductType}
      />
    </div>
  );
}
