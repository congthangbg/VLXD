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
//import javax.persistence.OneToMany;
//import javax.persistence.Version;
//
//@Entity(name="PRODUCT_TYPE")
//public class ProductType implements Serializable {
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
//    @Column(name="TYPE_NAME", length=100)
//    private String typeName;
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
//    @OneToMany(mappedBy="productType")
//    private Set<Product> product;
//
//    /** Default constructor. */
//    public ProductType() {
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
//     * Access method for typeName.
//     *
//     * @return the current value of typeName
//     */
//    public String getTypeName() {
//        return typeName;
//    }
//
//    /**
//     * Setter method for typeName.
//     *
//     * @param aTypeName the new value for typeName
//     */
//    public void setTypeName(String aTypeName) {
//        typeName = aTypeName;
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
//     * Access method for product.
//     *
//     * @return the current value of product
//     */
//    public Set<Product> getProduct() {
//        return product;
//    }
//
//    /**
//     * Setter method for product.
//     *
//     * @param aProduct the new value for product
//     */
//    public void setProduct(Set<Product> aProduct) {
//        product = aProduct;
//    }
//
//    /**
//     * Compares the key for this instance with another ProductType.
//     *
//     * @param other The object to compare to
//     * @return True if other object is instance of class ProductType and the key objects are equal
//     */
//    private boolean equalKeys(Object other) {
//        if (this==other) {
//            return true;
//        }
//        if (!(other instanceof ProductType)) {
//            return false;
//        }
//        ProductType that = (ProductType) other;
//        if (this.getId() != that.getId()) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Compares this instance with another ProductType.
//     *
//     * @param other The object to compare to
//     * @return True if the objects are the same
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (!(other instanceof ProductType)) return false;
//        return this.equalKeys(other) && ((ProductType)other).equalKeys(this);
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
//        StringBuffer sb = new StringBuffer("[ProductType |");
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
