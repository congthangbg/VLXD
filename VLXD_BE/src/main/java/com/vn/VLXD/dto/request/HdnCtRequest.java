// Generated with g9.

package com.vn.VLXD.dto.request;


import java.io.Serializable;

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

    /** Default constructor. */
    public HdnCtRequest() {
        super();
    }

}
