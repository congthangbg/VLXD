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

@Entity
@Table(name="HDX")
public class Hdx implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
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
    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
    private Set<HdxCt> hdxCt;
    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
    private Set<HdxCtTon> hdxCtTon;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @OneToMany(mappedBy="hdx",fetch = FetchType.LAZY)
    private Set<Pay> pay;

    /** Default constructor. */
    public Hdx() {
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
     * Access method for releaseDate.
     *
     * @return the current value of releaseDate
     */
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    /**
     * Setter method for releaseDate.
     *
     * @param aReleaseDate the new value for releaseDate
     */
    public void setReleaseDate(LocalDateTime aReleaseDate) {
        releaseDate = aReleaseDate;
    }

    /**
     * Access method for owe.
     *
     * @return the current value of owe
     */
    public double getOwe() {
        return owe;
    }

    /**
     * Setter method for owe.
     *
     * @param aOwe the new value for owe
     */
    public void setOwe(double aOwe) {
        owe = aOwe;
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
     * Access method for hdxCt.
     *
     * @return the current value of hdxCt
     */
    public Set<HdxCt> getHdxCt() {
        return hdxCt;
    }

    /**
     * Setter method for hdxCt.
     *
     * @param aHdxCt the new value for hdxCt
     */
    public void setHdxCt(Set<HdxCt> aHdxCt) {
        hdxCt = aHdxCt;
    }

    /**
     * Access method for hdxCtTon.
     *
     * @return the current value of hdxCtTon
     */
    public Set<HdxCtTon> getHdxCtTon() {
        return hdxCtTon;
    }

    /**
     * Setter method for hdxCtTon.
     *
     * @param aHdxCtTon the new value for hdxCtTon
     */
    public void setHdxCtTon(Set<HdxCtTon> aHdxCtTon) {
        hdxCtTon = aHdxCtTon;
    }

    /**
     * Access method for customer.
     *
     * @return the current value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Setter method for customer.
     *
     * @param aCustomer the new value for customer
     */
    public void setCustomer(Customer aCustomer) {
        customer = aCustomer;
    }

    /**
     * Access method for pay.
     *
     * @return the current value of pay
     */
    public Set<Pay> getPay() {
        return pay;
    }

    /**
     * Setter method for pay.
     *
     * @param aPay the new value for pay
     */
    public void setPay(Set<Pay> aPay) {
        pay = aPay;
    }

    /**
     * Compares the key for this instance with another Hdx.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Hdx and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Hdx)) {
            return false;
        }
        Hdx that = (Hdx) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Hdx.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Hdx)) return false;
        return this.equalKeys(other) && ((Hdx)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Hdx |");
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