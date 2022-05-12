// Generated with g9.

package com.vn.VLXD.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.HdxCt;
import com.vn.VLXD.entities.HdxCtTon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HdxResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2709443514216953149L;
	private long id;
    private String code = "";
    private double totalMoney;
    private String totalBill;
    private LocalDateTime releaseDate;
    private double owe;
    private Double pay;
    private long status;
    private List<HdxCt> hdxCt;
    private List<HdxCtTon> hdxCtTon;
    private Customer customer;


}
