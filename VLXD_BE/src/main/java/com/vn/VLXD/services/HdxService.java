package com.vn.VLXD.services;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.HdnRequest;
import com.vn.VLXD.dto.request.HdxRequest;
import com.vn.VLXD.dto.request.SupplierRequest;
import com.vn.VLXD.dto.request.UnitRequest;
import com.vn.VLXD.dto.request.VillageRequest;
import com.vn.VLXD.dto.response.HdxResponse;
import com.vn.VLXD.repositories.HdnRepository;

public interface HdxService {

	ResponseBodyDto<Object> deleteById(Long id);

	ResponseBodyDto<Object> findById(Long id);

	ResponseBodyDto<Object> save(HdxRequest request);

	ResponseBodyDto<Object> findAllSearch(String keySearch, Integer status, Pageable pageable);

	HdxResponse findByIdHdx(Long id);

}
