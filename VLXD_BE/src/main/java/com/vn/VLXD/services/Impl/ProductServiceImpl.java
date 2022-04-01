package com.vn.VLXD.services.Impl;

import java.sql.SQLException;
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
import com.vn.VLXD.dto.request.ProductRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.dto.response.ProductResponse;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.Product;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Unit;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.repositories.ProductRepository;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.repositories.UnitRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.CustomerService;
import com.vn.VLXD.services.ProductService;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductTypeRepository productTypeRepository;
	
	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional(rollbackFor = {SQLException.class})
	public ResponseBodyDto<Object> save(ProductRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Product u = MapperUtils.map(request, Product.class);
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(LocalDateTime.now());
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(LocalDateTime.now());
			
			Optional<ProductType> optional2 = productTypeRepository.findById(request.getTypeId());
			if(optional2.isPresent()) {
				u.setProductType(optional2.get());
			}
			Optional<Unit> optional3 = unitRepository.findById(request.getUnitId());
			if(optional3.isPresent()) {
				u.setUnit(optional3.get());
			}
			
			Product u2=  productRepository.save(u);
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Product> optional = productRepository.findById(request.getId());
			if(optional.isPresent()) {
				Product u = optional.get();
				if(request.getName() != null) {
					u.setName(request.getName());	
					}
				if(request.getImage() != null) {
					u.setImage(request.getImage());	
					}
				if(request.getPrice() != 0) {
					u.setPrice(request.getPrice());	
					}
				if(request.getQuantity() != 0) {
					u.setQuantity(request.getQuantity());	
					}
				
				Optional<ProductType> optional2 = productTypeRepository.findById(request.getTypeId());
				if(optional2.isPresent()) {
					u.setProductType(optional2.get());
				}
				Optional<Unit> optional3 = unitRepository.findById(request.getUnitId());
				if(optional3.isPresent()) {
					u.setUnit(optional3.get());
				}
				u.setUpdateBy(UserLogonService.getUsername());
				u.setModifyDate(LocalDateTime.now());
				
				Product u1 = productRepository.save(u);
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
		Page<Product> page = productRepository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			ProductResponse res = MapperUtils.map(optional.get(), ProductResponse.class);
			dto.setData(res);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	@Override
	public ResponseBodyDto<Object> findByProductType(Long typeId) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<ProductType> optional = productTypeRepository.findById(typeId);
		if(optional.isPresent()) {
			List<Product> list = productRepository.findByProductType(optional.get());
			dto.setData(list);
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
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			productRepository.deleteById(id);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	
	
}
