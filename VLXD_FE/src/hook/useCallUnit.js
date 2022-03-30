import { React, useState, useEffect, useRef } from 'react';
import { GETALL_AND_SEARCH_VILLAGE, GETALL_AND_SREACH_CUSTOMER, UNIT_API, VILLAGE_API } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useCallUnit(query) {
   const [dataUnit, setDataUnit] = useState([]);
   useEffect(() => {
    axiosInstance.get(UNIT_API.GET_ALL)
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
        setDataUnit(result)
      })
      .catch(err => {
        console.log(err);
        login401(err && err.response && err.response.status)
      })
    
  },[])
   return {dataUnit,setDataUnit};
}

export default useCallUnit;