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
import com.vn.VLXD.entities.Account;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.Hdx;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.AccountRepository;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.repositories.HdxRepository;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.AccountService;
import com.vn.VLXD.services.CustomerService;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository repository;
	
	@Autowired
	VillageRepository villageRepository;
	
	@Autowired
	HdxRepository hdxRepository;



	@Override
	public ResponseBodyDto<Object> findAllSearch(Pageable pageable) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Page<Account> page = repository.findAll(pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Account> optional = repository.findById(id);
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

	public ResponseBodyDto<Object> deleteById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Account> optional = repository.findById(id);
		if(optional.isPresent()) {
			List<Long> hdx = hdxRepository.findListIdCustomer();
			if(hdx.contains(id)) {
				dto.setMessage(MessageConstant.MSG_12);
				dto.setMessageCode(MessageConstant.MSG_12_CODE);
			}else {
				Account cus = optional.get();
				repository.save(cus);
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
