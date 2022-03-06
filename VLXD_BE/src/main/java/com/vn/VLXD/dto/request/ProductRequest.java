// Generated with g9.

package com.vn.VLXD.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {

    private long id;
    private String name;
    private double price;
    private double quantity;
    private String image;
    private long unitId;
    private long typeId;


}
