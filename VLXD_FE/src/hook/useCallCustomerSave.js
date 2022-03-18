import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SREACH_CUSTOMER, SAVE_UPDATE_CUSTOMER } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallCustomerSave(val) {
  console.log("valu",val);
   const [data, setData] = useState([]);
useEffect(() => {
  axiosInstance.post(SAVE_UPDATE_CUSTOMER,val)
  .then(response => {
    console.log("Res");
    setData(response)
  })
  .catch(err => {
    console.log(err);
    login401(err && err.response&&err.response.status)
  })
},[])
   

   return data;
}

export default useCallCustomerSave;