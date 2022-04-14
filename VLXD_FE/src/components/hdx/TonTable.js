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


export const TonTable = ({
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
  function currencyFormat(num) {
    return  num.toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
 }
 function currencyFormat3(num) {
  return  num.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
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
                Chiều dài(m)
              </TableCell>
              <TableCell>
                Chiều rộng(m)
              </TableCell>
              <TableCell>
                Số tấm
              </TableCell>
              <TableCell>
                Đơn vị tính
              </TableCell>
              <TableCell>
               Số m2
              </TableCell>
              <TableCell>
                Đơn giá
              </TableCell>
              <TableCell>
                Thành tiền
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
                  {p.name ? p.name : (p.product && p.product.name || '')}
                </TableCell>
                <TableCell>
                  {p.width}
                </TableCell>
                <TableCell>
                  {p.height}
                </TableCell>
                <TableCell>
                  {p.quantity}
                </TableCell>
                <TableCell>
                  {p.unit ? p.unit : (p.product && p.product.unit && p.product.unit.unitName || '')}
                </TableCell>
                <TableCell>
                  {currencyFormat3(Number(p.width) * Number(p.height) * Number(p.quantity))}
                </TableCell>
                <TableCell>
                  {p && p.price ? currencyFormat(p.price) : 0}
                </TableCell>
                <TableCell>
                <SeverityPill color={"secondary"}>
                {p.price && p.quantity && p.width && p.height ? currencyFormat(p.price * (Number(p.width) * Number(p.height) * Number(p.quantity))) : ""}
                </SeverityPill>
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
