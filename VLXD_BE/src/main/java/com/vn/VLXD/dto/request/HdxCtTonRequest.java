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
public class HdxCtTonRequest implements Serializable {
    private long id;
    private double width;
    private double height;
    private double quantity;
    private double numberM2;
    private double price;
    private String note;
    private long status;
    private long productId;

    /** Default constructor. */
    public HdxCtTonRequest() {
        super();
    }


}
