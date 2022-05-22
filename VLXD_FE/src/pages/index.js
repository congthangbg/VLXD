import Head from 'next/head';
import { Box, Container, Grid } from '@mui/material';
import { Budget } from '../components/dashboard/budget';
import { LatestOrders } from '../components/dashboard/latest-orders';
import { LatestProducts } from '../components/dashboard/latest-products';
import { Sales } from '../components/dashboard/sales';
import { TasksProgress } from '../components/dashboard/tasks-progress';
import { TotalCustomers } from '../components/dashboard/total-customers';
import { TotalProfit } from '../components/dashboard/total-profit';
import { TrafficByDevice } from '../components/dashboard/traffic-by-device';
import { DashboardLayout } from '../components/dashboard-layout';
import axiosInstance from 'src/components/config/axiosConfig';
import { DASHBOARD } from 'src/components/component/MessageContants';
import { useEffect, useState } from 'react';
import login401 from 'src/hook/login401';

const Dashboard = () => {

  const [dataBillNotPay, setDataBillNotPay] = useState(0)
  const [moneyNotPay, setMoneyNotPay] = useState(0)
  const [moneyPay, setMoneyPay] = useState(0)

 const getBillNotPay = () => {
    axiosInstance.get(DASHBOARD.GET_BILL_NOT_PAY)
      .then(response => {
        setDataBillNotPay(response.data)
      })
      .catch(err => {
        console.log(err);
        login401(err && err.response && err.response.status)
      })
  }
  const getMoneyNotPay = () => {
    axiosInstance.get(DASHBOARD.GET_MONEY_NOT_PAY)
      .then(response => {
        setMoneyNotPay(response.data)
      })
      .catch(err => {
        console.log(err);
        login401(err && err.response && err.response.status)
      })
  }
  const getMoneyPay = () => {
    axiosInstance.get(DASHBOARD.GET_MONEY_PAY)
      .then(response => {
        setMoneyPay(response.data)
      })
      .catch(err => {
        console.log(err);
        login401(err && err.response && err.response.status)
      })
  }
  useEffect(() => {
    getBillNotPay();
    getMoneyNotPay();
    getMoneyPay();
  }, [])
 return <>
    <Head>
      <title>
        Dashboard
      </title>
    </Head>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 8
      }}
    >
      <Container maxWidth={false}>
        <Grid
          container
          spacing={3}
        >
          <Grid
            item
            lg={3}
            sm={6}
            xl={3}
            xs={12}
          >
            <Budget dataBillNotPay={dataBillNotPay} />
          </Grid>
          <Grid
            item
            xl={3}
            lg={3}
            sm={6}
            xs={12}
          >
            <TotalCustomers moneyNotPay={moneyNotPay} />
          </Grid>
          <Grid
            item
            xl={3}
            lg={3}
            sm={6}
            xs={12}
          >
            <TasksProgress />
          </Grid>
          <Grid
            item
            xl={3}
            lg={3}
            sm={6}
            xs={12}
          >
            <TotalProfit moneyPay={moneyPay} sx={{ height: '100%' }} />
          </Grid>
          <Grid
            item
            lg={8}
            md={12}
            xl={9}
            xs={12}
          >
            <Sales />
          </Grid>
          <Grid
            item
            lg={4}
            md={6}
            xl={3}
            xs={12}
          >
            <TrafficByDevice sx={{ height: '100%' }} />
          </Grid>
          {/* <Grid
            item
            lg={4}
            md={6}
            xl={3}
            xs={12}
          >
            <LatestProducts sx={{ height: '100%' }} />
          </Grid>
          <Grid
            item
            lg={8}
            md={12}
            xl={9}
            xs={12}
          >
            <LatestOrders />
          </Grid> */}
        </Grid>
      </Container>
    </Box>
  </>
};

Dashboard.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Dashboard;
