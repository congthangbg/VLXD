package com.vn.VLXD.services;

import org.springframework.data.domain.Pageable;

import com.vn.VLXD.common.ResponseBodyDto;

public interface AccountService {

	ResponseBodyDto<Object> findAllSearch(String keySearch,Pageable pageable);


}
