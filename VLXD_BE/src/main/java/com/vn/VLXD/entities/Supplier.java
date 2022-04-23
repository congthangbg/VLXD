// Generated with g9.

package com.vn.VLXD.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "SUPPLIER")
public class Supplier implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="NAME", length=500)
    private String name;
    @Column(name="PHONE", nullable=false, length=50)
    private String phone;
    @Column(name="ADDRESS", length=500)
    private String address;
    @Column(name="CREATE_DATE")
    private Timestamp createDate;
    @Column(name="CREATE_BY", length=100)
    private String createBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(name="UPDATE_BY", nullable=true, length=100)
    private String updateBy;
    @Column(name="STATUS",nullable = true)
    private long status =1 ;
//    @OneToMany(mappedBy="supplier")
//    private Set<Hdn> hdn;

    /** Default constructor. */
    public Supplier() {
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
     * Access method for phone.
     *
     * @return the current value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method for phone.
     *
     * @param aPhone the new value for phone
     */
    public void setPhone(String aPhone) {
        phone = aPhone;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for createDate.
     *
     * @return the current value of createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for createDate.
     *
     * @param aCreateDate the new value for createDate
     */
    public void setCreateDate(Timestamp aCreateDate) {
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
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    /**
     * Setter method for modifyDate.
     *
     * @param aModifyDate the new value for modifyDate
     */
    public void setModifyDate(Timestamp aModifyDate) {
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
     * Compares the key for this instance with another Supplier.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Supplier and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Supplier)) {
            return false;
        }
        Supplier that = (Supplier) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Supplier.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Supplier)) return false;
        return this.equalKeys(other) && ((Supplier)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Supplier |");
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
