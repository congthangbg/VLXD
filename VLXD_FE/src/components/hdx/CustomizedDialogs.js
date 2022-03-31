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
import { Autocomplete, Grid, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, NOTIFY, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR, UNIT_API, VILLAGE_API } from '../component/MessageContants';
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
  const [openCus,setOpenCus] = useState(false)
  const [openAddP,setOpenAddP] = useState(false)
  const [dataPTable,setDataPTable] = useState([])
  const [dataPTableEdit,setDataPTableEdit] = useState([])
  const [openModal, setOpenModal] = useState(false)
  const { open, setOpen, dataEdit, setDataEdit, handleSearch,
     dataCustomer,getInitCustomer,dataProductType,dataProduct,dataUnit,getProduct } = props;
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit({})
  };

  
useEffect(()=>{
 getInitCustomer();
},[openCus])


  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      id: dataEdit ? dataEdit.id : '',
      unitName: dataEdit ? dataEdit.unitName : '',
    },
    validationSchema: Yup.object({
      unitName: Yup
        .string()
        .trim()
        .max(255)
        .required(NOTIFY.NOT_BLANK),
    }),
    onSubmit: (values, { resetForm }) => {
      axiosInstance.post(UNIT_API.SAVE_UPDATE, values)
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
  const handAddProduct = (p) => {
    if(p.id){
      const a ={
        id:p.id,
        name:p.product.name,
        price:p.price,
        quantity:p.quantity,
        unit:p.product.unit.unitName,
        product :p.product
      }
     const index = dataPTable && dataPTable.findIndex(e=>e.id ===p.id)
      dataPTable && dataPTable.map((e,idx)=>{
        if(index===idx){
          dataPTable[idx] = a
        }
      })
    }else{
      const a ={
        id:new Date().getTime(),
        name:p.product.name,
        price:p.price,
        quantity:p.quantity,
        unit:p.product.unit.unitName,
        product :p.product
      }
       setDataPTable([...dataPTable,a]);
    }
  
  }
  const handleEdit = (a) => {
    setOpenAddP(true);
    setDataPTableEdit(a)
  }
  const handleDelete = (a) => {
    setDataPTableEdit(a)
    setOpenModal(true)
  }
  const onDetele =()=>{
    
    if(dataPTableEdit){
     const index = dataPTable && dataPTable.findIndex(e=>e.id ===dataPTableEdit.id)
     dataPTable.splice(index, 1)
    }
    setDataPTableEdit({})
    setOpenModal(false)
  }
  
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
            <Grid container rowSpacing={1} style={{marginTop:-20,marginBottom:10}} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
              <Grid item xs={3}>
                <Autocomplete
                  size="small"
                  id="customerId"
                  name="customerId"
                  options={dataCustomer ? dataCustomer.data : []}
                  // groupBy={ option => option.state }
                  getOptionLabel={option => option.name}
                  onChange={(event, value) => formik.setFieldValue("customerId", value)}
                  value={formik.values && formik.values.customerId ? formik.values.customerId : undefined}
                  // style={{ width: 300 }}
                  renderInput={params => (
                    <TextField
                      {...params}
                      onChange={formik.handleChange}
                      margin="normal"
                      label="Khách hàng"
                      fullWidth
                      error={Boolean(formik.touched.customerId && formik.errors.customerId)}
                      helperText={formik.touched.customerId && formik.errors.customerId}
                    />
                  )}
                />
              </Grid>
              <Grid item xs={3}>
              {formik && formik.values && formik.values.customerId ?
                  <TextField
                    id="outlined-size-small"
                    size="small"
                    fullWidth
                    label="Thông tin"
                    margin="normal"
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
                    value={'SĐT: ' + formik.values.customerId.phone + '- Địa chỉ: ' + formik.values.customerId.village.villageName}
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

          </DialogContent>

          {/* <DialogActions  > */}
          <Box
            sx={{
              display: 'flex',
              justifyContent: 'center',
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

          {/* </DialogActions> */}
        </form>
      </BootstrapDialog>
      <Customer
      open={openCus}
      setOpen={setOpenCus}
      setDataEdit={()=>console.log("sss")}
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
    />
     <AlertDialog
      open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
    </div>
  );
}
