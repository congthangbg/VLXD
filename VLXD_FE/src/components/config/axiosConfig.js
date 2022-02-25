import axios from "axios";
import config from "./config.json";

const localUrl = config.localhost;
const axiosInstance = axios.create({
  baseURL: `${localUrl}/api/`,
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});


// Add a request interceptor
axiosInstance.interceptors.request.use(
  function (config) {
    const userInfo = localStorage.getItem("access_token");
    if (userInfo) {
      config.headers = {
        Authorization:`Bearer ${userInfo}` ,
      };
    }
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

// Add a response interceptor
axiosInstance.interceptors.response.use(
  function (response) {
    return response.data;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default axiosInstance;
