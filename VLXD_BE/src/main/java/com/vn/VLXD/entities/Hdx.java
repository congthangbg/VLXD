// Generated with g9.

package com.vn.VLXD.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="HDX")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hdx implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="CODE")
    private String code;
    @Column(name="TOTAL_MONEY")
    private String totalMoney;
    @Column(name="RELEASE_DATE")
    private LocalDateTime releaseDate;
    @Column(name="OWE", precision=53)
    private double owe;
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
//    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
//    private Set<HdxCt> hdxCt;
//    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
//    private Set<HdxCtTon> hdxCtTon;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
//    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
//    private Set<Pay> pay;


}
