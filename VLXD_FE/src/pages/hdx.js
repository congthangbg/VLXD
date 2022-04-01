import Head from 'next/head';
import { Box, Container, Grid, Pagination } from '@mui/material';
import { products } from '../__mocks__/products';
import { useEffect, useState } from 'react';
import axiosInstance from '../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, HDX_API, VILLAGE_API, PRODUCT, UNIT_API } from '../components/component/MessageContants';
import { Search as SearchIcon } from '../icons/search';

import { DashboardLayout } from '../components/dashboard-layout';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import login401 from 'src/hook/login401';
import CustomerTextField from 'src/components/component/CustomerTextField';
import useCallVillage from 'src/hook/useCallVillage';
import { HdxListResults } from 'src/components/hdx/HdxListResults';
import CustomizedDialogs from 'src/components/hdx/CustomizedDialogs';
import useCallCustomer from 'src/hook/useCallCustomer';
const Hdx = () => {
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
//  const {data,setData}= useCallVillage()
 const [data,setData] = useState([])
 const [dataCustomer, setDataCustomer] = useState([]);
 const [dataProductType, setDataProductType] = useState([]);
 const [dataProduct, setDataProduct] = useState([]);
 const [dataUnit, setDataUnit] = useState([]);
 const getInitCustomer = ()=>{
  axiosInstance.get(GETALL_AND_SREACH_CUSTOMER)
  .then(response => {
    const result ={
      data:null,
      totalRecords :null
    }
    result.data= response && response.data.map((item, index) => ({
      ...item,
      order: index + 1,
    }))
    result.totalRecords = response.totalRecords;
    setDataCustomer(result)
  })
  .catch(err => {
    console.log(err);
    login401(err && err.response&&err.response.status)
  })
 }
 const getProductType = ()=>{
  axiosInstance.get(PRODUCT_TYPE.GET_ALL)
  .then(response => {
    const result ={
      data:null,
      totalRecords :null
    }
    result.data= response && response.data.map((item, index) => ({
      ...item,
      order: index + 1,
    }))
    result.totalRecords = response.totalRecords;
    setDataProductType(result)
  })
  .catch(err => {
    console.log(err);
    login401(err && err.response&&err.response.status)
  })
 }
 const getProduct = ()=>{
  axiosInstance.get(PRODUCT.GET_ALL)
  .then(response => {
    const result ={
      data:null,
      totalRecords :null
    }
    result.data= response && response.data.map((item, index) => ({
      ...item,
      order: index + 1,
    }))
    result.totalRecords = response.totalRecords;
    setDataProduct(result)
  })
  .catch(err => {
    console.log(err);
    login401(err && err.response&&err.response.status)
  })
 }
 const getListUnit= () => {
  axiosInstance.get(UNIT_API.GET_ALL)
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
      setDataUnit(result)
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
 useEffect(()=>{
  handleSearch();
  getInitCustomer();
  getProductType();
  getProduct();
  getListUnit();
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
    axiosInstance.get(HDX_API.GET_ALL  + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
    axiosInstance.post(HDX_API.DELETE + "?id=" + dataDelete.id)
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
        Hóa đơn xuất
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
          title = "Hóa đơn xuất"
        />
        
        <Box sx={{ mt: 3 }}>
            <HdxListResults
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
      dataCustomer={dataCustomer}
      getInitCustomer={getInitCustomer}
      dataProductType={dataProductType}
      dataProduct={dataProduct}
      dataUnit={dataUnit}
      getProduct={getProduct}
      setDataProduct={setDataProduct}
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
  </>
}

Hdx.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Hdx;
