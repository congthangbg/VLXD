package com.vn.VLXD.services;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.ProductRequest;
import com.vn.VLXD.dto.request.SupplierRequest;
import com.vn.VLXD.dto.request.UnitRequest;
import com.vn.VLXD.dto.request.VillageRequest;

public interface ProductService {

	ResponseBodyDto<Object> deleteById(Long id);

	ResponseBodyDto<Object> findById(Long id);

	ResponseBodyDto<Object> findAllSearch(String keySearch, Pageable pageable);

	ResponseBodyDto<Object> save(ProductRequest request);

	ResponseBodyDto<Object> findByProductType(Long typeId);

}
