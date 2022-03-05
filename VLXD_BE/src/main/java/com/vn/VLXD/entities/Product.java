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
//import javax.persistence.OneToOne;
//import javax.persistence.Version;
//
//@Entity(name="PRODUCT")
//public class Product implements Serializable {
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
//    @Column(name="PRICE", precision=53)
//    private double price;
//    @Column(name="QUANTITY", precision=53)
//    private double quantity;
//    @Column(name="IMAGE", length=200)
//    private String image;
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
//    @OneToOne(mappedBy="product")
//    private HdnCt hdnCt;
//    @OneToMany(mappedBy="product")
//    private Set<HdxCt> hdxCt;
//    @OneToMany(mappedBy="product")
//    private Set<HdxCtTon> hdxCtTon;
//    @ManyToOne
//    @JoinColumn(name="UNIT_ID")
//    private Unit unit;
//    @ManyToOne
//    @JoinColumn(name="TYPE_ID")
//    private ProductType productType;
//
//    /** Default constructor. */
//    public Product() {
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
//     * Access method for image.
//     *
//     * @return the current value of image
//     */
//    public String getImage() {
//        return image;
//    }
//
//    /**
//     * Setter method for image.
//     *
//     * @param aImage the new value for image
//     */
//    public void setImage(String aImage) {
//        image = aImage;
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
//     * Access method for hdnCt.
//     *
//     * @return the current value of hdnCt
//     */
//    public HdnCt getHdnCt() {
//        return hdnCt;
//    }
//
//    /**
//     * Setter method for hdnCt.
//     *
//     * @param aHdnCt the new value for hdnCt
//     */
//    public void setHdnCt(HdnCt aHdnCt) {
//        hdnCt = aHdnCt;
//    }
//
//    /**
//     * Access method for hdxCt.
//     *
//     * @return the current value of hdxCt
//     */
//    public Set<HdxCt> getHdxCt() {
//        return hdxCt;
//    }
//
//    /**
//     * Setter method for hdxCt.
//     *
//     * @param aHdxCt the new value for hdxCt
//     */
//    public void setHdxCt(Set<HdxCt> aHdxCt) {
//        hdxCt = aHdxCt;
//    }
//
//    /**
//     * Access method for hdxCtTon.
//     *
//     * @return the current value of hdxCtTon
//     */
//    public Set<HdxCtTon> getHdxCtTon() {
//        return hdxCtTon;
//    }
//
//    /**
//     * Setter method for hdxCtTon.
//     *
//     * @param aHdxCtTon the new value for hdxCtTon
//     */
//    public void setHdxCtTon(Set<HdxCtTon> aHdxCtTon) {
//        hdxCtTon = aHdxCtTon;
//    }
//
//    /**
//     * Access method for unit.
//     *
//     * @return the current value of unit
//     */
//    public Unit getUnit() {
//        return unit;
//    }
//
//    /**
//     * Setter method for unit.
//     *
//     * @param aUnit the new value for unit
//     */
//    public void setUnit(Unit aUnit) {
//        unit = aUnit;
//    }
//
//    /**
//     * Access method for productType.
//     *
//     * @return the current value of productType
//     */
//    public ProductType getProductType() {
//        return productType;
//    }
//
//    /**
//     * Setter method for productType.
//     *
//     * @param aProductType the new value for productType
//     */
//    public void setProductType(ProductType aProductType) {
//        productType = aProductType;
//    }
//
//    /**
//     * Compares the key for this instance with another Product.
//     *
//     * @param other The object to compare to
//     * @return True if other object is instance of class Product and the key objects are equal
//     */
//    private boolean equalKeys(Object other) {
//        if (this==other) {
//            return true;
//        }
//        if (!(other instanceof Product)) {
//            return false;
//        }
//        Product that = (Product) other;
//        if (this.getId() != that.getId()) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Compares this instance with another Product.
//     *
//     * @param other The object to compare to
//     * @return True if the objects are the same
//     */
//    @Override
//    public boolean equals(Object other) {
//        if (!(other instanceof Product)) return false;
//        return this.equalKeys(other) && ((Product)other).equalKeys(this);
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
//        StringBuffer sb = new StringBuffer("[Product |");
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
