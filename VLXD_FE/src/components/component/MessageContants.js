export  const logOut = "Đăng xuất thành công !"
export const logIn = "Đăng nhập thành công !"
export const errorLogIn = "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!"
export const LOGIN_FAILED = "Vui lòng đăng nhập lại!"
export const TB_SAVE_UPDATE_CUSTOMER = "Lưu khách hàng thành công!"
export const TB_SAVE_UPDATE_CUSTOMER_ERR = "Lưu khách hàng thất bại!"

export const LOGIN = "/login"

//phone
export const phoneRegExp = /^((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?$/
export const PHONE={
   MIN : 'Số điện thoại không được < 8 kí tự !',
   MAX : 'Số điện thoại không được > 10 kí tự !',
   VALID_PHONE : 'Số điện thoại không đúng định dạng !',
   
}

//Thôn list
export const GETALL_AND_SEARCH_VILLAGE = "/village"
//Khách hàng
export const GETALL_AND_SREACH_CUSTOMER = "/customer/callCustomer"
export const DELETE_CUSTOMER = "/customer/delete"
export const SAVE_UPDATE_CUSTOMER = "/customer/saveOrUpdate"
//Loại sản phẩm
export const PRODUCT_TYPE={
   GET_ALL : '/productType',
   SAVE_UPDATE:"/productType/saveOrUpdate",
   DELETE:"/productType/delete",
}
//Sản phẩm
export const PRODUCT={
   GET_ALL : '/product',
   SAVE_UPDATE:"/product/saveOrUpdate",
   DELETE:"/product/delete",
   GET_PRODUCT_BY_TYPE_ID:"/product/findByProductType",
}
//list thôn
export const VILLAGE_API={
   GET_ALL : '/village',
   SAVE_UPDATE:"/village/saveOrUpdate",
   DELETE:"/village/delete",
}
//danh mục đơn vị tính
export const UNIT_API={
   GET_ALL : '/unit',
   SAVE_UPDATE:"/unit/saveOrUpdate",
   DELETE:"/unit/delete",
}
//tài khoản
export const ACCOUNT_API={
   GET_ALL : '/auth/listAccount',
   GET_ALL_ROLE : '/auth/listRolse',
   SAVE_UPDATE:"/auth/signup",
   DELETE:"/unit/delete",
}
//Hóa đơn xuất
export const HDX_API={
   GET_ALL : '/hdx',
   GET_REPORT : '/report',
   SAVE_UPDATE:"/hdx/saveOrUpdate",
   DELETE:"/hdx/delete",
   FIND_TOTAL_OWN:"/hdx/findTotalOwn",
}
//list Hóa đơn nhập
export const HDN_API={
   GET_ALL : '/hdn',
   SAVE_UPDATE:"/hdn/saveOrUpdate",
   DELETE:"/hdn/delete",
}
//nahf cung ccaps
export const SUPPLIER_API={
   GET_ALL : '/supplier',
   SAVE_UPDATE:"/supplier/saveOrUpdate",
   DELETE:"/supplier/delete",
}

export function currencyFormat3(num) {
   return num && num.toFixed(3).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,') || 0
 }
 export function currencyFormat(num) {
    if(String(num).includes(",")){
       return num;
    }else{
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
    }
}
export const STATUS_401 = 401
export const DA_THANH_TOAN = 2
export const CHO_THANH_TOAN = 1 // 2: đã thanh toán,1: chờ thanh toán
export const SAVE_SUCCESS = "Thao tác thành công!"
export const SAVE_ERROR = "Thao tác thất bại!"

export const DELETE_SUCCESS = "Xóa thành công!"
export const DELETE_ERROR = "Xóa thất bại!"

export const NOTIFY ={
   NOT_NAME : 'Bạn chưa nhập họ tên !',
   VILLAGE : 'Bạn chưa chọn thôn !',
   NOT_BLANK : 'Không được để trống !',
   VALID_PHONE:'Số điện thoại không đúng định dạng !',
   P_TYPE : 'Bạn chưa chọn loại sản phẩm !',
   PRODUCT : 'Bạn chưa chọn sản phẩm !',
   UNIT : 'Bạn chưa chọn đơn vị tính !',
   NUMBER: "Chỉ được nhập ký tự số !",
   PAY: "Chưa nhập số tiền thanh toán !",
   IS_PAY_SUCCESS: "Bản ghi đã thanh toán , không thể cập nhật !",
   MESSAGE_CODE_OK: "OK",

}