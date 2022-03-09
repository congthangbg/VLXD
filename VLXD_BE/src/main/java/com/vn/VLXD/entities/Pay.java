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
import javax.persistence.Version;

@Entity(name="PAY")
public class Pay implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="PAY_DAY")
    private LocalDateTime payDay;
    @Column(name="PAY_AMOUNT", precision=53)
    private double payAmount;
    @Column(name="TOTAL_MONEY_HDX", precision=53)
    private double totalMoneyHdx;
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
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="HDX_ID")
    private Hdx hdx;

    /** Default constructor. */
    public Pay() {
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
     * Access method for payDay.
     *
     * @return the current value of payDay
     */
    public LocalDateTime getPayDay() {
        return payDay;
    }

    /**
     * Setter method for payDay.
     *
     * @param aPayDay the new value for payDay
     */
    public void setPayDay(LocalDateTime aPayDay) {
        payDay = aPayDay;
    }

    /**
     * Access method for payAmount.
     *
     * @return the current value of payAmount
     */
    public double getPayAmount() {
        return payAmount;
    }

    /**
     * Setter method for payAmount.
     *
     * @param aPayAmount the new value for payAmount
     */
    public void setPayAmount(double aPayAmount) {
        payAmount = aPayAmount;
    }

    /**
     * Access method for totalMoneyHdx.
     *
     * @return the current value of totalMoneyHdx
     */
    public double getTotalMoneyHdx() {
        return totalMoneyHdx;
    }

    /**
     * Setter method for totalMoneyHdx.
     *
     * @param aTotalMoneyHdx the new value for totalMoneyHdx
     */
    public void setTotalMoneyHdx(double aTotalMoneyHdx) {
        totalMoneyHdx = aTotalMoneyHdx;
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
     * Access method for hdx.
     *
     * @return the current value of hdx
     */
    public Hdx getHdx() {
        return hdx;
    }

    /**
     * Setter method for hdx.
     *
     * @param aHdx the new value for hdx
     */
    public void setHdx(Hdx aHdx) {
        hdx = aHdx;
    }

    /**
     * Compares the key for this instance with another Pay.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Pay and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Pay)) {
            return false;
        }
        Pay that = (Pay) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Pay.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Pay)) return false;
        return this.equalKeys(other) && ((Pay)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Pay |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Long.valueOf(getId()));
        return ret;
    }

}
