import Head from 'next/head';
import { Box, Container, Grid, Typography } from '@mui/material';
import { AccountProfile } from '../components/account/account-profile';
import { AccountProfileDetails } from '../components/account/account-profile-details';
import { DashboardLayout } from '../components/dashboard-layout';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import login401 from 'src/hook/login401';
import CustomerTextField from 'src/components/component/CustomerTextField';
import CustomizedDialogs from 'src/components/account/CustomizedDialogs';
import axiosInstance from '../components/config/axiosConfig';
import { useEffect, useState } from 'react';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, ACCOUNT_API, NOTIFY, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, UNIT_API, VILLAGE_API } from '../components/component/MessageContants';
import { AccountListResults } from 'src/components/account/AccountListResults';

const Account = () => {
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
const [data, setData] = useState({})
const [roles, setRoles] = useState([])

 useEffect(()=>{
  handleSearch(query);
  getRoles();
 },[])
//  useEffect(()=>{
//   handleSearch(query);
//  },[query.page,query.limit])


  const handleTextSearch = (e) => {
    setQuery({
      ...query,
      keySearch:e ? e :""
    })
  }
  
const getRoles = () => {
  axiosInstance.get(ACCOUNT_API.GET_ALL_ROLE)
    .then(response => {
      if(response.messageCode == NOTIFY.MESSAGE_CODE_OK){
        const result = {
          data: null,
          totalRecords:null
        }
        result.data = response.data.map((item, index) => ({
          ...item,
          order: index + 1,
        }))
        result.totalRecords = response.totalRecords;
        setRoles(result)
      }else{
        toastifyAlert.error(response.message ? response.message : SAVE_ERROR)
      }
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
  const handleSearch = (query) => {
    axiosInstance.get(ACCOUNT_API.GET_ALL  + `?page=${query.page}&size=${query.limit}&keySearch=${query.keySearch}`)
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
    axiosInstance.get(ACCOUNT_API.DELETE + `/${dataDelete.id}`)
      .then(response => {
        if(response.messageCode == NOTIFY.MESSAGE_CODE_OK){
          toastifyAlert.success(DELETE_SUCCESS)
          handleSearch(query);
        }else{
          toastifyAlert.error(response.message ? response.message :DELETE_ERROR)
        }
      
      })
      .catch(err => {
        console.log(err);
        toastifyAlert.error(DELETE_ERROR)
      })
  }
  return <>
    <Head>
      <title>
        Account | Material Kit
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
        <CustomerTextField
          setOpen={setOpen}
          handleSearch={handleTextSearch}
          onSearch={handleSearch}
          query={query}
          setQuery={setQuery}
          title = "Tài khoản"
          />
        
        <Box sx={{ mt: 3 }}>
            <AccountListResults
            setOpenModal={setOpenModal}
            handleDelete={handleDelete}
            customers={data}
            handleEdit={handleEdit}
            setOpen={setOpen}
            setQuery={setQuery}
            query={query}
          
          />
        </Box>
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'center',
            pt: 3
          }}
          >
        </Box>
      </Container>
    </Box>
    <CustomizedDialogs
      dataEdit={dataEdit}
      setDataEdit={setDataEdit}
      open={open}
      setOpen={setOpen}
      handleSearch={handleSearch}
      query={query}
      roles={roles}
      />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
      />
  </>
}
;

Account.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Account;
