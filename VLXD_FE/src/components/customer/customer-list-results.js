import { useState } from 'react';
import PerfectScrollbar from 'react-perfect-scrollbar';
import PropTypes from 'prop-types';
import { format } from 'date-fns';
import { spacing } from '@mui/system';
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
} from '@mui/material';
import { getInitials } from '../../utils/get-initials';
import useMagicColor from './../../hook/useMagicColor';
import { AccessAlarm, ThreeDRotation,Edit,Delete } from '@mui/icons-material';


export const CustomerListResults = ({
   customers,
   setOpenModal,
   handleDelete,
   handleEdit,
   setOpen,
   setQuery,
   query, ...rest }) => {
  const [selectedCustomerIds, setSelectedCustomerIds] = useState([]);
  const [limit, setLimit] = useState(10);
  const [page, setPage] = useState(0);

  const handleSelectAll = (event) => {
    let newSelectedCustomerIds;

    if (event.target.checked) {
      newSelectedCustomerIds = customers.map((customer) => customer.id);
    } else {
      newSelectedCustomerIds = [];
    }

    setSelectedCustomerIds(newSelectedCustomerIds);
  };

  const handleSelectOne = (event, id) => {
    const selectedIndex = selectedCustomerIds.indexOf(id);
    let newSelectedCustomerIds = [];

    if (selectedIndex === -1) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds, id);
    } else if (selectedIndex === 0) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds.slice(1));
    } else if (selectedIndex === selectedCustomerIds.length - 1) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(selectedCustomerIds.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelectedCustomerIds = newSelectedCustomerIds.concat(
        selectedCustomerIds.slice(0, selectedIndex),
        selectedCustomerIds.slice(selectedIndex + 1)
      );
    }

    setSelectedCustomerIds(newSelectedCustomerIds);
  };

  const handleLimitChange = (event) => {
    setLimit(event.target.value);
    setQuery({
      ...query,
      limit: event.target.value
    })
  };

  const handlePageChange = (event, newPage) => {
    setPage(newPage);
    setQuery({
      ...query,
      page: newPage
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


  return (
    <Card {...rest}>
      <PerfectScrollbar>
        <Box sx={{ minWidth: 1050 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>
                  STT
                </TableCell>
                <TableCell>
                  Họ tên
                </TableCell>
                <TableCell>
                  Số điện thoại
                </TableCell>
                <TableCell>
                  Địa chỉ
                </TableCell>
                <TableCell>
                  Chú thích
                </TableCell>
                <TableCell>
                  Hành động
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {customers.slice(0, limit).map((customer) => (
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
                      {/* <Avatar
                        src={customer.avatarUrl}
                        sx={{ mr: 2 }}
                      >
                        {getInitials(customer.name)}
                      </Avatar> */}
                      <Typography
                        color="textPrimary"
                        variant="body1"
                      >
                        {customer.name}
                      </Typography>
                    </Box>
                  </TableCell>
                  <TableCell>
                    {customer.phone}
                  </TableCell>
                  <TableCell>
                    {customer.village.villageName}
                  </TableCell>
                  <TableCell>
                    {customer.address}
                    {/* {`${customer.address.city}, ${customer.address.state}, ${customer.address.country}`} */}
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
                        <Button  onClick={()=>handleUpdate(customer) }  style={{marginRight:4}} color="warning" variant="contained">
                          Sửa
                          <Edit/>
                        </Button>
                        <Button onClick={()=>handleDelete1(customer) } color="error" variant="contained">
                          Xóa
                          <Delete/>
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
      </PerfectScrollbar>
      <TablePagination
        component="div"
        count={customers.length}
        onPageChange={handlePageChange}
        onRowsPerPageChange={handleLimitChange}
        page={page}
        rowsPerPage={limit}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
};

CustomerListResults.propTypes = {
  customers: PropTypes.array.isRequired
};
