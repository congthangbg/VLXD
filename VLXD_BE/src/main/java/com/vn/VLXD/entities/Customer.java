//// Generated with g9.
//
//package com.vn.VLXD.entities;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Version;
//
//@Entity(name="CUSTOMER")
//public class Customer implements Serializable {
//
//    /** Primary key. */
//    protected static final String PK = "id";
//
//    /**
//     * The optimistic lock. Available via standard bean get/set operations.
//     */
//    @Version
//    @Column(name="LOCK_FLAG")
//    private Integer lockFlag;
//
//    /**
//     * Access method for the lockFlag property.
//     *
//     * @return the current value of the lockFlag property
//     */
//    public Integer getLockFlag() {
//        return lockFlag;
//    }
//
//    /**
//     * Sets the value of the lockFlag property.
//     *
//     * @param aLockFlag the new value of the lockFlag property
//     */
//    public void setLockFlag(Integer aLockFlag) {
//        lockFlag = aLockFlag;
//    }
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name="ID", unique=true, nullable=false, precision=19)
//    private long id;
//    @Column(name="NAME", length=200)
//    private String name;
//    @Column(name="PHONE", precision=10)
//    private int phone;
//    @Column(name="ADDRESS", length=500)
//    private String address;
//    @Column(name="CREATE_DATE")
//    private LocalDateTime createDate;
//    @Column(name="CREATE_BY", length=100)
//    private String createBy;
//    @Column(name="MODIFY_DATE")
//    private LocalDateTime modifyDate;
//    @Column(name="UPDATE_BY", length=100)
//    private String updateBy;
//    @Column(name="STATUS", precision=19)
//    private long status;
//    @ManyToOne
//    @JoinColumn(name="VILLAGE_ID")
//    private Village village;
//    @OneToMany(mappedBy="customer")
//    private Set<Hdx> hdx;
//    @OneToMany(mappedBy="customer")
//    private Set<Pay> pay;
//
//    /** Default constructor. */
//    public Customer() {
//        super();
//    }
//
//    /**
//     * Access method for id.
//     *
//     * @return the current value of id
//     */
//    public long getId() {
//        return id;
//    }
//
//    /**
//     * Setter method for id.
//     *
//     * @param aId the new value for id
//     */
//    public void setId(long aId) {
//        id = aId;
//    }
//
//    /**
//     * Access method for name.
//     *
//     * @return the current value of name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Setter method for name.
//     *
//     * @param aName the new value for name
//     */
//    public void setName(String aName) {
//        name = aName;
//    }
//
//    /**
//     * Access method for phone.
//     *
//     * @return the current value of phone
//     */
//    public int getPhone() {
//        return phone;
//    }
//
//    /**
//     * Setter method for phone.
//     *
//     * @param aPhone the new value for phone
//     */
//    public void setPhone(int aPhone) {
//        phone = aPhone;
//    }
//
//    /**
//     * Access method for address.
//     *
//     * @return the current value of address
//     */
//    public String getAddress() {
//        return address;
//    }
//
//    /**
//     * Setter method for address.
//     *
//     * @param aAddress the new value for address
//     */
//    public void setAddress(String aAddress) {
//        address = aAddress;
//    }
//
//    /**
//     * Access method for createDate.
//     *
//     * @return the current value of createDate
//     */
//    public LocalDateTime getCreateDate() {
//        return createDate;
//    }
//
//    /**
//     * Setter method for createDate.
//     *
//     * @param aCreateDate the new value for createDate
//     */
//    public void setCreateDate(LocalDateTime aCreateDate) {
//        createDate = aCreateDate;
//    }
//
//    /**
//     * Access method for createBy.
//     *
//     * @return the current value of createBy
//     */
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    /**
//     * Setter method for createBy.
//     *
//     * @param aCreateBy the new value for createBy
//     */
//    public void setCreateBy(String aCreateBy) {
//        createBy = aCreateBy;
//    }
//
//    /**
//     * Access method for modifyDate.
//     *
//     * @return the current value of modifyDate
//     */
//    public LocalDateTime getModifyDate() {
//        return modifyDate;
//    }
//
//    /**
//     * Setter method for modifyDate.
//     *
//     * @param aModifyDate the new value for modifyDate
//     */
//    public void setModifyDate(LocalDateTime aModifyDate) {
//        modifyDate = aModifyDate;
//    }
//
//    /**
//     * Access method for updateBy.
//     *
//     * @return the current value of updateBy
//     */
//    public String getUpdateBy() {
//        return updateBy;
//    }
//
//    /**
//     * Setter method for updateBy.
//     *
//     * @param aUpdateBy the new value for updateBy
//     */
//    public void setUpdateBy(String aUpdateBy) {
//        updateBy = aUpdateBy;
//    }
//
//    /**
//     * Access method for status.
//     *
//     * @return the current value of status
//     */
//    public long getStatus() {
//        return status;
//    }
//
//    /**
//     * Setter method for status.
//     *
//     * @param aStatus the new value for status
//     */
//    public void setStatus(long aStatus) {
//        status = aStatus;
//    }
//
//    /**
//     * Access method for village.
//     *
//     * @return the current value of village
//     */
//    public Village getVillage() {
//        return village;
//    }
//
//    /**
//     * Setter method for village.
//     *
//     * @param aVillage the new value for village
//     */
//    public void setVillage(Village aVillage) {
//        village = aVillage;
//    }
//
//    /**
//     * Access method for hdx.
//     *
//     * @return the current value of hdx
//     */
//    public Set<Hdx> getHdx() {
//        return hdx;
//    }
//
//    /**
//     * Setter method for hdx.
//     *
//     * @param aHdx the new value for hdx
//     */
//    public void setHdx(Set<Hdx> aHdx) {
//        hdx = aHdx;
//    }
//
//    /**
//     * Access method for pay.
//     *
//     * @return the current value of pay
//     */
//    public Set<Pay> getPay() {
//        return pay;
//    }
//
//    /**
//     * Setter method for pay.
//     *
//     * @param aPay the new value for pay
//     */
//    public void setPay(Set<Pay> aPay) {
//        pay = aPay;
//    }
//
//    /**
//     * Compares the key for this instance with another Customer.
//     *
//     * @param other The object to compare to
//     * @return True if other object is instance of class Customer and the key objects are equal
//     */
//    private boolean equalKeys(Object other) {
//        if (this==other) {
//            return true;
//        }
//        if (!(other instanceof Customer)) {
//            return false;
//        }
//        Customer that = (Customer) other;
//        if (this.getId() != that.getId()) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Compares this instance with another Customer.
//     *
//     * @param other The object to compare to
//     * @return True if the objects are the same
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (!(other instanceof Customer)) return false;
//        return this.equalKeys(other) && ((Customer)other).equalKeys(this);
//    }
//
//    /**
//     * Returns a hash code for this instance.
//     *
//     * @return Hash code
//     */
//    @Override
//    public int hashCode() {
//        int i;
//        int result = 17;
//        i = (int)(getId() ^ (getId()>>>32));
//        result = 37*result + i;
//        return result;
//    }
//
//    /**
//     * Returns a debug-friendly String representation of this instance.
//     *
//     * @return String representation of this instance
//     */
//    @Override
//    public String toString() {
//        StringBuffer sb = new StringBuffer("[Customer |");
//        sb.append(" id=").append(getId());
//        sb.append("]");
//        return sb.toString();
//    }
//
//    /**
//     * Return all elements of the primary key.
//     *
//     * @return Map of key names to values
//     */
//    public Map<String, Object> getPrimaryKey() {
//        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
//        ret.put("id", Long.valueOf(getId()));
//        return ret;
//    }
//
//}
