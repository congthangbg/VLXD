export  const logOut = "Đăng xuất thành công !"
export const logIn = "Đăng nhập thành công !"
export const errorLogIn = "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!"
export const LOGIN_FAILED = "Vui lòng đăng nhập lại!"
export const TB_SAVE_UPDATE_CUSTOMER = "Lưu khách hàng thành công!"
export const TB_SAVE_UPDATE_CUSTOMER_ERR = "Lưu khách hàng thất bại!"

export const LOGIN = "/login"

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
//nahf cung ccaps
export const SUPPLIER_API={
   GET_ALL : '/supplier',
   SAVE_UPDATE:"/supplier/saveOrUpdate",
   DELETE:"/supplier/delete",
}

export const STATUS_401 = 401
export const SAVE_SUCCESS = "Thêm mới thành công!"
export const SAVE_ERROR = "Thêm mới thất bại!"

export const DELETE_SUCCESS = "Xóa thành công!"
export const DELETE_ERROR = "Xóa thất bại!"

export const NOTIFY ={
   NOT_NAME : 'Bạn chưa nhập họ tên !',
   VILLAGE : 'Bạn chưa chọn thôn !',
   NOT_BLANK : 'Không được để trống !',
   VALID_PHONE:'Số điện thoại không đúng định dạng !',
   P_TYPE : 'Bạn chưa chọn loại sản phẩm !',
   UNIT : 'Bạn chưa chọn đơn vị tính !',
}