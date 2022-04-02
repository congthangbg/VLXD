// Generated with g9.

package com.vn.VLXD.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.HdxCt;
import com.vn.VLXD.entities.HdxCtTon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HdxResponse implements Serializable {
    private long id;
    private String code;
    private double totalMoney;
    private String totalBill;
    private LocalDateTime releaseDate;
    private double owe;
    private Double pay;
    private long status;
    private List<HdxCt> hdxCt;
    private List<HdxCtTon> hdxCtTon;
    private Customer customer;


}
