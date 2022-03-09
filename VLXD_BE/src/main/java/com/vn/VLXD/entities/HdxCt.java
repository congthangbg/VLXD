// Generated with g9.

package com.vn.VLXD.entities;

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
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="HDX_CT")
@Data
@AllArgsConstructor
public class HdxCt implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="QUANTITY", precision=53)
    private double quantity;
    @Column(name="PRICE", precision=53)
    private double price;
    @Column(name="NOTE", length=500)
    private String note;
    @Column(name="CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name="CREATE_BY", length=100)
    private String createBy;
    @Column(name="MODIFY_DATE")
    private LocalDateTime modifyDate;
    @Column(name="UPDATE_BY", length=100)
    private String updateBy;
    @Column(name="STATUS", precision=19)
    private long status;
    @ManyToOne
    @JoinColumn(name="HDX_ID")
    private Hdx hdx;
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    /** Default constructor. */
    public HdxCt() {
        super();
    }

   
}
