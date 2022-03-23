import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SEARCH_VILLAGE, GETALL_AND_SREACH_CUSTOMER } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallVillage() {
   const [data, setData] = useState([]);
   useEffect(() => {
    axiosInstance.get(GETALL_AND_SEARCH_VILLAGE)
    .then(response => {
      setData(response.data)
    })
    .catch(err => {
      console.log(err);
      login401(err &&err.response && err.response.status)
   
    })
    
  },[])
   return data;
}

export default useCallVillage;