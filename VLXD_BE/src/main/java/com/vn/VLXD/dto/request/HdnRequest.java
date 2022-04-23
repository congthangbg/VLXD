// Generated with g9.

package com.vn.VLXD.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HdnRequest {
    private long id;
    private long dateAdded;
    private long status;
    private double owe;
    private double pay;
    private double total;
    private String totalBill;
    private List<HdnCtRequest> hdnCt;
    private List<HdnCtTonRequest> hdnCtTonRequest;
    private long supplierId;
  

    /** Default constructor. */
    public HdnRequest() {
        super();
    }


}
