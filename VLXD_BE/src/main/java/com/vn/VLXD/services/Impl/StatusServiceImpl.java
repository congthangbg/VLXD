package com.vn.VLXD.services.Impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.VLXD.common.ResponseBase;
import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.request.StatusRequest;
import com.vn.VLXD.dto.request.SupplierRequest;
import com.vn.VLXD.dto.request.VillageRequest;
import com.vn.VLXD.entities.Status;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.StatusRepository;
import com.vn.VLXD.repositories.SupplierRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.StatusService;
import com.vn.VLXD.services.SupplierService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.services.VillageService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusRepository repository;

	@Override
	public ResponseBodyDto<Object> save(StatusRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Status status = MapperUtils.map(request, Status.class);
			Status status2=  repository.save(status);
			dto.setData(status2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Status> optional = repository.findById(request.getId());
			if(optional.isPresent()) {
				Status status = optional.get();
				if(request.getName() != null) {
					status.setName(request.getName());
				}
				Status status2 = repository.save(status);
				dto.setData(status2);
				dto.setMessage(MessageConstant.MSG_OK);
				dto.setMessageCode(MessageConstant.MSG_OK_CODE);
			}else {
				dto.setMessage(MessageConstant.MSG_10);
				dto.setMessageCode(MessageConstant.MSG_10_CODE);
			}
		}
		return dto ;
	}

	@Override
	public ResponseBodyDto<Object> findAllSearch(String keySearch,Pageable pageable) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Page<Status> page = repository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Status> optional = repository.findById(id);
		if(optional.isPresent()) {
			dto.setData(optional.get());
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> deleteById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Status> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	
	
}
