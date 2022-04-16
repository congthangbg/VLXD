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
    if(dataEdit && dataEdit.hdxCt) {
      dataEdit.hdxCt.map((e,i)=>{
        tong = tong + (e.quantity * e.price)
     })  
    }
    setTotal(tong)
  },[dataEdit])
  React.useEffect(() => {
    const tong = 0;
    if(dataEdit && dataEdit.hdxCtTon) {
      dataEdit.hdxCtTon.map((p,i)=>{
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
            Chi tiết đơn hàng
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <Grid container spacing={2}>
              <Grid item xs={5}>
                <p style={{ fontSize: 25, fontWeight: "bold", fontFamily: "Times New Roman", textAlign: 'center' }}>
                  HUYỀN TOÀN</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  ĐC: Xuân Minh - Hương Mai - VY - BG</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 64 }}>
                  SĐT : 0983868072</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 64 }}>
                  STK : 0983868072 MB BANK</p>
              </Grid>
              <Grid item xs={1}>

              </Grid>
              <Grid item xs={6}>
                <p style={{ fontSize: 25, fontWeight: "bold", fontFamily: "Times New Roman", textAlign: 'center' }}>
                  CHUYÊN VẬT LIỆU XÂY DỰNG</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  SẮT THÉP THÁI NGUYÊN, SẮT HỘP MẠ KẼM...</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  TẤM LỢP, TÔN XỐP, TÔN LẠNH, TÔN TRẦN, TÔN VÁCH...</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center' }}>
                  CÁC PHỤ KIỆN NGÀNH SẮT.</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center', fontSize: 30, fontWeight: "bold" }}>
                  HÓA ĐƠN THANH TOÁN</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'center', fontSize: 17 }}>
                  Ngày tạo : {dataEdit &&dataEdit.releaseDate&& format(new Date(dataEdit.releaseDate ), 'dd/MM/yyyy')|| ''}</p>
              </Grid>
              <Grid item xs={12}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  Khách hàng : {dataEdit && dataEdit.customer && dataEdit.customer.name || ''} </p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  Địa chỉ : {dataEdit && dataEdit.customer && dataEdit.customer.village.villageName || ''}</p>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', marginLeft: 65, fontWeight: "bold" }}>
                  SĐT : {dataEdit && dataEdit.customer && dataEdit.customer.phone || ''}</p>
              </Grid>
              <Grid item xs={12}>
                <Card >
                  {/* <Box sx={{ minWidth: 1050 }}> */}
                  {dataEdit && dataEdit.hdxCt && dataEdit.hdxCt.length > 0 ?
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>
                            STT
                          </TableCell>
                          <TableCell>
                            Tên sản phẩm
                          </TableCell>
                          <TableCell>
                            Đơn vị tính
                          </TableCell>
                          <TableCell>
                            Số lượng
                          </TableCell>
                          <TableCell>
                            Đơn giá
                          </TableCell>
                          <TableCell>
                            Thành tiền
                          </TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {dataEdit && dataEdit.hdxCt.map((p, i) => (
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

                          </TableRow>
                        ))}
                        <TableRow>
                        <TableCell colSpan={2}></TableCell>
                          <TableCell colSpan={3}><p style={{fontSize:17,fontWeight: "bold"}} >Tổng</p></TableCell>
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
                  {dataEdit && dataEdit.hdxCtTon && dataEdit.hdxCtTon.length > 0 ?
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>
                            STT
                          </TableCell>
                          <TableCell>
                            Tên sản phẩm
                          </TableCell>
                          <TableCell>
                            Chiều dài(m)
                          </TableCell>
                          <TableCell>
                            Chiều rộng(m)
                          </TableCell>
                          <TableCell>
                            Số tấm
                          </TableCell>
                          <TableCell>
                            ĐVT
                          </TableCell>
                          <TableCell>
                            Số m2
                          </TableCell>
                          <TableCell>
                            Đơn giá
                          </TableCell>
                          <TableCell>
                            Thành tiền
                          </TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {dataEdit && dataEdit.hdxCtTon.map((p, i) => (
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
                          </TableRow>
                        ))}
                        <TableRow>
                        <TableCell colSpan={2}></TableCell>
                          <TableCell colSpan={6}><p style={{fontSize:17,fontWeight: "bold"}} >Tổng</p></TableCell>
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
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', fontWeight: "bold", fontSize: 20, marginLeft: 65 }}>
                  Tổng hóa đơn : {dataEdit && dataEdit.totalBill && dataEdit.totalBill || 0} VND
                </p>
              </Grid>
              <Grid item xs={4} md={4}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  Nợ cũ : {dataEdit && dataEdit.owe && currencyFormat(dataEdit.owe) || 0} VND
                </p>
              </Grid>
              <Grid item xs={6} md={6}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  Đã thanh toán : {dataEdit && dataEdit.pay && currencyFormat(dataEdit.pay) || 0} VND
                </p>

              </Grid>
              <Grid item xs={4} md={4}>
                <p style={{ fontFamily: "Times New Roman", textAlign: 'left', fontWeight: "bold", fontSize: 18, marginLeft: 65 }}>
                  Còn lại : {dataEdit && dataEdit.totalMoney && currencyFormat(dataEdit.totalMoney) || 0} VND
                </p>

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
              <Button type="button"
                style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }}
                color="secondary" size="small" variant="contained" autoFocus onClick={onPrint} >
                In hóa đơn
              </Button>
            </div>
          </DialogActions>
        </form>
      </BootstrapDialog>

    </div>
  );
}
