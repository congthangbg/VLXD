import { useEffect, useState } from 'react';
import PerfectScrollbar from 'react-perfect-scrollbar';
import PropTypes from 'prop-types';
import { format } from 'date-fns';
import { spacing } from '@mui/system';
import moment from 'moment'
import {
  Avatar,
  Box,
  Card,
  Checkbox,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TablePagination,
  TableRow,
  Typography,
  Button,
  CardContent,
} from '@mui/material';
import { getInitials } from '../../utils/get-initials';
import useMagicColor from '../../hook/useMagicColor';
import { AccessAlarm, ThreeDRotation, Edit, Delete, Preview, Visibility, LocalPrintshop } from '@mui/icons-material';
import { SeverityPill } from '../severity-pill';
import { currencyFormat, DELETE_ERROR, NOTIFY } from '../component/MessageContants';
import toastifyAlert from '../component/toastify-message/toastify';


export const HdxListResults = ({
  customers,
  setOpenModal,
  setOpenView,
  handleDelete,
  handleEdit,
  setOpen,
  setQuery,
  print,
  query, ...rest }) => {
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(0);

  useEffect(() => {
    setPage(query && query.page || 0)
  }, [query.page]);

  const handleLimitChange = (event) => {
    setLimit(event.target.value);
    setQuery({
      ...query,
      page: 0,
      limit: event.target.value
    })
    setPage(0)
  };

  const handlePageChange = (event, newPage) => {
    setPage(newPage);
    setQuery({
      ...query,
      page: newPage,
      skip: newPage * query.limit
    })
  };
  const handleDelete1 = (e) => {
    setOpenModal(true)
    handleDelete(e)
  }
  const handleUpdate = (e) => {
    setOpen(true)
    handleEdit(e)
  }
  const handlePrint = (e) => {
    print(e)
  }
  return (
    <Card >
      <Box sx={{ minWidth: 1050 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>
                STT
              </TableCell>
              <TableCell>
                MHD
              </TableCell>
              <TableCell>
                Tên khách hàng
              </TableCell>
              <TableCell>
                Địa chỉ
              </TableCell>
              <TableCell>
                Tổng tiền HĐ
              </TableCell>
              <TableCell>
                Nợ cũ
              </TableCell>
              <TableCell>
                Đã thanh toán
              </TableCell>
              <TableCell>
                Còn lại
              </TableCell>
              <TableCell>
                Ngày tạo
              </TableCell>
              <TableCell>
                Trạng thái
              </TableCell>
              <TableCell>
                Hành động
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {customers && customers.data && customers.data.slice(0, limit).map((customer) => (
              <TableRow
                hover
                key={customer.id}
              >
                <TableCell >
                  {customer.order}
                </TableCell>
                <TableCell>
                  {"HD" + customer.id}
                </TableCell>
                <TableCell>
                  {customer && customer.customer && customer.customer.name || ''}
                </TableCell>
                <TableCell>
                  {customer && customer.customer && customer.customer.village.villageName || ''}
                </TableCell>
                <TableCell>
                  {customer && customer.totalBill || 0}
                </TableCell>
                <TableCell>
                  {customer && customer.owe && currencyFormat(customer.owe) || 0}
                </TableCell>
                <TableCell>
                  {customer && customer.pay && currencyFormat(customer.pay) || 0}
                </TableCell>
                <TableCell>
                  <SeverityPill color={"secondary"}>
                    {customer && customer.totalMoney && currencyFormat(customer.totalMoney) || 0}
                  </SeverityPill>
                </TableCell>
                <TableCell>
                  {customer && customer.releaseDate ? format(new Date(customer.releaseDate), 'HH:mm dd/MM/yyyy') : new Date()}
                </TableCell>
                <TableCell>
                  <SeverityPill
                    color={customer && customer.status == 1 ? 'error' : (customer.status == 2 ? 'success' : 'warning')}
                  >
                    {customer.status == 1 ? 'Chờ thanh toán' : (customer.status == 2 ? 'Đã thanh toán' : "Khác")}
                  </SeverityPill>
                </TableCell>
                <TableCell style={{ width: '200px' }}>
                  <div>
                    <Button size="small" onClick={() => {
                      setOpenView(true)
                      handleEdit(customer)
                    }} style={{ marginRight: 4 }} color="success" variant="contained">
                      <Visibility />
                    </Button>
                    <Button size="small" onClick={() => handlePrint(customer)} style={{ marginRight: 4 }} color="primary" variant="contained">
                      <LocalPrintshop />
                    </Button>
                    {customer && customer.status !== 2 ?
                      <div>
                        <Button size="small" onClick={() => handleUpdate(customer)} style={{ marginRight: 4 }} color="warning" variant="contained">
                          <Edit />
                        </Button>
                        <Button size="small" onClick={() => handleDelete1(customer)} color="error" variant="contained">
                          <Delete />
                        </Button>
                      </div>
                      : ''}


                  </div>
                </TableCell>

              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Box>
      <TablePagination
        component="div"
        count={customers && customers.totalRecords || 0}
        onPageChange={handlePageChange}
        onRowsPerPageChange={handleLimitChange}
        page={page}
        rowsPerPage={limit}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
};

// HdxListResults.propTypes = {
//   customers: PropTypes.array.isRequired
// };
