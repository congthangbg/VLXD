import { React, useEffect, useState } from 'react'
import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon, Typography, Grid, Paper, IconButton
} from '@mui/material';
import { Search as SearchIcon } from '../../icons/search';
import CustomizedDialogs from './CustomizedDialogs';
import { ToastContainer } from 'react-toastify';
import ClearIcon from '@mui/icons-material/Clear';

import { styled } from '@mui/material/styles';
function CustomerToolbar(props) {
  const { setOpen, handleSearch, onSearch ,query,setQuery} = props;
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));
  const [data, setData] = useState("")
 const handleKeyPress = (event) => {
    if(event.key === 'Enter'){
      const newData = {
        ...query,
        keySearch:data
      }
      setQuery(newData)
      onSearch(newData)
    }
  }
  const onTextSearch = (e) => {
    setData(e.target.value)
    handleSearch(e.target.value)
  }
  const onClear = ()=>{
    const newData = {
      ...query,
      keySearch:''
    }
    setQuery(newData)
    setData('')
  }
  useEffect(()=>{
    if(data==''){
      onSearch(query)
    }
  },[data])
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
            onClick={e => setOpen(true)}
          >
            Thêm mới khách hàng
          </Button>
        </Box>
      </Box>
      <Box sx={{ mt: 3 }}>
        <Card>
          <CardContent>
            <Grid container spacing={2}>
              <Grid item xs={3} md={5}>
                <TextField
                  fullWidth
                  onChange={onTextSearch}
                  onKeyPress={handleKeyPress}
                  value={data && data || ''}
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="start">
                         <IconButton onClick={onClear}  type="reset" sx={{ p: '10px' }} aria-label="search">
                            <ClearIcon />
                          </IconButton>
                          <IconButton onClick={onSearch}  type="submit" sx={{ p: '10px' }} aria-label="search">
                            <SearchIcon />
                          </IconButton>
                      </InputAdornment>
                    )
                  }}
                  placeholder="Search customer"
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={6} md={4} >
                {/* <Button
                  size="large"
                  color="secondary"
                  variant="contained"
                  onClick={onSearch}
                  fontSize="medium"
                >
                  Tìm kiếm
                </Button> */}
              </Grid>
            </Grid>

          </CardContent>
        </Card>
      </Box>

    </div>

  )

}

export default CustomerToolbar