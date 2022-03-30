import { useState } from 'react';
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
import { AccessAlarm, ThreeDRotation, Edit, Delete } from '@mui/icons-material';


export const ProductListResults = ({
  customers,
  setOpenModal,
  handleDelete,
  handleEdit,
  setOpen,
  setQuery,
  query, ...rest }) => {
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(0);


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
  function currencyFormat(num) {
    return  num.toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
 }
  return (
    <Card {...rest} >
      {/* <PerfectScrollbar> */}
        <Box sx={{ minWidth: 1050}}>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>
                      STT
                    </TableCell>
                    <TableCell>
                      Tên sản phẩm
                    </TableCell>
                    <TableCell>
                      Giá
                    </TableCell>
                    <TableCell>
                     Số lượng
                    </TableCell>
                    <TableCell>
                     Đơn vị tính
                    </TableCell>
                    <TableCell>
                    Loại sản phẩm
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
                        <Box
                          sx={{
                            alignItems: 'center',
                            display: 'flex'
                          }}
                        >
                          <Typography
                            color="textPrimary"
                            variant="body1"
                          >
                            {customer.name}
                          </Typography>
                        </Box>
                      </TableCell>
                      <TableCell>
                        {currencyFormat(customer.price)}
                      </TableCell>
                      <TableCell>
                        {customer.quantity}
                      </TableCell>
                      <TableCell>
                        {customer && customer.unit && customer.unit.unitName || ''}
                      </TableCell>
                      <TableCell>
                      {customer && customer.productType && customer.productType.typeName || ''}

                      </TableCell>
                      <TableCell style={{width:220}}>
                        <Box
                          sx={{
                            alignItems: 'center',
                            display: 'flex'
                          }}
                        >
                          <Typography
                            color="textPrimary"
                            variant="body1"
                          >
                            <Button onClick={() => handleUpdate(customer)} style={{ marginRight: 4 }} color="warning" variant="contained">
                              Sửa
                              <Edit />
                            </Button>
                            <Button onClick={() => handleDelete1(customer)} color="error" variant="contained">
                              Xóa
                              <Delete />
                            </Button>

                          </Typography>

                        </Box>
                      </TableCell>
                      {/* <TableCell>
                    {format(customer.createdAt, 'dd/MM/yyyy')}
                  </TableCell> */}
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
        </Box>
      {/* </PerfectScrollbar> */}
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

// ProductListResults.propTypes = {
//   customers: PropTypes.array.isRequired
// };
