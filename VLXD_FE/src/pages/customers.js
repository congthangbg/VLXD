import Head from 'next/head';
import { CustomerListResults } from '../components/customer/customer-list-results';
import { DashboardLayout } from '../components/dashboard-layout';
// import { customers } from '../__mocks__/customers';
import { useEffect, useState } from 'react';
import axiosInstance from './../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, SAVE_ERROR, SAVE_SUCCESS } from './../components/component/MessageContants';
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
import useCallCustomer from 'src/hook/useCallCustomer';
import login401 from 'src/hook/login401';
const Customers = () => {

  const [check, setCheck] = useState(false)
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
 const {data,setData}= useCallCustomer(check, setCheck, query)

 useEffect(()=>{
  axiosInstance.get(GETALL_AND_SREACH_CUSTOMER + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
  .then(response => {
    const result ={
      data:null,
      totalRecords :null
    }
    result.data= response && response.data.map((item, index) => ({
      ...item,
      order: query.skip + index + 1,
    }))
    result.totalRecords = response.totalRecords;
    setData(result)
    setCheck(false)
    setQuery({...query,skip:0})
  })
  .catch(err => {
    console.log(err);
    login401(err && err.response && err.response.status)
  })
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
        setCheck(false)
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
        setCheck(true)
        toastifyAlert.success(DELETE_SUCCESS)
      })
      .catch(err => {
        console.log(err);
        toastifyAlert.error(DELETE_ERROR)
        setCheck(false)
      })
  }
  return <>
    <Head>
      <title>
        Customers | Material Kit
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
      setCheck={setCheck}
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
