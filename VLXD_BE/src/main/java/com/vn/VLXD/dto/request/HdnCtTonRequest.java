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

import com.vn.VLXD.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class HdnCtTonRequest implements Serializable {
    private long id;
    private String width;
    private String height;
    private double quantity;
    private String numberM2;
    private double price;
    private String note;
    private long status;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime modifyDate;
    private String updateBy;
    private Product product;

    /** Default constructor. */
    public HdnCtTonRequest() {
        super();
    }


}
