import React, { useEffect, useState } from "react";
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import { Autocomplete, Box, Checkbox, Link, TextField } from '@mui/material';
import { Field, useFormik } from 'formik';
import * as Yup from 'yup';
import { ACCOUNT_API, GETALL_AND_SEARCH_VILLAGE, LOGIN, LOGIN_FAILED, NOTIFY, PRODUCT_TYPE, SAVE_ERROR, SAVE_SUCCESS, SAVE_UPDATE_CUSTOMER, STATUS_401, STAUTS_401, TB_SAVE_UPDATE_CUSTOMER, TB_SAVE_UPDATE_CUSTOMER_ERR, UNIT_API, VILLAGE_API } from '../component/MessageContants';
import axiosInstance from '../config/axiosConfig';
import toastifyAlert from '../component/toastify-message/toastify';
import { ToastContainer } from 'react-toastify';
import { useRouter } from 'next/router';
import login401 from 'src/hook/login401';
import useCallVillage from 'src/hook/useCallVillage';
import useCallCustomerSave from 'src/hook/useCallCustomerSave';
import NextLink from 'next/link';

const BootstrapDialog = styled(Dialog)(({ theme }) => ({
  '& .MuiDialogContent-root': {
    padding: theme.spacing(2),
  },
  '& .MuiDialogActions-root': {
    padding: theme.spacing(1),
  },
}));


const BootstrapDialogTitle = (props) => {
  const { children, open, onClose, ...other } = props;
  return (
    <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
      {children}
      {onClose ? (
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: 'absolute',
            right: 8,
            top: 8,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </DialogTitle>
  );
};

BootstrapDialogTitle.propTypes = {
  children: PropTypes.node,
  onClose: PropTypes.func.isRequired,
};

export default function CustomizedDialogs(props) {
  const formik = useFormik({
    enableReinitialize: true,
    initialValues: {
      // email: '',
      id: '',
      accountName: '',
      password: '',
      role: ""
      // policy: false
    },
    validationSchema: Yup.object({
      // email: Yup
      //   .string()
      //   .email(
      //     'Must be a valid email')
      //   .max(255)
      //   .required(
      //     'Email is required'),
      // firstName: Yup
      //   .string()
      //   .max(255)
      //   .required(
      //     'First name is required'),
      accountName: Yup
        .string()
        .max(255)
        .required(
          'User name is required'),
      password: Yup
        .string()
        .max(255)
        .required(
          'Password is required'),
      role: Yup
        .array().nullable()
        .required("Chưa chọn quyền"),
      // policy: Yup
      //   .boolean()
      //   .oneOf(
      //     [true],
      //     'This field must be checked'
      //   )
    }),
    onSubmit: (values, { resetForm }) => {
      const a = [];
      values.role.map(role => {
        a.push(role.name);
      })
      const newData = {
        ...values,
        role: a
      }
      axiosInstance.post(ACCOUNT_API.SAVE_UPDATE, newData)
        .then(response => {
          if (response.messageCode == NOTIFY.MESSAGE_CODE_OK) {
            handleSearch(query);
            toastifyAlert.success(SAVE_SUCCESS)
          } else {
            toastifyAlert.error(response.message ? response.message : SAVE_ERROR)
          }

        })
        .catch(err => {
          console.log("ee", err);
          toastifyAlert.error(SAVE_ERROR)
        })
      handleClose();
      resetForm();

    }
  });
  const router = useRouter();
  const { open, setOpen, dataEdit,
    setDataEdit, handleSearch, query, roles } = props;
  const handleClose = () => {
    setOpen(false);
    formik.resetForm();
    setDataEdit(null)
  };
  useEffect(() => {
    if (dataEdit) {
      formik.setFieldValue("accountName", dataEdit && dataEdit.accountName || "");
      formik.setFieldValue("role", dataEdit && dataEdit.roles || "")
      formik.setFieldValue("id", dataEdit && dataEdit.id || "")
    }
  }, [dataEdit])
  return (
    <div>
      <BootstrapDialog
        onClose={handleClose}
        aria-labelledby="customized-dialog-title"
        open={open}
        maxWidth='md'
        fullWidth={true}
      >
        <form onSubmit={formik.handleSubmit}>
          <BootstrapDialogTitle id="customized-dialog-title" onClose={handleClose}>
            Thêm mới tài khoản
          </BootstrapDialogTitle>
          <DialogContent dividers>
            <form onSubmit={formik.handleSubmit}>

              <TextField
                error={Boolean(formik.touched.accountName && formik.errors.accountName)}
                fullWidth
                helperText={formik.touched.accountName && formik.errors.accountName}
                label="Tài khoản"
                margin="normal"
                name="accountName"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                value={formik.values.accountName}
                disabled={dataEdit != null && dataEdit !== undefined ? true:false}
                variant="outlined"
              />
              <TextField
                error={Boolean(formik.touched.password && formik.errors.password)}
                fullWidth
                helperText={formik.touched.password && formik.errors.password}
                label="Mật khẩu"
                margin="normal"
                name="password"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                type="password"
                value={formik.values.password}
                variant="outlined"
              />
              <Autocomplete
                multiple= {true}
                id="role"
                name="role"
                options={roles && roles.data ? roles.data : []}
                // groupBy={ option => option.state }
                getOptionLabel={option => option && option.name || []}
                onChange={(event, value) => formik.setFieldValue("role", value)}
                // onSelect={(event, value) => console.log(event.target.value)}
                value={formik.values && formik.values.role ? formik.values.role : []}
                style={{ width: 500 }}
                renderInput={params => (
                  <TextField
                    {...params}
                    onChange={formik.handleChange}
                    variant="standard"
                    margin="normal"
                    label="Quyền"
                    fullWidth
                    error={Boolean(formik.touched.role && formik.errors.role)}
                    helperText={formik.touched.role && formik.errors.role}
                  />
                )}
              />

              {Boolean(formik.touched.policy && formik.errors.policy) && (
                <FormHelperText error>
                  {formik.errors.policy}
                </FormHelperText>
              )}
              {/* <Box sx={{ py: 2 }}>
              <Button
                color="primary"
                disabled={formik.isSubmitting}
                fullWidth
                size="large"
                type="submit"
                variant="contained"
              >
                Sign Up Now
              </Button>
            </Box> */}

            </form>
          </DialogContent>
          <DialogActions  >
            <Typography
              color="textSecondary"
              variant="body2"
              style={{ float: "left" }}
            >
              Bạn đã có tài khoản?
              {' '}
              <NextLink
                href="/login"
                passHref
              >
                <Link
                  variant="subtitle2"
                  underline="hover"
                >
                  Đăng nhập
                </Link>
              </NextLink>
            </Typography>
            <div style={{ marginLeft: 500 }}>  </div>
            <Button type="reset" onClick={() => handleClose()} style={{ fontSize: 20, marginRight: 10, fontFamily: "Times New Roman", color: "black" }} color="error" size="large" variant="contained" autoFocus  >
              Hủy
            </Button>
            <Button type="submit" style={{ fontSize: 20, marginRight: 30, fontFamily: "Times New Roman", color: "black" }} color="secondary" size="large" variant="contained" autoFocus  >
              Lưu lại
            </Button>
          </DialogActions>
        </form>
      </BootstrapDialog>
    </div>
  );
}
