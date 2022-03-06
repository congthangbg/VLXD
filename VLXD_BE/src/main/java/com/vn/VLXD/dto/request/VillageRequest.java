// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;
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

public class VillageRequest implements Serializable {
    private long id;
    private String villageName;

    public VillageRequest() {
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



}
