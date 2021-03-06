package com.vn.VLXD.services;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.SupplierRequest;

public interface SupplierService {

	ResponseBodyDto<Object> deleteById(Long id);

	ResponseBodyDto<Object> findById(Long id);

	ResponseBodyDto<Object> findAllSearch(String keySearch, Pageable pageable);

	ResponseBodyDto<Object> save(SupplierRequest request);

}
