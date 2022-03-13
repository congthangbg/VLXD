import { useEffect, useState } from 'react';
import { SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR } from 'src/components/component/MessageContants';
import axiosInstance from 'src/components/config/axiosConfig';
import login401 from './login401';



function useSaveCustomer({setCheck,values}) {
   const [data, setData] = useState([]);
 console.log("values",values);
    axiosInstance.post(SAVE_UPDATE_CUSTOMER,values)
    .then(response=>{
      setData(response)
      toastifyAlert.success(TB_SAVE_UPDATE_CUSTOMER)
      setCheck(true)
    })
    .catch(err => {
      console.log("ee",err);
      toastifyAlert.error(TB_SAVE_UPDATE_CUSTOMER_ERR)
      setCheck(false)
      login401(err.response.status)
    })
   
   return data;
}

export default useSaveCustomer;