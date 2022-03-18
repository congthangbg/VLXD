import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

export default function AlertDialog(props) {
  const {open,setOpen,text,onDelete} = props;


  const handleOk = () => {
   onDelete();
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Dialog
       fullWidth={true}
       maxWidth='sm'
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
        {text ? text : "Bạn có đồng ý xóa ?"}
        </DialogTitle>
        {/* <DialogContent>
          <DialogContentText id="alert-dialog-description">
         {text ? text : "Bạn có đồng ý xóa ?"}
          </DialogContentText>
        </DialogContent> */}
        <DialogActions>
          <Button onClick={handleClose}>Hủy</Button>
          <Button onClick={handleOk} autoFocus>
            Đồng ý
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
