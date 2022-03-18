import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SREACH_CUSTOMER } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallCustomer(check,setCheck,query) {
   const [data, setData] = useState([]);
   useEffect(() => {
      axiosInstance.get(GETALL_AND_SREACH_CUSTOMER+`?keySearch=${query.keySearch}&page=${query.page}&size=${query.limit}`)
    .then(response => {
      const result = {
        data: null,
      }
      result.data = response.data.map((item, index) => ({
        ...item,
        order: index + 1,
      }))
      setData(result.data)
      setCheck(false)
    })
    .catch(err => {
      console.log(err);
      login401(err && err.response&&err.response.status)
    })
    
  },[check])
   return {data,setData};
}

export default useCallCustomer;