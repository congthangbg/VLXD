import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SREACH_CUSTOMER, PRODUCT_TYPE } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallProductType() {
   const [dataType, setDataType] = useState([]);
   useEffect(() => {
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
      setDataType(result)
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response&&err.response.status)
    })
    
  },[])
   return {dataType,setDataType};
}

export default useCallProductType;