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
    /**
	 * 
	 */
	private static final long serialVersionUID = 4692466542874550849L;
	private long id;
    private Timestamp releaseDate;
    private double owe;
    private long status;
    private List<HdxCtRequest> hdxCtRequest;
    private List<HdxCtTonRequest> hdxCtTonRequest;
    private long customerId;
    private List<PayRequest> payRequest;

    /** Default constructor. */
    public HdxRequest() {
        super();
    }


}
