import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SREACH_CUSTOMER } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallCustomer(check) {
   const [data, setData] = useState([]);
   useEffect(() => {
      axiosInstance.get(GETALL_AND_SREACH_CUSTOMER)
    .then(response => {
      const result = {
        data: null,
      }
      result.data = response.data.map((item, index) => ({
        ...item,
        order: index + 1,
      }))
      setData(result.data)
    })
    .catch(err => {
      console.log(err);
      login401(err.response.status)
    })
    
  },[check])
   return data;
}

export default useCallCustomer;