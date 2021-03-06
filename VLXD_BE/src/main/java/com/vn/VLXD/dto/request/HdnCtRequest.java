// Generated with g9.

package com.vn.VLXD.dto.request;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.vn.VLXD.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class HdnCtRequest implements Serializable {

    private long id;
    private double quantity;
    private double price;
    private String note;
    private long status;
    private long hdnId;
    private long productId;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime modifyDate;
    private String updateBy;
    private Product product;

    /** Default constructor. */
    public HdnCtRequest() {
        super();
    }

}
