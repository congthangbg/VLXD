// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;
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

public class UnitRequest implements Serializable {
   
    private long id;
    private String unitName;

    /** Default constructor. */
    public UnitRequest() {
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
     * Access method for unitName.
     *
     * @return the current value of unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Setter method for unitName.
     *
     * @param aUnitName the new value for unitName
     */
    public void setUnitName(String aUnitName) {
        unitName = aUnitName;
    }

  
}
