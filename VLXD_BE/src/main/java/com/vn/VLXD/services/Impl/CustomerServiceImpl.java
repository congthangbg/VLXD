package com.vn.VLXD.services.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.request.CustomerRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.CustomerService;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	VillageRepository villageRepository;

	@Override
	public ResponseBodyDto<Object> save(CustomerRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Customer u = MapperUtils.map(request, Customer.class);
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(LocalDateTime.now());
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(LocalDateTime.now());
			
			Optional<Village> optional2 = villageRepository.findById(request.getVillageId());
			if(optional2.isPresent()) {
				u.setVillage(optional2.get());
			}
			Customer u2=  repository.save(u);
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Customer> optional = repository.findById(request.getId());
			if(optional.isPresent()) {
				Customer u = optional.get();
				if(request.getName() != null) {
					u.setName(request.getName());	
					}
				if(request.getPhone() != null) {
					u.setPhone(request.getPhone());	
					}
				if(request.getAddress() != null) {
					u.setAddress(request.getAddress());	
					}
				Optional<Village> optional2 = villageRepository.findById(request.getVillageId());
				if(optional2.isPresent()) {
					u.setVillage(optional2.get());
				}
				u.setUpdateBy(UserLogonService.getUsername());
				u.setModifyDate(LocalDateTime.now());
				
				Customer u1 = repository.save(u);
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
		Page<Customer> page = repository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Customer> optional = repository.findById(id);
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
		Optional<Customer> optional = repository.findById(id);
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

	@Override
	public ResponseBodyDto<Object> findAllTest(String Text, Integer page, Integer size, String orderBy) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		try {
			List<Customer> list = repository.findAllTest(Text, page, size, orderBy);
			Integer count = repository.CustomerCount(Text, page, size, orderBy);
			dto.setData(list);
			dto.setTotalRecords(count);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
