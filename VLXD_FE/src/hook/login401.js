import { React, useState, useEffect, useRef } from 'react';
import { useRouter } from 'next/router';
import { LOGIN, LOGIN_FAILED, STATUS_401 } from 'src/components/component/MessageContants';
import toastifyAlert from 'src/components/component/toastify-message/toastify';


function login401(err) {
   if(err === STATUS_401){
      toastifyAlert.error(LOGIN_FAILED)
      window.location.href = LOGIN;
    }
   
}

export default login401;