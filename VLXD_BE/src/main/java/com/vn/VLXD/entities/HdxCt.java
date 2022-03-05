//// Generated with g9.
//
//package com.vn.VLXD.entities;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Version;
//
//@Entity(name="HDX_CT")
//public class HdxCt implements Serializable {
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
//    @Column(name="QUANTITY", precision=53)
//    private double quantity;
//    @Column(name="PRICE", precision=53)
//    private double price;
//    @Column(name="NOTE", length=500)
//    private String note;
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
//    @JoinColumn(name="HDX_ID")
//    private Hdx hdx;
//    @ManyToOne
//    @JoinColumn(name="PRODUCT_ID")
//    private Product product;
//
//    /** Default constructor. */
//    public HdxCt() {
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
//     * Access method for quantity.
//     *
//     * @return the current value of quantity
//     */
//    public double getQuantity() {
//        return quantity;
//    }
//
//    /**
//     * Setter method for quantity.
//     *
//     * @param aQuantity the new value for quantity
//     */
//    public void setQuantity(double aQuantity) {
//        quantity = aQuantity;
//    }
//
//    /**
//     * Access method for price.
//     *
//     * @return the current value of price
//     */
//    public double getPrice() {
//        return price;
//    }
//
//    /**
//     * Setter method for price.
//     *
//     * @param aPrice the new value for price
//     */
//    public void setPrice(double aPrice) {
//        price = aPrice;
//    }
//
//    /**
//     * Access method for note.
//     *
//     * @return the current value of note
//     */
//    public String getNote() {
//        return note;
//    }
//
//    /**
//     * Setter method for note.
//     *
//     * @param aNote the new value for note
//     */
//    public void setNote(String aNote) {
//        note = aNote;
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
//     * Access method for hdx.
//     *
//     * @return the current value of hdx
//     */
//    public Hdx getHdx() {
//        return hdx;
//    }
//
//    /**
//     * Setter method for hdx.
//     *
//     * @param aHdx the new value for hdx
//     */
//    public void setHdx(Hdx aHdx) {
//        hdx = aHdx;
//    }
//
//    /**
//     * Access method for product.
//     *
//     * @return the current value of product
//     */
//    public Product getProduct() {
//        return product;
//    }
//
//    /**
//     * Setter method for product.
//     *
//     * @param aProduct the new value for product
//     */
//    public void setProduct(Product aProduct) {
//        product = aProduct;
//    }
//
//    /**
//     * Compares the key for this instance with another HdxCt.
//     *
//     * @param other The object to compare to
//     * @return True if other object is instance of class HdxCt and the key objects are equal
//     */
//    private boolean equalKeys(Object other) {
//        if (this==other) {
//            return true;
//        }
//        if (!(other instanceof HdxCt)) {
//            return false;
//        }
//        HdxCt that = (HdxCt) other;
//        if (this.getId() != that.getId()) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Compares this instance with another HdxCt.
//     *
//     * @param other The object to compare to
//     * @return True if the objects are the same
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (!(other instanceof HdxCt)) return false;
//        return this.equalKeys(other) && ((HdxCt)other).equalKeys(this);
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
//        StringBuffer sb = new StringBuffer("[HdxCt |");
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
