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
import com.vn.VLXD.dto.request.SupplierRequest;
import com.vn.VLXD.dto.request.UnitRequest;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Unit;
import com.vn.VLXD.repositories.ProductRepository;
import com.vn.VLXD.repositories.SupplierRepository;
import com.vn.VLXD.repositories.UnitRepository;
import com.vn.VLXD.services.SupplierService;
import com.vn.VLXD.services.UnitService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class UnitServiceImpl implements UnitService{

	@Autowired
	UnitRepository repository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseBodyDto<Object> save(UnitRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Unit u = MapperUtils.map(request, Unit.class);
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(LocalDateTime.now());
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(LocalDateTime.now());
			Unit u2=  repository.save(u);
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Unit> optional = repository.findById(request.getId());
			if(optional.isPresent()) {
				Unit u = optional.get();
				if(request.getUnitName() != null) {
					u.setUnitName(request.getUnitName());
				}
				u.setUpdateBy(UserLogonService.getUsername());
				u.setModifyDate(LocalDateTime.now());
				
				Unit u1 = repository.save(u);
				dto.setData(u1);
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
		Page<Unit> page = repository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Unit> optional = repository.findById(id);
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
		Optional<Unit> optional = repository.findById(id);
		if(optional.isPresent()) {
			List<Long> lstIdUnitInProd = productRepository.lstIdUnit(optional.get());
			if(lstIdUnitInProd.contains(optional.get().getId())) {
				dto.setMessage(MessageConstant.MSG_16);
				dto.setMessageCode(MessageConstant.MSG_16_CODE);
			}else {
				optional.get().setStatus(0);
				repository.save(optional.get());
				dto.setMessage(MessageConstant.MSG_OK);
				dto.setMessageCode(MessageConstant.MSG_OK_CODE);
			}
			
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	
	
}
