// Generated with g9.

package com.vn.VLXD.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.vn.VLXD.entities.HdnCt;
import com.vn.VLXD.entities.HdnCtTon;
import com.vn.VLXD.entities.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HdnResponse  {

	private long id;
    private String code;
    private Double totalMoney;
    private String totalBill;
    private LocalDateTime dateAdded;
    private Double owe;
    private Double pay;
    private long status;
    private List<HdnCt> hdnCt;
    private List<HdnCtTon> hdnCtTon;
    private Supplier supplier;
    private String createBy;
    private LocalDateTime modifyDate;
    private String updateBy;


}
