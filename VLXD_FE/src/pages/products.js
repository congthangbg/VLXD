import Head from 'next/head';
import { Box, Container, Grid, Pagination } from '@mui/material';
import { products } from '../__mocks__/products';
import CustomerTextField from 'src/components/component/CustomerTextField';
import { useState,useEffect } from 'react';
import axiosInstance from 'src/components/config/axiosConfig';
import { DELETE_ERROR, DELETE_SUCCESS, PRODUCT } from 'src/components/component/MessageContants';
import login401 from 'src/hook/login401';
import { DashboardLayout } from 'src/components/dashboard-layout';
import { ProductListResults } from 'src/components/product/ProductListResults';
import useCallUnit from 'src/hook/useCallUnit';
import CustomizedDialogs from 'src/components/product/CustomizedDialogs';
import AlertDialog from 'src/components/component/AlertDialog';
import useCallProductType from 'src/hook/useCallProductType';
import toastifyAlert from 'src/components/component/toastify-message/toastify';

const Products = () => {
  const [open, setOpen] = useState(false)
  const [query, setQuery] = useState({ keySearch: '', limit: 10, page: 0,skip:0 })
  const [openModal, setOpenModal] = useState(false)
  const [dataDelete, setDataDelete] = useState({})
  const [dataEdit, setDataEdit] = useState({})
  const [data, setData] = useState([])
  const {dataUnit,setDataUnit} = useCallUnit();
  const {dataType,setDataType} = useCallProductType();
  const handleTextSearch = (e) => {
    setQuery({
      ...query,
      keySearch:e ? e :"",
      limit: 10, page: 0,skip:0 
    })
  }
 useEffect(()=>{
  handleSearch(query);
 },[])
 useEffect(()=>{
  handleSearch(query);
  },[query.page,query.limit])


  const handleSearch = (query) => {
    axiosInstance.get(PRODUCT.GET_ALL  + `?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
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
    axiosInstance.post(PRODUCT.DELETE + "?id=" + dataDelete.id)
      .then(response => {
        toastifyAlert.success(DELETE_SUCCESS)
        handleSearch(query);
      })
      .catch(err => {
        console.log(err);
        toastifyAlert.error(DELETE_ERROR)
      })
  }
 return <>
    <Head>
      <title>
       Danh sách sản phẩm
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
          title = "Sản phẩm"
        />
        <Box sx={{ pt: 3 }}>
        <ProductListResults
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
      handleSearch={handleSearch}
      dataUnit={dataUnit}
      dataType={dataType}
    />
    <AlertDialog open={openModal}
      setOpen={setOpenModal}
      onDelete={onDetele}
    />
  </>
};

Products.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Products;
