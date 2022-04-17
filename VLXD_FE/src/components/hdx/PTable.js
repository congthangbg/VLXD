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
import { AccessAlarm, ThreeDRotation, Edit, Delete, Preview, Visibility, LocalPrintshop } from '@mui/icons-material';
import { SeverityPill } from '../severity-pill';
import { currencyFormat } from '../component/MessageContants';


export const PTable = ({
  listData,
  setOpenModal,
  handleDelete,
  handleEdit,
  setOpen,
  setQuery,
  query, ...rest }) => {

  const handleDelete1 = (e) => {
    handleDelete(e)
  }
  const handleUpdate = (e) => {
    handleEdit(e)
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
                Tên sản phẩm
              </TableCell>
              <TableCell>
                Đơn vị tính
              </TableCell>
              <TableCell>
                Số lượng
              </TableCell>
              <TableCell>
                Đơn giá
              </TableCell>
              <TableCell>
                Thành tiền
              </TableCell>
              <TableCell>
                Ghi chú
              </TableCell>
              <TableCell>
                Hành động
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {listData && listData.map((p,i) => (
              <TableRow
                hover
                key={p.id}
              >
                <TableCell >
                  {i+1}
                </TableCell>
                <TableCell>
                  {p.name ? p.name : (p.product && p.product.name|| '')} 
                </TableCell>
                <TableCell>
                  {p.unit ? p.unit : (p.product && p.product.unit && p.product.unit.unitName || '')}
                </TableCell>
                <TableCell>
                  {p.quantity}
                </TableCell>
                <TableCell>
                  {p && p.price &&currencyFormat(p.price) || 0}
                </TableCell>
                <TableCell style={{ width: '165px' }}>
                <SeverityPill color={"secondary"}>
                {p.price && p.quantity ? currencyFormat(p.price * p.quantity) : ""}
                </SeverityPill>
                </TableCell>
                <TableCell>
                  {p.createDate && format(new Date(p.createDate), 'dd/MM/yyyy') || format(new Date(), 'dd/MM/yyyy') }
                </TableCell>
                <TableCell style={{ width: '200px' }}>
                  <div>
                  <Button size="small" onClick={() => handleUpdate(p)} style={{ marginRight: 4 }} color="warning" variant="contained">
                    <Edit />
                  </Button>
                  <Button size="small" onClick={() => handleDelete1(p)} color="error" variant="contained">
                    <Delete />
                  </Button>
                  </div>
                </TableCell>

              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Box>

    </Card>
  );
};

// PTable.propTypes = {
//   customers: PropTypes.array.isRequired
// };
