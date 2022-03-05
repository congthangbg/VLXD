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
import javax.persistence.Version;

import lombok.Data;

@Data
public class SupplierRequest implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String address;
//    private Set<Hdn> hdn;

    public SupplierRequest() {
        super();
    }


}
