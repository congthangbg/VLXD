package com.vn.VLXD.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.CustomerRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.entities.Customer;

public interface CustomerService {

	ResponseBodyDto<Object> deleteById(Long id);

	ResponseBodyDto<Object> findById(Long id);

	ResponseBodyDto<Object> findAllSearch(String keySearch, Pageable pageable);

	ResponseBodyDto<Object> save(CustomerRequest request);

	ResponseBodyDto<Object> findAllTest(String Text, Integer page, Integer size, String orderBy);

}
