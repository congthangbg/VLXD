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
import ClearIcon from '@mui/icons-material/Clear';

import { styled } from '@mui/material/styles';
function CustomerTextField(props) {
  const { setOpen, handleSearch, onSearch ,query,setQuery,title} = props;
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
          mt: -7
        }}
      >
        <Typography
          sx={{ m: 1 }}
          variant="h4"
        >
          {title ? title : 'Màn mới'}
        </Typography>

        <Box sx={{ m: 1 }}>
          <Button
            color="primary"
            variant="contained"
            onClick={e => setOpen(true)}
          >
            Thêm mới {title}
          </Button>
        </Box>
      </Box>
      <Box sx={{ mt: 1 }}>
        {/* <Card>
          <CardContent> */}
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
                  placeholder="Tìm kiếm..."
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

          {/* </CardContent>
        </Card> */}
      </Box>

    </div>

  )

}

export default CustomerTextField