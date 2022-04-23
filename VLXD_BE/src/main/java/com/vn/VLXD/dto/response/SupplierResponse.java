// Generated with g9.

package com.vn.VLXD.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {

    private long id;
    private String name;
    private String phone;
    private String address;
    private Timestamp createDate;
    private String createBy;
    private Timestamp modifyDate;
    private String updateBy;
    private long status =1 ;

}
