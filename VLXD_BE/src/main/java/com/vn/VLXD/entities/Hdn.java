// Generated with g9.

package com.vn.VLXD.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="HDN")
public class Hdn implements Serializable {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
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

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(long aId) {
        id = aId;
    }

    /**
     * Access method for dateAdded.
     *
     * @return the current value of dateAdded
     */
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    /**
     * Setter method for dateAdded.
     *
     * @param aDateAdded the new value for dateAdded
     */
    public void setDateAdded(LocalDateTime aDateAdded) {
        dateAdded = aDateAdded;
    }

    /**
     * Access method for createDate.
     *
     * @return the current value of createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for createDate.
     *
     * @param aCreateDate the new value for createDate
     */
    public void setCreateDate(LocalDateTime aCreateDate) {
        createDate = aCreateDate;
    }

    /**
     * Access method for createBy.
     *
     * @return the current value of createBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Setter method for createBy.
     *
     * @param aCreateBy the new value for createBy
     */
    public void setCreateBy(String aCreateBy) {
        createBy = aCreateBy;
    }

    /**
     * Access method for modifyDate.
     *
     * @return the current value of modifyDate
     */
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    /**
     * Setter method for modifyDate.
     *
     * @param aModifyDate the new value for modifyDate
     */
    public void setModifyDate(LocalDateTime aModifyDate) {
        modifyDate = aModifyDate;
    }

    /**
     * Access method for updateBy.
     *
     * @return the current value of updateBy
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * Setter method for updateBy.
     *
     * @param aUpdateBy the new value for updateBy
     */
    public void setUpdateBy(String aUpdateBy) {
        updateBy = aUpdateBy;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public long getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(long aStatus) {
        status = aStatus;
    }

    /**
     * Access method for account.
     *
     * @return the current value of account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Setter method for account.
     *
     * @param aAccount the new value for account
     */
    public void setAccount(Account aAccount) {
        account = aAccount;
    }

    /**
     * Access method for supplier.
     *
     * @return the current value of supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Setter method for supplier.
     *
     * @param aSupplier the new value for supplier
     */
    public void setSupplier(Supplier aSupplier) {
        supplier = aSupplier;
    }

    /**
     * Compares the key for this instance with another Hdn.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Hdn and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Hdn)) {
            return false;
        }
        Hdn that = (Hdn) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Hdn.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Hdn)) return false;
        return this.equalKeys(other) && ((Hdn)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = (int)(getId() ^ (getId()>>>32));
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Hdn |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
//    public Map<String, Object> getPrimaryKey() {
//        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
//        ret.put("id", Long.valueOf(getId()));
//        return ret;
//    }

}
