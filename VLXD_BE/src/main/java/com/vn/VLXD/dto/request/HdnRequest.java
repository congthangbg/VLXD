// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.VLXD.entities.HdnCt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class HdnRequest implements Serializable {
    private long id;
    private long dateAdded;
    private long status;
    
    private List<HdnCtRequest> hdnCt;
    private long supplierId;

    /** Default constructor. */
    public HdnRequest() {
        super();
    }


}
