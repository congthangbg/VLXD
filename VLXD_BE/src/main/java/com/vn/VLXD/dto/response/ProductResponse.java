// Generated with g9.

package com.vn.VLXD.dto.response;

import java.io.Serializable;

import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse implements Serializable {

    private long id;
    private String name;
    private double price;
    private double quantity;
    private String image;
    private Unit unit;
    private ProductType productType;


}
