import { useEffect, useState } from 'react';
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
import { Autocomplete, Checkbox, FormControlLabel, FormGroup, Grid, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { CHO_THANH_TOAN, currencyFormat, currencyFormat3, DA_THANH_TOAN, GETALL_AND_SEARCH_VILLAGE, HDX_API, LOGIN, LOGIN_FAILED, NOTIFY, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR, UNIT_API, VILLAGE_API } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
import toastifyAlert from '../component/toastify-message/toastify';
import { ToastContainer } from 'react-toastify';
import { useRouter } from 'next/router';
import login401 from 'src/hook/login401';
import { PTable } from './PTable';
import { Box } from '@mui/system';
import Customer from 'src/components/customer/CustomizedDialogs';
import AddProductDialog from './AddProductDialog';
import AlertDialog from '../component/AlertDialog';
import { TonTable } from './TonTable';
import AddTonDialog from './AddTonDialog';
import CurrencyFormat from 'react-currency-format';

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
  const [openCus, setOpenCus] = useState(false)
  const [openAddP, setOpenAddP] = useState(false)
  const [openAddTon, setOpenAddTon] = useState(false)
  const [dataPTable, setDataPTable] = useState([])
  const [dataPTableEdit, setDataPTableEdit] = useState([])
  const [dataTon, setDataTon] = useState([])
  const [dataTonEdit, setDataTonEdit] = useState([])
  const [openModal, setOpenModal] = useState(false)
  const [openModalTon, setOpenModalTon] = useState(false)
  const [pay, setPay] = useState({ owe: 0, pay: 0 })
  const { open, setOpen, dataEdit, setDataEdit, handleSearch,
    dataCustomer, getInitCustomer, dataProductType, dataProduct, dataUnit, getProduct,query,
    setDataProduct } = props;
    const formik = useFormik({
      enableReinitialize: true,
      initialValues: {
        id: dataEdit && dataEdit.id ? dataEdit.id : '',
        customer: dataEdit && dataEdit.customer ? dataEdit.customer : '',
        isPaySuccess: dataEdit && dataEdit.isPaySuccess ? dataEdit.isPaySuccess : false,
      },
      validationSchema: Yup.object({
        customer: Yup
          .object().nullable()
          .required(NOTIFY.NOT_BLANK),
      }),
      onSubmit: (values, { resetForm }) => {
        if (dataTon && dataTon.length <= 0 && dataPTable && dataPTable.length <= 0) {
          toastifyAlert.error(NOTIFY.PRODUCT)
        }else if(values.isPaySuccess === true && pay.pay <= 0){
          toastifyAlert.error(NOTIFY.PAY)
        }
         else {
          const newData = {
            ...values,
            customerId: values.customer.id,
            owe: pay.owe,
            pay: pay.pay,
            hdxCtTonRequest: dataTon,
            hdxCtRequest: dataPTable,
            status: values.isPaySuccess === true ? DA_THANH_TOAN : CHO_THANH_TOAN,
            releaseDate: new Date().getTime(),
            total: total(),
            totalBill: currencyFormat(totalBill())
  
          }
          axiosInstance.post(HDX_API.SAVE_UPDATE, newData)
            .then(response => {
              handleSearch(query);
              toastifyAlert.success(SAVE_SUCCESS)
            })
            .catch(err => {
              console.log("ee", err);
              toastifyAlert.error(SAVE_ERROR)
            })
          console.log("newData", newData);
          handleClose();
          resetForm();
        }
      }
    });
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit(null)
    setPay({ owe: 0, pay: 0 })
    setDataPTable([])
    setDataTon([])
  };
  useEffect(() => {
    if (dataEdit && dataEdit !== null) {
      setPay({ pay: dataEdit && dataEdit.pay ? dataEdit.pay : 0, owe: dataEdit && dataEdit.owe ? dataEdit.owe : 0 })
      formik.setFieldValue("pay",dataEdit && dataEdit.pay && dataEdit.pay || '')
      formik.setFieldValue("owe", dataEdit.owe  && dataEdit.owe && dataEdit.owe || '')
      setDataPTable(dataEdit.hdxCt)
      setDataTon(dataEdit.hdxCtTon)

    }
  }, [dataEdit])

  useEffect(() => {
    if (!open) {
      handleClose();
    }
  }, [open])

  useEffect(() => {
    getInitCustomer();
  }, [openCus])
  useEffect(() => {
    getProduct();
  }, [openAddP])



  
  const handAddProduct = (p) => {
    if (p.id) {
      const a = {
        id: p.id,
        name: p.product.name,
        price: p.price,
        quantity: p.quantity,
        unit: p.product.unit.unitName,
        product: p.product
      }
      const index = dataPTable && dataPTable.findIndex(e => e.id === p.id) || -1
      dataPTable && dataPTable.map((e, idx) => {
        if (index === idx) {
          dataPTable[idx] = a
        }
      })
    } else {
      const product = dataPTable && dataPTable.find(e => e.name === p.product.name) || -1
      if (product !== -1 && product !== undefined) {
        let i = dataPTable && dataPTable.indexOf(product)
        const a = {
          ...product,
          quantity: Number(product.quantity) + Number(p.quantity)
        }
        dataPTable[i] = a
      } else {
        const a = {
          id: new Date().getTime(),
          name: p.product.name,
          price: p.price,
          quantity: p.quantity,
          unit: p.product.unit.unitName,
          product: p.product
        }
        setDataPTable([...dataPTable, a]);
      }
    }
  }
  const handAddTon = (p) => {
    if (p.id) {
      const a = {
        id: p.id,
        name: p.product.name,
        price: p.price,
        quantity: p.quantity,
        unit: p.product.unit.unitName,
        product: p.product,
        width: p.width,
        height: p.height,
        numberM2: currencyFormat3((Number(p.quantity) * Number(p.height) * Number(p.width)))
      }
      const index = dataTon && p && dataTon.findIndex((c, i) => c.id === p.id)
      dataTon && dataTon.map((e, idx) => {
        if (index === idx) {
          dataTon[idx] = a
        }
      })
    } else {

      const a = {
        id: new Date().getTime(),
        name: p.product.name,
        price: p.price,
        quantity: p.quantity,
        unit: p.product.unit.unitName,
        product: p.product,
        width: p.width,
        height: p.height,
        numberM2: currencyFormat3((Number(p.quantity) * Number(p.height) * Number(p.width)))
      }
      setDataTon([...dataTon, a]);
    }
  }
  const handleEdit = (a) => {
    setOpenAddP(true);
    setDataPTableEdit(a)
  }
  const handleEditTon = (a) => {
    setOpenAddTon(true);
    setDataTonEdit(a)
  }
  const handleDeleteTon = (a) => {
    setDataTonEdit(a)
    setOpenModalTon(true)
  }
  const handleDelete = (a) => {

    setDataPTableEdit(a)
    setOpenModal(true)
  }
  const onDetele = () => {
    if (dataPTableEdit) {
      const index = dataPTable && dataPTable.findIndex(e => e.id === dataPTableEdit.id)
      dataPTable.splice(index, 1)
    }
    setDataPTableEdit({})
    setOpenModal(false)
  }
  const onDeteleTon = () => {
    if (dataTonEdit) {
      const index = dataTon && dataTon.findIndex(e => e.id === dataTonEdit.id)
      dataTon.splice(index, 1)
    }
    setDataTonEdit({})
    setOpenModalTon(false)
  }

  ////customer
  const onSave = (val) => {
    axiosInstance.post(SAVE_UPDATE_CUSTOMER, val)
      .then(response => {
        toastifyAlert.success(TB_SAVE_UPDATE_CUSTOMER)
        getInitCustomer();
      })
      .catch(err => {
        console.log("ee", err);
        toastifyAlert.error(TB_SAVE_UPDATE_CUSTOMER_ERR)
      })
  }
  
  const handleChangowe = (name, value) => {
    setPay({
      ...pay,
      [name]: value
    })
  }
  const totalBill = () => {
    let total = 0;
    dataPTable && dataPTable.map(e => {
      total += Number(e.price) * Number(e.quantity)
    })
    dataTon && dataTon.map(p => {
      total += p.price * (Number(p.width) * Number(p.height) * Number(p.quantity))
    })
    // total = total + Number(pay.owe)//Nợ cũ
    // total = total - Number(pay.pay)//số tiền thanh toán

    return total;
  }
  const total = () => {
    let total = 0;
    dataPTable && dataPTable.map(e => {
      total += Number(e.price) * Number(e.quantity)
    })
    dataTon && dataTon.map(p => {
      total += p.price * (Number(p.width) * Number(p.height) * Number(p.quantity))
    })
    total = total + Number(pay.owe)//Nợ cũ
    total = total - Number(pay.pay)//số tiền thanh toán

    return total;
  }
  // console.log("formik",formik);
  return (
    <div>
      <BootstrapDialog
        onClose={handleClose}
        aria-labelledby="customized-dialog-title"
        open={open}
        maxWidth='large'
        fullWidth={true}
      >
        <form onSubmit={formik.handleSubmit}>
          <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose}>
            Thêm mới hóa đơn
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <Grid container rowSpacing={1} style={{ marginTop: -20, marginBottom: 10 }} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
              <Grid item xs={3}>
                <Autocomplete
                  size="small"
                  id="customer"
                  name="customer"
                  options={dataCustomer ? dataCustomer.data : []}
                  // groupBy={ option => option.state }
                  getOptionLabel={option => option.name}
                  onChange={(event, value) => formik.setFieldValue("customer", value)}
                  value={formik.values && formik.values.customer ? formik.values.customer : undefined}
                  // style={{ width: 300 }}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Khách hàng"
                      fullWidth
                      error={Boolean(formik.touched.customer && formik.errors.customer)}
                      helperText={formik.touched.customer && formik.errors.customer}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={3}>
                {formik && formik.values && formik.values.customer ?
                  <TextField
                    id="outlined-size-small"
                    size="small"
                    fullWidth
                    label="Thông tin"
                    margin="normal"
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
                    value={'SĐT: ' + formik.values.customer.phone + '- Địa chỉ: ' + formik.values.customer.village.villageName}
                    variant="outlined"
                    disabled

                  />
                  :
                  <Button type="button" onClick={() => setOpenCus(true)} style={{ marginTop: 17 }}
                    color="error" size="small" variant="contained" autoFocus  >
                    Thêm khách hàng
                  </Button>}
              </Grid>
              <Grid item xs={3}>
                <p></p>
              </Grid>
              <Grid item xs={3}>
                <Box
                  sx={{
                    display: 'flex',
                    justifyContent: 'flex-end',
                    p: 1,
                    m: 1,
                    bgcolor: 'background.paper',
                    borderRadius: 1,
                  }}
                >
                  <Button style={{ marginRight: 6 }} type="button" onClick={() => setOpenAddP(true)}
                    color="success" size="small" variant="contained" autoFocus  >
                    Thêm sản phẩm
                  </Button>

                </Box>
              </Grid>
            </Grid>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <PTable
                  handleDelete={handleDelete}
                  listData={dataPTable ? dataPTable : []}
                  handleEdit={handleEdit}
                />
              </Grid>

            </Grid>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <Box
                  sx={{
                    display: 'flex',
                    justifyContent: 'flex-end',
                    p: 1,
                    m: 1,
                    bgcolor: 'background.paper',
                    borderRadius: 1,
                  }}
                >
                  <Button style={{ marginRight: 6 }} type="button" onClick={() => setOpenAddTon(true)}
                    color="success" size="small" variant="contained" autoFocus  >
                    Thêm sản phẩm tôn
                  </Button>

                </Box>
              </Grid>
            </Grid>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TonTable
                  handleDelete={handleDeleteTon}
                  listData={dataTon ? dataTon : []}
                  handleEdit={handleEditTon}
                />
              </Grid>

            </Grid>
            <Grid container spacing={2}>
            <Grid item xs={4}></Grid>
              <Grid item xs={2}>

              </Grid>
              <Grid item xs={3} >
                Nợ cũ :  <CurrencyFormat
                  style={{
                    marginTop: 16, width: 300, height: 38,
                    borderRadius: 5, borderWidth: 1, variant: 'outlined',
                    paddingLeft: 10, fontSize: 15
                  }}
                  value={formik.values.owe}
                  thousandSeparator={true}
                  //  suffix={' VND'}
                  onValueChange={(values) => {
                    const { formattedValue, value } = values;
                    // formattedValue = $2,223
                    // value ie, 2223
                    handleChangowe("owe", value);
                    formik.setFieldValue("owe", value)
                  }}
                /> VND
                {Boolean(formik.touched.owe && formik.errors.owe) ? <p style={{ color: '#D14343', fontSize: 12, marginLeft: 40, marginTop: 5 }} >{formik.errors.owe}</p> : ''}
              </Grid>
              <Grid item xs={3}>
                Thanh toán :  <CurrencyFormat
                  style={{
                    marginTop: 16, width: 250, height: 38,
                    borderRadius: 5, borderWidth: 1, variant: 'outlined',
                    paddingLeft: 10, fontSize: 15,
                  }}
                  value={formik.values.pay}
                  thousandSeparator={true}
                  //  suffix={' VND'}
                  onValueChange={(values) => {
                    const { formattedValue, value } = values;
                    // formattedValue = $2,223
                    // value ie, 2223
                    handleChangowe("pay", value);
                    formik.setFieldValue("pay", value)
                  }}
                /> VND
                {Boolean(formik.touched.pay && formik.errors.pay) ? <p style={{ color: '#D14343', fontSize: 12, marginLeft: 40, marginTop: 5 }} >{formik.errors.pay}</p> : ''}
              </Grid>
         
            </Grid>


          </DialogContent>

          {/* <DialogActions  > */}
          <Grid container spacing={2}>
            {/* <Grid item xs={1}>

            </Grid> */}
            <Grid item xs={7}>
              <Box
                sx={{
                  display: 'flex',
                  justifyContent: 'end',
                  pt: 1,
                  pb: 1
                }}
              >
                <Button type="reset" onClick={() => handleClose()}
                  style={{ fontSize: 20, marginRight: 10, fontFamily: "Times New Roman", color: "black" }} color="error" size="small" variant="contained" autoFocus  >
                  Hủy
                </Button>
                <Button type="submit"
                  style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }}
                  color="secondary" size="small" variant="contained" autoFocus  >
                  Lưu lại
                </Button>
              </Box>
            </Grid>
            <Grid item xs={3}>
              <p style={{ fontSize: 20, fontWeight: "bold", marginTop: 10 }}>
                Tổng tiền : {currencyFormat(total())} VND
              </p>

            </Grid>
            <Grid item xs={2}>
              <FormGroup>
                <FormControlLabel control={<Checkbox
                  checked={formik.values.isPaySuccess} onChange={formik.handleChange} name="isPaySuccess"
                />} label="Đã thanh toán xong" />
              </FormGroup>
            </Grid>

          </Grid>



          {/* </DialogActions> */}
        </form>
      </BootstrapDialog>
      <Customer
        open={openCus}
        setOpen={setOpenCus}
        setDataEdit={() => console.log("sss")}
        onSave={onSave}
      />
      <AddProductDialog
        open={openAddP}
        setOpen={setOpenAddP}
        dataProductType={dataProductType}
        setDataEdit={setDataPTableEdit}
        dataProduct={dataProduct}
        handAddProduct={handAddProduct}
        setOpenCus={setOpenCus}
        dataEdit={dataPTableEdit}
        dataUnit={dataUnit}
        getProduct={getProduct}
        setDataProduct={setDataProduct}
      />
      <AddTonDialog
        open={openAddTon}
        setOpen={setOpenAddTon}
        dataProductType={dataProductType}
        setDataEdit={setDataTonEdit}
        dataProduct={dataProduct}
        handAddTon={handAddTon}
        // setOpenCus={setOpenCus}
        dataEdit={dataTonEdit}
        dataUnit={dataUnit}
        getProduct={getProduct}
        setDataProduct={setDataProduct}
      />

      <AlertDialog
        open={openModal}
        setOpen={setOpenModal}
        onDelete={onDetele}
      />
      <AlertDialog
        open={openModalTon}
        setOpen={setOpenModalTon}
        onDelete={onDeteleTon}
      />
    </div>
  );
}
