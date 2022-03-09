// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PayRequest implements Serializable {
    private long id;
    private LocalDateTime payDay;
    private double payAmount;
    private double totalMoneyHdx;
    private long status;
    private long customerId;
    private long hdxId;

    /** Default constructor. */
    public PayRequest() {
        super();
    }

}
