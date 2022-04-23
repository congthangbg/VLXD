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
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.repositories.SupplierRepository;
import com.vn.VLXD.services.SupplierService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService{

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public ResponseBodyDto<Object> save(SupplierRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Supplier supplier = MapperUtils.map(request, Supplier.class);
			supplier.setCreateBy(UserLogonService.getUsername());
			supplier.setCreateDate(Timestamp.from(Instant.now()));
			supplier.setUpdateBy(UserLogonService.getUsername());
			supplier.setModifyDate(Timestamp.from(Instant.now()));
			Supplier supplier2=  supplierRepository.save(supplier);
			dto.setData(supplier2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Supplier> optional = supplierRepository.findById(request.getId());
			if(optional.isPresent()) {
				Supplier supplier = optional.get();
				if(request.getAddress() != null) {
					supplier.setAddress(request.getAddress());
				}
				if(request.getName()!= null) {
					supplier.setName(request.getName());
				}
				if(request.getPhone() != null) {
					supplier.setPhone(request.getPhone());
				}
				supplier.setUpdateBy(UserLogonService.getUsername());
				supplier.setModifyDate(new Timestamp(System.currentTimeMillis()));
				
				Supplier supplier1 = supplierRepository.save(supplier);
				dto.setData(supplier1);
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
		Page<Supplier> page = supplierRepository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Supplier> optional = supplierRepository.findById(id);
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
		Optional<Supplier> optional = supplierRepository.findById(id);
		if(optional.isPresent()) {
			supplierRepository.delete(optional.get());
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	
	
}
