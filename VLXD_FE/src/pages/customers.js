import Head from 'next/head';
import { CustomerListResults } from '../components/customer/customer-list-results';
import { DashboardLayout } from '../components/dashboard-layout';
// import { customers } from '../__mocks__/customers';
import { useEffect, useState } from 'react';
import axiosInstance from './../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR } from './../components/component/MessageContants';
import { Search as SearchIcon } from '../icons/search';
import {
  Container,
  Box,
  Card,
  Typography,
  Button,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon,
} from '@mui/material';
import CustomerToolbar from './../components/customer/CustomerToolbar';
import { ToastContainer } from 'react-toastify';
import CustomizedDialogs from 'src/components/customer/CustomizedDialogs';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import login401 from 'src/hook/login401';
const Customers = () => {

  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [data, setData] = useState([])
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })

  useEffect(()=>{
    handleSearch();
   },[])

 useEffect(()=>{
  handleSearch();
 },[query.page,query.limit])

  const handleTextSearch = (e) => {
    setQuery({
      ...query,
      keySearch:e ? e :""
    })
  }
  const handleSearch = () => {
    axiosInstance.get(GETALL_AND_SREACH_CUSTOMER + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
      .then(response => {
        const result = {
          data: null,
          totalRecords:null
        }
        result.data = response.data.map((item, index) => ({
          ...item,
          order: index + 1,
        }))
        result.totalRecords = response.totalRecords;
        setData(result)
      })
      .catch(err => {
        console.log(err);
        login401(err && err.response && err.response.status)
      })
  }
  const handleDelete = (e) => {
    setDataDelete(e)
  }
  const handleEdit = (e) => {
    setDataEdit(e)
  }
  const onDetele = () => {
    setOpenModal(false)
    axiosInstance.post(DELETE_CUSTOMER + "?id=" + dataDelete.id)
      .then(response => {
        toastifyAlert.success(DELETE_SUCCESS)
        handleSearch();
      })
      .catch(err => {
        console.log(err);
        toastifyAlert.error(DELETE_ERROR)
      })
  }
  const onSave = (val) => {
    axiosInstance.post(SAVE_UPDATE_CUSTOMER, val)
    .then(response => {
      toastifyAlert.success(TB_SAVE_UPDATE_CUSTOMER)
      handleSearch();
    })
    .catch(err => {
      console.log("ee", err);
      toastifyAlert.error(TB_SAVE_UPDATE_CUSTOMER_ERR)
    })
  }
  return <>
    <Head>
      <title>
        Customers 
      </title>
    </Head>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 8
      }}
    >
      <Container maxWidth={false}>
        <CustomerToolbar
          setOpen={setOpen}
          handleSearch={handleTextSearch}
          onSearch={handleSearch}
          query={query}
          setQuery={setQuery}
        />
        <Box sx={{ mt: 3 }}>
          <CustomerListResults
            setOpenModal={setOpenModal}
            handleDelete={handleDelete}
            customers={data}
            handleEdit={handleEdit}
            setOpen={setOpen}
            setQuery={setQuery}
            query={query}
          />
        </Box>
      </Container>
    </Box>
    <CustomizedDialogs
      dataEdit={dataEdit}
      setDataEdit={setDataEdit}
      open={open}
      setOpen={setOpen}
      onSave={onSave}
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
  </>
}
Customers.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);


export default Customers;
