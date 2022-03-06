// Generated with g9.

package com.vn.VLXD.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="NAME", length=200)
    private String name;
    @Column(name="PRICE", precision=53)
    private double price;
    @Column(name="QUANTITY", precision=53)
    private double quantity;
    @Column(name="IMAGE", length=200)
    private String image;
    @Column(name="CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name="CREATE_BY", length=100)
    private String createBy;
    @Column(name="MODIFY_DATE")
    private LocalDateTime modifyDate;
    @Column(name="UPDATE_BY", length=100)
    private String updateBy;
    @Column(name="STATUS", precision=19)
    private long status =1;
   
    @ManyToOne
    @JoinColumn(name="TYPE_ID")
    private ProductType productType;
    
    @ManyToOne
    @JoinColumn(name="UNIT_ID")
    private Unit unit;

    /** Default constructor. */
    public Product() {
        super();
    }

    public Unit getUnit() {
		return unit;
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
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for price.
     *
     * @return the current value of price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method for price.
     *
     * @param aPrice the new value for price
     */
    public void setPrice(double aPrice) {
        price = aPrice;
    }

    /**
     * Access method for quantity.
     *
     * @return the current value of quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Setter method for quantity.
     *
     * @param aQuantity the new value for quantity
     */
    public void setQuantity(double aQuantity) {
        quantity = aQuantity;
    }

    /**
     * Access method for image.
     *
     * @return the current value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method for image.
     *
     * @param aImage the new value for image
     */
    public void setImage(String aImage) {
        image = aImage;
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

    
    public void setUnit(Unit aUnit) {
        unit = aUnit;
    }

    /**
     * Access method for productType.
     *
     * @return the current value of productType
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Setter method for productType.
     *
     * @param aProductType the new value for productType
     */
    public void setProductType(ProductType aProductType) {
        productType = aProductType;
    }

    /**
     * Compares the key for this instance with another Product.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Product and the key objects are equal
     */
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


}
