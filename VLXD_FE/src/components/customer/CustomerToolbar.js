import { React, useEffect, useState } from 'react'
import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  InputAdornment,
  SvgIcon, Typography, Grid, Paper, IconButton, Autocomplete
} from '@mui/material';
import { Search as SearchIcon } from '../../icons/search';
import CustomizedDialogs from './CustomizedDialogs';
import { ToastContainer } from 'react-toastify';
import ClearIcon from '@mui/icons-material/Clear';

import { styled } from '@mui/material/styles';
import useCallVillage from 'src/hook/useCallVillage';
function CustomerToolbar(props) {
  const {data}=useCallVillage();
  const { setOpen, handleSearch, onSearch ,query,setQuery,isComboxVillage} = props;
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));
  const [data1, setData] = useState("")
 const handleKeyPress = (event) => {
    if(event.key === 'Enter'){
      const newData = {
        ...query,
        keySearch:data1 ? data1 :"",
        page: 0, skip: 0,
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
    if(data1==''){
      onSearch(query)
    }
  },[data1])

  const handleClickSearch = ()=>{
    onSearch(query)
  }

  const onChange = (e) => {
    if (e) {
      const newData = {
        ...query,
         page: 0, skip: 0,
        villageId: e && e.id ? e.id:""
      }
      setQuery(newData)
      onSearch(newData)
    } else if (e == null) {
      const newData = {
        ...query,
        villageId: "",
        page: 0, skip: 0,
      }
      setQuery(newData)
      onSearch(newData)
    }
  }
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
        {/* <Card>
          <CardContent> */}
            <Grid container spacing={2}>
              <Grid item xs={3} md={5}>
                <TextField
                  fullWidth
                  size="small"
                  onChange={onTextSearch}
                  onKeyPress={handleKeyPress}
                  value={data1 && data1 || ''}
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="start">
                         <IconButton onClick={onClear}  type="reset" sx={{ p: '10px' }} aria-label="search">
                            <ClearIcon />
                          </IconButton>
                          <IconButton onClick={handleClickSearch}  type="submit" sx={{ p: '10px' }} aria-label="search">
                            <SearchIcon />
                          </IconButton>
                      </InputAdornment>
                    )
                  }}
                  placeholder="Search customer"
                  variant="outlined"
                />
              </Grid>
              {isComboxVillage == true ?
            <Grid item xs={3} md={3}>
              <Autocomplete
                size="small"
                onChange={(event, value) => onChange(value)}
                disablePortal
                id="combo-box-demo"
                options={data && data.data ? data.data : []}
                getOptionLabel={op => op.villageName}
                sx={{ width: 300 }}
                renderInput={(params) => <TextField {...params} label="Thôn" />}
              />
                </Grid>
              : ""}
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

          {/* </CardContent>
        </Card> */}
      </Box>

    </div>

  )

}

export default CustomerToolbar