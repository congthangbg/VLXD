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
import { Autocomplete, Box, Card, Grid, Table, TableBody, TableCell, TableHead, TableRow, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { currencyFormat, currencyFormat3, GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, NOTIFY, PRODUCT, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
import toastifyAlert from '../component/toastify-message/toastify';
import { ToastContainer } from 'react-toastify';
import { useRouter } from 'next/router';
import login401 from 'src/hook/login401';
import CurrencyFormat from 'react-currency-format';
import AddProduct from 'src/components/product/CustomizedDialogs';
import { SeverityPill } from '../severity-pill';
import { format } from 'date-fns';


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

export default function ViewDetails(props) {
  const router = useRouter();
  const [openAddSp, setOpenAddSp] = React.useState(false)
  const { open, setOpen, dataEdit,
    setDataEdit, dataProductType, dataProduct
    , handAddProduct, setOpenCus, dataUnit, getProduct, setDataProduct, print } = props;
  const handleClose = () => {
    setOpen(false);
    setDataEdit({})
  };
  const onPrint = () => {
    if (dataEdit) {
      print(dataEdit)
    }
  }
  const [total,setTotal] = React.useState(0)
  const [totalTon,setTotalTon] = React.useState(0)
  React.useEffect(() => {
    const tong = 0;
    if(dataEdit && dataEdit.hdnCt) {
      dataEdit.hdnCt.map((e,i)=>{
        tong = tong + (e.quantity * e.price)
     })  
    }
    setTotal(tong)
  },[dataEdit])
  React.useEffect(() => {
    const tong = 0;
    if(dataEdit && dataEdit.hdnCtTon) {
      dataEdit.hdnCtTon.map((p,i)=>{
        tong = tong + (p.price * (Number(p.width) * Number(p.height) * Number(p.quantity)))
     })  
    }
    setTotalTon(tong)
  },[dataEdit])
  return (
    <div>
      <BootstrapDialog
        onClose={handleClose}
        aria-labelledby="customized-dialog-title"
        open={open}
        maxWidth='md'
        fullWidth={true}
      >
        <form>
          <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose}>
            Chi ti???t ????n h??ng
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <Grid container spacing={2}>
              <Grid item xs={5}>
                <p style={{ fontSize: 25, fontWeight: "bold", fontFamily: "Times New Roman", textAlign: 'center' }}>
                  HUY???N TO??N</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  ??C: Xu??n Minh - H????ng Mai - VY - BG</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 64 }}>
                  S??T : 0983868072</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 64 }}>
                  STK : 0983868072 MB BANK</p>
              </Grid>
              <Grid item xs={1}>

              </Grid>
              <Grid item xs={6}>
                <p style={{ fontSize: 25, fontWeight: "bold", fontFamily: "Times New Roman", textAlign: 'center' }}>
                  CHUY??N V???T LI???U X??Y D???NG</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  S???T TH??P TH??I NGUY??N, S???T H???P M??? K???M...</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  T???M L???P, T??N X???P, T??N L???NH, T??N TR???N, T??N V??CH...</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  C??C PH??? KI???N NG??NH S???T.</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center', fontSize: 30, fontWeight: "bold" }}>
                  H??A ????N NH???P H??NG</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center', fontSize: 17 }}>
                  Ng??y t???o : {dataEdit &&dataEdit.dateAdded&& format(new Date(dataEdit.dateAdded ), 'dd/MM/yyyy')|| ''}</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  Nh?? cung c???p : {dataEdit && dataEdit.supplier && dataEdit.supplier.name || ''} </p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  ?????a ch??? : {dataEdit && dataEdit.supplier && dataEdit.supplier.address || ''}</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  S??T : {dataEdit && dataEdit.supplier && dataEdit.supplier.phone || ''}</p>
              </Grid>
              <Grid item xs={12}>
                <Card >
                  {/* <Box sx={{ minWidth: 1050 }}> */}
                  {dataEdit && dataEdit.hdnCt && dataEdit.hdnCt.length > 0 ?
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell style={{width:10}}>
                            STT
                          </TableCell>
                          <TableCell>
                            T??n s???n ph???m
                          </TableCell>
                          <TableCell>
                            ????n v??? t??nh
                          </TableCell>
                          <TableCell>
                            S??? l?????ng
                          </TableCell>
                          <TableCell>
                            ????n gi??
                          </TableCell>
                          <TableCell>
                            Th??nh ti???n
                          </TableCell>
                          <TableCell>
                            Ghi ch??
                          </TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {dataEdit && dataEdit.hdnCt.map((p, i) => (
                          <TableRow
                            hover
                            key={p.id}
                          >
                            <TableCell >
                              {i + 1}
                            </TableCell>
                            <TableCell>
                              {p.name ? p.name : (p.product && p.product.name || '')}
                            </TableCell>
                            <TableCell>
                              {p.unit ? p.unit : (p.product && p.product.unit && p.product.unit.unitName || '')}
                            </TableCell>
                            <TableCell>
                              {p.quantity}
                            </TableCell>
                            <TableCell>
                              {currencyFormat(p.price)}
                            </TableCell>
                            <TableCell style={{ width: '165px' }}>
                              <SeverityPill color={"secondary"}>
                                {p.price && p.quantity ? currencyFormat(p.price * p.quantity) : ""}
                              </SeverityPill>
                            </TableCell>
                            <TableCell style={{ width: 50 }}>
                              {p.createDate && format(new Date(p.createDate), 'dd/MM/yyyy')}
                            </TableCell>
                          </TableRow>
                        ))}
                        <TableRow>
                        <TableCell colSpan={2}></TableCell>
                          <TableCell colSpan={3}><p style={{fontSize:17,fontWeight: "bold"}} >T???ng</p></TableCell>
                          <TableCell align="left">
                          <SeverityPill color={"error"}>
                          {currencyFormat(total)}
                          </SeverityPill>
                          </TableCell>
                        </TableRow>
                      </TableBody>
                    </Table> : ""
                  }
                  {/* </Box> */}

                </Card>
              </Grid>
              <Grid item xs={12}>
                <Card >
                  {dataEdit && dataEdit.hdnCtTon && dataEdit.hdnCtTon.length > 0 ?
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell style={{width:10}}>
                            STT
                          </TableCell>
                          <TableCell>
                            T??n s???n ph???m
                          </TableCell>
                          <TableCell>
                            CD
                          </TableCell>
                          <TableCell>
                            CR
                          </TableCell>
                          <TableCell>
                            S??? t???m
                          </TableCell>
                          <TableCell>
                            ??VT
                          </TableCell>
                          <TableCell>
                            m2
                          </TableCell>
                          <TableCell>
                            ????n gi??
                          </TableCell>
                          <TableCell>
                            Th??nh ti???n
                          </TableCell>
                          <TableCell>
                            Ghi ch??
                          </TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {dataEdit && dataEdit.hdnCtTon.map((p, i) => (
                          <TableRow
                            hover
                            key={p.id}
                          >
                            <TableCell >
                              {i + 1}
                            </TableCell>
                            <TableCell>
                              {p.name ? p.name : (p.product && p.product.name || '')}
                            </TableCell>
                            <TableCell>
                              {p.width}
                            </TableCell>
                            <TableCell>
                              {p.height}
                            </TableCell>
                            <TableCell>
                              {p.quantity}
                            </TableCell>
                            <TableCell>
                              {p.unit ? p.unit : (p.product && p.product.unit && p.product.unit.unitName || '')}
                            </TableCell>
                            <TableCell>
                              {currencyFormat3(Number(p.width) * Number(p.height) * Number(p.quantity))}
                            </TableCell>
                            <TableCell>
                              {p && p.price ? currencyFormat(p.price) : 0}
                            </TableCell>
                            <TableCell>
                              <SeverityPill color={"secondary"}>
                                {p.price && p.quantity && p.width && p.height ? currencyFormat(p.price * (Number(p.width) * Number(p.height) * Number(p.quantity))) : ""}
                              </SeverityPill>
                            </TableCell>
                            <TableCell style={{ width: 50 }}>
                              {p.createDate && format(new Date(p.createDate), 'dd/MM/yyyy')}
                            </TableCell>
                          </TableRow>
                        ))}
                        <TableRow>
                        <TableCell colSpan={2}></TableCell>
                          <TableCell colSpan={6}><p style={{fontSize:17,fontWeight: "bold"}} >T???ng</p></TableCell>
                          <TableCell align="left">
                          <SeverityPill color={"error"}>
                          {currencyFormat(totalTon)}
                          </SeverityPill>
                          </TableCell>
                        </TableRow>
                      </TableBody>
                    </Table> : ""
                  }
                </Card>
              </Grid>
              {/* <Grid item xs={7} md={7}>
              </Grid> */}
              <Grid item xs={6} md={6}>
                <p style={{  textAlign: 'left', fontWeight: "bold", fontSize: 20, marginLeft: 65 }}>
                  T???ng h??a ????n : {dataEdit && dataEdit.totalBill && dataEdit.totalBill || 0} VND
                </p>
              </Grid>
              <Grid item xs={4} md={4}>
                <p style={{  textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  N??? c?? : {dataEdit && dataEdit.owe && currencyFormat(dataEdit.owe) || 0} VND
                </p>
              </Grid>
              <Grid item xs={6} md={6}>
                <p style={{  textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  ???? thanh to??n : {dataEdit && dataEdit.pay && currencyFormat(dataEdit.pay) || 0} VND
                </p>

              </Grid>
              <Grid item xs={4} md={4}>
                <p style={{  textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  C??n l???i : {dataEdit && dataEdit.totalMoney && currencyFormat(dataEdit.totalMoney) || 0} VND
                </p>

              </Grid>
            </Grid>

          </DialogContent>
          <DialogActions  >
            <div>
              <Button type="reset" onClick={() => handleClose()}
                style={{ fontSize: 20, marginRight: 10, fontFamily: "Times New Roman", color: "black" }}
                color="error" size="small" variant="contained" autoFocus  >
                H???y
              </Button>
              {/* <Button type="button"
                style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }}
                color="secondary" size="small" variant="contained" autoFocus onClick={onPrint} >
                In h??a ????n
              </Button> */}
            </div>
          </DialogActions>
        </form>
      </BootstrapDialog>

    </div>
  );
}
