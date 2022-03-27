package com.vn.VLXD.services.Impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	ProductTypeRepository repository;

	@Override
	public ResponseBodyDto<Object> save(ProductTypeRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			ProductType u = MapperUtils.map(request, ProductType.class);
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(new Timestamp(System.currentTimeMillis()));
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(new Timestamp(System.currentTimeMillis()));
			ProductType u2=  repository.save(u);
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<ProductType> optional = repository.findById(request.getId());
			if(optional.isPresent()) {
				ProductType u = optional.get();
				if(request.getTypeName() != null) {
					u.setTypeName(request.getTypeName());	
					}
				u.setUpdateBy(UserLogonService.getUsername());
				u.setModifyDate(new Timestamp(System.currentTimeMillis()));
				
				ProductType u1 = repository.save(u);
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
		Page<ProductType> page = repository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<ProductType> optional = repository.findById(id);
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
		Optional<ProductType> optional = repository.findById(id);
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
