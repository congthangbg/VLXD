import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SREACH_CUSTOMER } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallCustomer() {
   const [dataCustomer, setDataCustomer] = useState([]);
   useEffect(() => {
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
    
  },[])
   return {dataCustomer,setDataCustomer};
}

export default useCallCustomer;