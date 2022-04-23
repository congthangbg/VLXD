import Head from 'next/head';
import { Box, Container, Grid, Pagination } from '@mui/material';
import { products } from '../__mocks__/products';
import { useEffect, useState } from 'react';
import axiosInstance from '../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, HDN_API, NOTIFY, PRODUCT, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SUPPLIER_API, UNIT_API, VILLAGE_API } from '../components/component/MessageContants';
import { Search as SearchIcon } from '../icons/search';

import { DashboardLayout } from '../components/dashboard-layout';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import login401 from 'src/hook/login401';
import useCallVillage from 'src/hook/useCallVillage';
import { HdnListResults } from 'src/components/hdn/HdnListResults';
import CustomerTextField from 'src/components/component/CustomerTextField';
import CustomizedDialogs from 'src/components/hdn/CustomizedDialogs';
import ViewDetails from 'src/components/hdn/ViewDetails';
const Hdn = () => {
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [dataSupplier, setDataSupplier] = useState([])
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
 const [data,setData]= useState([])
//  const {data,setData}= useCallVillage()
 const [dataProductType, setDataProductType] = useState([]);
 const [dataProduct, setDataProduct] = useState([]);
 const [dataUnit, setDataUnit] = useState([]);
 const [openView, setOpenView] = useState(false)

 const getInitSupplier = () => {
  axiosInstance.get(SUPPLIER_API.GET_ALL)
    .then(response => {
      const result = {
        data: null,
        totalRecords: null
      }
      result.data = response && response.data.map((item, index) => ({
        ...item,
        order: index + 1,
      }))
      result.totalRecords = response.totalRecords;
      setDataSupplier(result)
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
const getProductType = () => {
  axiosInstance.get(PRODUCT_TYPE.GET_ALL)
    .then(response => {
      const result = {
        data: null,
        totalRecords: null
      }
      result.data = response && response.data.map((item, index) => ({
        ...item,
        order: index + 1,
      }))
      result.totalRecords = response.totalRecords;
      setDataProductType(result)
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
const getProduct = () => {
  axiosInstance.get(PRODUCT.GET_ALL)
    .then(response => {
      if(response.messageCode === NOTIFY.MESSAGE_CODE_OK){
        const result = {
          data: null,
          totalRecords: null
        }
        result.data = response && response.data.map((item, index) => ({
          ...item,
          order: index + 1,
        }))
        result.totalRecords = response.totalRecords;
        setDataProduct(result)
      }else{
        toastifyAlert.error(response.message ? response.message :SAVE_ERROR)
      }
   
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
const getListUnit = () => {
  axiosInstance.get(UNIT_API.GET_ALL)
    .then(response => {
      if(response.messageCode === NOTIFY.MESSAGE_CODE_OK){
        const result = {
          data: null,
          totalRecords: null
        }
        result.data = response.data.map((item, index) => ({
          ...item,
          order: index + 1,
        }))
        result.totalRecords = response.totalRecords;
        setDataUnit(result)
      }else{
        toast.error(response.message ? response.message : SAVE_ERROR)
      }
      
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response && err.response.status)
    })
}
useEffect(()=>{
  getInitSupplier()
  getProductType();
  getProduct();
  getListUnit();
 },[])
 useEffect(()=>{
  handleSearch(query)
 },[query.page,query.limit])


  const handleTextSearch = (e) => {
    setQuery({
      ...query,
      keySearch:e ? e :""
    })
  }
  const handleSearch = (query) => {
    axiosInstance.get(HDN_API.GET_ALL  + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
        if(response.messageCode == NOTIFY.MESSAGE_CODE_OK){
          toastifyAlert.success(DELETE_SUCCESS)
          handleSearch(query);
        }else{
          toastifyAlert.error(response.message ? response.message : DELETE_ERROR)
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
        Hóa đơn nhập
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
          title = "Hóa đơn nhập"
        />
        
        <Box sx={{ mt: 3 }}>
            <HdnListResults
            setOpenModal={setOpenModal}
            handleDelete={handleDelete}
            customers={data}
            handleEdit={handleEdit}
            setOpen={setOpen}
            setQuery={setQuery}
            query={query}
            setOpenView={setOpenView}
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
      dataCustomer={dataSupplier}
      getInitCustomer={getInitSupplier}
      dataProductType={dataProductType}
      dataProduct={dataProduct}
      dataUnit={dataUnit}
      getProduct={getProduct}
      setDataProduct={setDataProduct}
      query={query}
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
        <ViewDetails
      open={openView}
      setOpen={setOpenView}
      setDataEdit={setDataEdit}
      dataEdit={dataEdit}
      print={()=>console.log()}
    />
  </>
}

Hdn.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Hdn;
