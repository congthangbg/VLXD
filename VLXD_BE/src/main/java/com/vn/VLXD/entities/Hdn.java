// Generated with g9.

package com.vn.VLXD.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="HDN")
@Data
@AllArgsConstructor
public class Hdn implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="DATE_ADDED")
    private LocalDateTime dateAdded;
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
    @Column(name="OWE", precision=53)
    @Nullable
    private Double owe;
    @Column(name="PAY")
    private Double pay;
    @Column(name="TOTAL_MONEY")
    private Double totalMoney;
    @Column(name="TOTAL_BILL")
    private String totalBill;
    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
//    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Account account;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="SUPPLIER_ID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Supplier supplier;
//    @OneToMany(mappedBy="hdn",fetch = FetchType.LAZY)
//    private List<HdnCt> hdnCt;
//    
//    public List<HdnCt> getHdnCt() {
//		return hdnCt;
//	}
//
//	public void setHdnCt(List<HdnCt> hdnCt) {
//		this.hdnCt = hdnCt;
//	}

	/** Default constructor. */
    public Hdn() {
        super();
    }

 

}
