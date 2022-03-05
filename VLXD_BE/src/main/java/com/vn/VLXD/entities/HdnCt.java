//// Generated with g9.
//
//package com.vn.VLXD.entities;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Version;
//
//@Entity(name="HDN_CT")
//public class HdnCt implements Serializable {
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
//    @Column(name="PRODUCT_ID", precision=19)
//    private long productId;
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
//    @JoinColumn(name="HDN_ID")
//    private Hdn hdn;
//    @OneToOne(optional=false, mappedBy="hdnCt")
//    @JoinColumn(name="ID", nullable=false)
//    private Product product;
//
//    /** Default constructor. */
//    public HdnCt() {
//        super();
//    }
//
//    /**
//     * Access method for productId.
//     *
//     * @return the current value of productId
//     */
//    public long getProductId() {
//        return productId;
//    }
//
//    /**
//     * Setter method for productId.
//     *
//     * @param aProductId the new value for productId
//     */
//    public void setProductId(long aProductId) {
//        productId = aProductId;
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
//     * Access method for hdn.
//     *
//     * @return the current value of hdn
//     */
//    public Hdn getHdn() {
//        return hdn;
//    }
//
//    /**
//     * Setter method for hdn.
//     *
//     * @param aHdn the new value for hdn
//     */
//    public void setHdn(Hdn aHdn) {
//        hdn = aHdn;
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
//}
