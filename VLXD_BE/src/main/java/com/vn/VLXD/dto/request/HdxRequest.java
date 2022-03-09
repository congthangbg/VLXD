// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class HdxRequest implements Serializable {
    private long id;
    private LocalDateTime releaseDate;
    private double owe;
    private long status;
    private Set<HdxCtRequest> hdxCtRequest;
    private Set<HdxCtTonRequest> hdxCtTonRequest;
    private long customerId;
    private Set<PayRequest> payRequest;

    /** Default constructor. */
    public HdxRequest() {
        super();
    }


}
