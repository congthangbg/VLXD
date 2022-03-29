import Head from 'next/head';
import { Box, Container, Grid, Pagination } from '@mui/material';
import { products } from '../__mocks__/products';
import { useEffect, useState } from 'react';
import axiosInstance from '../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, VILLAGE_API } from '../components/component/MessageContants';
import { Search as SearchIcon } from '../icons/search';

import { DashboardLayout } from '../components/dashboard-layout';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import login401 from 'src/hook/login401';
import CustomerTextField from 'src/components/component/CustomerTextField';
import { VillageListResults } from 'src/components/village/VillageListResults';
import useCallVillage from 'src/hook/useCallVillage';
import CustomizedDialogs from 'src/components/village/CustomizedDialogs';
const Village = () => {
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
 const {data,setData}= useCallVillage(query)

 useEffect(()=>{
  axiosInstance.get(VILLAGE_API.GET_ALL + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
    axiosInstance.get(VILLAGE_API.GET_ALL  + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
    axiosInstance.post(VILLAGE_API.DELETE + "?id=" + dataDelete.id)
      .then(response => {
        toastifyAlert.success(DELETE_SUCCESS)
        handleSearch();
      })
      .catch(err => {
        console.log(err);
        toastifyAlert.error(DELETE_ERROR)
      })
  }
  return <>
    <Head>
      <title>
        Thôn
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
          title = "Thôn"
        />
        
        <Box sx={{ mt: 3 }}>
            <VillageListResults
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
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
  </>
}

Village.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Village;
