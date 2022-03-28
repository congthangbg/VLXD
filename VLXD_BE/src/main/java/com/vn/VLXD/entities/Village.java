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

import com.sun.istack.Nullable;

@Entity
@Table(name="VILLAGE")
public class Village implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="VILLAGE_NAME", length=200)
    private String villageName;
    @Column(name="CREATE_DATE")
    private Timestamp createDate;
    @Column(name="CREATE_BY", length=100)
    private String createBy;
    @Column(name="MODIFY_DATE")
    private Timestamp modifyDate;
    @Column(name="UPDATE_BY", length=100)
    private String updateBy;
    @Column(name="STATUS", precision=19)
    @Nullable
    private Integer status = 1;
    
    

    public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/** Default constructor. */
    public Village() {
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
     * Access method for villageName.
     *
     * @return the current value of villageName
     */
    public String getVillageName() {
        return villageName;
    }

    /**
     * Setter method for villageName.
     *
     * @param aVillageName the new value for villageName
     */
    public void setVillageName(String aVillageName) {
        villageName = aVillageName;
    }


    /**
     * Compares the key for this instance with another Village.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Village and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Village)) {
            return false;
        }
        Village that = (Village) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Village.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Village)) return false;
        return this.equalKeys(other) && ((Village)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Village |");
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
