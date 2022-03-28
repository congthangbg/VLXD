import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SEARCH_VILLAGE, GETALL_AND_SREACH_CUSTOMER, VILLAGE_API } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallVillage(query) {
   const [data, setData] = useState([]);
   useEffect(() => {
    axiosInstance.get(VILLAGE_API.GET_ALL)
    .then(response => {
      const result ={
        data:null,
        totalRecords :null
      }
      result.data= response && response.data.map((item, index) => ({
        ...item,
        order: query.skip + index + 1,
      }))
      result.totalRecords = response.totalRecords;
      setData(result)
    })
    .catch(err => {
      console.log(err);
      login401(err &&err.response && err.response.status)
   
    })
    
  },[])
   return {data,setData};
}

export default useCallVillage;