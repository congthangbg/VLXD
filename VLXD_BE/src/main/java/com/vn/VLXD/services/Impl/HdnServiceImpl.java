package com.vn.VLXD.services.Impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.Constants;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.request.CustomerRequest;
import com.vn.VLXD.dto.request.HdnCtRequest;
import com.vn.VLXD.dto.request.HdnRequest;
import com.vn.VLXD.dto.request.ProductRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.dto.response.ProductResponse;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.Hdn;
import com.vn.VLXD.entities.HdnCt;
import com.vn.VLXD.entities.Product;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Unit;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.repositories.HdnCtRepository;
import com.vn.VLXD.repositories.HdnRepository;
import com.vn.VLXD.repositories.ProductRepository;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.repositories.SupplierRepository;
import com.vn.VLXD.repositories.UnitRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.CustomerService;
import com.vn.VLXD.services.HdnService;
import com.vn.VLXD.services.ProductService;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class HdnServiceImpl implements HdnService{

	@Autowired
	ProductTypeRepository productTypeRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	HdnRepository hdnRepository;
	
	@Autowired
	HdnCtRepository hdnCtRepository;

	@Override
	@Transactional
	public ResponseBodyDto<Object> save(HdnRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Hdn u = MapperUtils.map(request, Hdn.class);
			u.setDateAdded(new Timestamp(request.getDateAdded()).toLocalDateTime());
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(LocalDateTime.now());
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(LocalDateTime.now());
			u.setStatus(Constants.ChuaThanhToan);
			u.setAccount(UserLogonService.currentUser());
			Optional<Supplier> optional2 = supplierRepository.findById(request.getSupplierId());
			if(optional2.isPresent()) {
				u.setSupplier(optional2.get());
			}
			Hdn u2=  hdnRepository.save(u);
			if(!request.getHdnCt().isEmpty()) {
				for (HdnCtRequest e : request.getHdnCt()) {
					HdnCt hdnCt = MapperUtils.map(e, HdnCt.class);
					Optional<Product> optional = productRepository.findById(e.getProductId());
					if(optional.isPresent()) {
						hdnCt.setProduct(optional.get());
					}
					hdnCt.setHdn(u2);
					hdnCt.setCreateBy(UserLogonService.getUsername());
					hdnCt.setCreateDate(LocalDateTime.now());
					hdnCt.setUpdateBy(UserLogonService.getUsername());
					hdnCt.setModifyDate(LocalDateTime.now());
					hdnCt.setStatus(1);
					hdnCtRepository.save(hdnCt);
				}
			}
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Hdn> optional = hdnRepository.findById(request.getId());
			if(optional.isPresent()) {
				Hdn u = optional.get();
				
				List<HdnCt> hdnCts = hdnCtRepository.findByHdn(u);
				if(!hdnCts.isEmpty()) {
					hdnCtRepository.deleteAll(hdnCts);
				}
				if(!request.getHdnCt().isEmpty()) {
					for (HdnCtRequest e : request.getHdnCt()) {
						HdnCt hdnCt = MapperUtils.map(e, HdnCt.class);
						Optional<Product> optional1 = productRepository.findById(e.getProductId());
						if(optional1.isPresent()) {
							hdnCt.setProduct(optional1.get());
						}
						hdnCt.setHdn(u);
						hdnCt.setCreateBy(UserLogonService.getUsername());
						hdnCt.setCreateDate(LocalDateTime.now());
						hdnCt.setUpdateBy(UserLogonService.getUsername());
						hdnCt.setModifyDate(LocalDateTime.now());
						hdnCt.setStatus(1);
						hdnCtRepository.save(hdnCt);
					}
				}
				
				u.setUpdateBy(UserLogonService.getUsername());
				u.setModifyDate(LocalDateTime.now());
				
				Hdn u1 = hdnRepository.save(u);
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
		Page<Hdn> page = hdnRepository.findAll( pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Hdn> optional = hdnRepository.findById(id);
		if(optional.isPresent()) {
//			ProductResponse res = MapperUtils.map(optional.get(), ProductResponse.class);
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
		Optional<Hdn> optional = hdnRepository.findById(id);
		if(optional.isPresent()) {
			List<HdnCt> optional1 = hdnCtRepository.findByHdn(optional.get());
			if(!optional1.isEmpty()) {
				hdnCtRepository.deleteAll(optional1);
			}
			hdnRepository.deleteById(id);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}

}
