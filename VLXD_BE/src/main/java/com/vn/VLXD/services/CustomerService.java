package com.vn.VLXD.services;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.CustomerRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;

public interface CustomerService {

	ResponseBodyDto<Object> deleteById(Long id);

	ResponseBodyDto<Object> findById(Long id);

	ResponseBodyDto<Object> findAllSearch(String keySearch, Pageable pageable);

	ResponseBodyDto<Object> save(CustomerRequest request);

}
