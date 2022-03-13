import {React,useState} from 'react'
import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon, Typography
} from '@mui/material';
import { Search as SearchIcon } from '../../icons/search';
import CustomizedDialogs from './CustomizedDialogs';
import { ToastContainer } from 'react-toastify';
function CustomerToolbar(props) {
const {setOpen} = props;
  return (
    <div>
       <Box
      sx={{
        alignItems: 'center',
        display: 'flex',
        justifyContent: 'space-between',
        flexWrap: 'wrap',
        m: -1
      }}
    >
      <Typography
        sx={{ m: 1 }}
        variant="h4"
      >
        Khách hàng
      </Typography>

      <Box sx={{ m: 1 }}>
        <Button
          color="primary"
          variant="contained"
          onClick={e=>setOpen(true)}
        >
          Thêm mới khách hàng
        </Button>
      </Box>
    </Box>
    <Box sx={{ mt: 3 }}>
      <Card>
        <CardContent>
          <Box sx={{ maxWidth: 500 }}>
            <TextField
              fullWidth
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <SvgIcon
                      color="action"
                      fontSize="small"
                    >
                      <SearchIcon />
                    </SvgIcon>
                  </InputAdornment>
                )
              }}
              placeholder="Search customer"
              variant="outlined"
            />
          </Box>
        </CardContent>
      </Card>
    </Box>

    </div>
   
  )

}

export default CustomerToolbar