import Head from 'next/head';
import { Box, Container, Grid, Pagination } from '@mui/material';
import { products } from '../__mocks__/products';
import { useEffect, useState } from 'react';
import axiosInstance from './../components/config/axiosConfig';
import { DELETE_CUSTOMER, DELETE_ERROR, DELETE_SUCCESS, GETALL_AND_SREACH_CUSTOMER, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS } from './../components/component/MessageContants';
import { Search as SearchIcon } from '../icons/search';

import { DashboardLayout } from '../components/dashboard-layout';
import AlertDialog from 'src/components/component/AlertDialog';
import toastifyAlert from 'src/components/component/toastify-message/toastify';
import useCallCustomer from 'src/hook/useCallCustomer';
import login401 from 'src/hook/login401';
import CustomerTextField from 'src/components/component/CustomerTextField';
import { ProductTypeListResults } from 'src/components/productType/ProductTypeListResults';
import CustomizedDialogs from 'src/components/productType/CustomizedDialogs';
import useCallProductType from 'src/hook/useCallProductType';
const ProductType = () => {
  const [check, setCheck] = useState(false)
  const [open, setOpen] = useState(false)
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
 const [data,setData] = useState([])

 useEffect(()=>{
 handleSearch();
 },[query.page,query.limit])
 useEffect(()=>{
  handleSearch();
  },[])
 

  const handleTextSearch = (e) => {
    setQuery({
      ...query,
      keySearch:e ? e :""
    })
  }
  const handleSearch = () => {
    axiosInstance.get(PRODUCT_TYPE.GET_ALL  + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
    axiosInstance.post(PRODUCT_TYPE.DELETE + "?id=" + dataDelete.id)
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
        Loại sản phẩm
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
          title = "Loại sản phẩm"
        />
        
        <Box sx={{ mt: 3 }}>
          <Grid
            container
            spacing={3}
          >
            <ProductTypeListResults
            setOpenModal={setOpenModal}
            handleDelete={handleDelete}
            customers={data}
            handleEdit={handleEdit}
            setOpen={setOpen}
            setQuery={setQuery}
            query={query}
          />
          </Grid>
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
      setCheck={setCheck}
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
  </>
}

ProductType.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default ProductType;
