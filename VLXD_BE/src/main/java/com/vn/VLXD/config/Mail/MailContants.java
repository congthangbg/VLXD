package com.vn.VLXD.config.Mail;

public class MailContants {

	    // Replace with your email here:  
	    public static final String MY_EMAIL = "congthangbg1@gmail.com";

	    // Replace password!!
	    public static final String MY_PASSWORD = "congthang1";

	    // And receiver!
//	    public static final String FRIEND_EMAIL = "yourFriend@gmail.com";
	    
	    public static final String MESSAGE = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
	    		"Gmail gửi đang tắt truy cập đến các ứng dụng không an toàn . Các bước khắc phục ",
	    		"1:Đăng nhập tài khoản gmail b dùng để gửi gmail ",
	    		"2:Đi tới Quản lý tài khoản Google của bạn --> Bảo mật --> Quyền truy cập ứng dụng kém an toàn hơn --> Bật quyền truy cập(Không được khuyến nghị)",
	    		"or ",
	    		"Truy cập đường link : ",
	    		"https://www.google.com/settings/security/lesssecureapps",
	    		"3: Chuyển cho phép ứng dụng kém an toàn : TẮT ---> Cho phép ứng dụng kém an toàn : BẬT"
	    		);

}
