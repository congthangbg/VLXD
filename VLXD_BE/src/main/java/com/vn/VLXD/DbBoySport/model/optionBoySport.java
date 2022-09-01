//package com.vn.VLXD.DbBoySport.model;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name="OPTIONS")
//public class optionBoySport {
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name="OPTION_ID", unique=true, nullable=false, precision=19)
//    private long id;
//    @Column(name="OPTION_NAME", length=200)
//    private String name;
//    @Column(name="CREATE_DATE")
//    private LocalDateTime createDate;
//    @Column(name="CREATE_BY", length=100)
//    private String createBy;
//    @Column(name="MODIFY_DATE")
//    private LocalDateTime modifyDate;
//    @Column(name="UPDATE_BY", length=100)
//    private String updateBy;
//    @Column(name="STATUS", precision=19)
//    private long status =1;
//
//}
