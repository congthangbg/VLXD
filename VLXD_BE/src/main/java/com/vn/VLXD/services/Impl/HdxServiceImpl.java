package com.vn.VLXD.services.Impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.Constants;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.request.HdxCtRequest;
import com.vn.VLXD.dto.request.HdxCtTonRequest;
import com.vn.VLXD.dto.request.HdxRequest;
import com.vn.VLXD.dto.response.HdxResponse;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.Hdx;
import com.vn.VLXD.entities.HdxCt;
import com.vn.VLXD.entities.HdxCtTon;
import com.vn.VLXD.entities.Product;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.repositories.HdxCtRepository;
import com.vn.VLXD.repositories.HdxCtTonRepository;
import com.vn.VLXD.repositories.HdxRepository;
import com.vn.VLXD.repositories.ProductRepository;
import com.vn.VLXD.repositories.ProductTypeRepository;
import com.vn.VLXD.services.HdxService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class HdxServiceImpl implements HdxService{

	@Autowired
	ProductTypeRepository productTypeRepository;
	
	@Autowired
	HdxCtTonRepository hdxCtTonRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	HdxRepository hdxRepository;
	
	@Autowired
	HdxCtRepository hdxCtRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional(rollbackFor = {SQLException.class})
	public ResponseBodyDto<Object> save(HdxRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Hdx u = MapperUtils.map(request, Hdx.class);
			
			u.setReleaseDate(request.getReleaseDate().toLocalDateTime());
			u.setCreateBy(UserLogonService.getUsername());
			u.setCreateDate(LocalDateTime.now());
			u.setUpdateBy(UserLogonService.getUsername());
			u.setModifyDate(LocalDateTime.now());
			u.setStatus(request.getStatus() != 0 ? request.getStatus() : Constants.ChuaThanhToan);
			u.setTotalMoney(request.getTotal());
			Optional<Customer> cOptional = customerRepository.findById(request.getCustomerId());
			if(cOptional.isPresent()) {
				u.setCustomer(cOptional.get());
			}
			
			Hdx u2=  hdxRepository.save(u);
			
			if(!request.getHdxCtRequest().isEmpty()) {
				for (HdxCtRequest e : request.getHdxCtRequest()) {
					HdxCt hdnCt = MapperUtils.map(e, HdxCt.class);
					
					Optional<Product> optional = productRepository.findById(e.getProduct().getId());
					if(optional.isPresent()) {
						hdnCt.setProduct(optional.get());
						//Trừ số lượng của product
						optional.get().setQuantity(optional.get().getQuantity() -  e.getQuantity());
							productRepository.save(optional.get());
					}
					hdnCt.setHdx(u2);
					hdnCt.setCreateBy(UserLogonService.getUsername());
					hdnCt.setCreateDate(LocalDateTime.now());
					hdnCt.setUpdateBy(UserLogonService.getUsername());
					hdnCt.setModifyDate(LocalDateTime.now());
					hdnCt.setStatus(1);
					hdxCtRepository.save(hdnCt);
				}
			}
			if(!request.getHdxCtTonRequest().isEmpty()) {
				for (HdxCtTonRequest e : request.getHdxCtTonRequest()) {
					HdxCtTon hdnCt = MapperUtils.map(e, HdxCtTon.class);
					
					Optional<Product> optional = productRepository.findById(e.getProduct().getId());
					if(optional.isPresent()) {
						hdnCt.setProduct(optional.get());
						//Trừ số lượng của product
						optional.get().setQuantity(optional.get().getQuantity() -  e.getQuantity());
							productRepository.save(optional.get());
					}
					hdnCt.setHdx(u2);
					hdnCt.setCreateBy(UserLogonService.getUsername());
					hdnCt.setCreateDate(LocalDateTime.now());
					hdnCt.setUpdateBy(UserLogonService.getUsername());
					hdnCt.setModifyDate(LocalDateTime.now());
					hdnCt.setStatus(1);
					hdxCtTonRepository.save(hdnCt);
					
				}
			}
			dto.setData(u2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Hdx> optional = hdxRepository.findById(request.getId());
			if(optional.isPresent()) {
				Hdx u2 = optional.get();
				
				Optional<Customer> cOptional = customerRepository.findById(request.getCustomerId());
				if(cOptional.isPresent()) {
					u2.setCustomer(cOptional.get());
				}
				if(!request.getHdxCtRequest().isEmpty()) {
					List<HdxCt> hdnCts = hdxCtRepository.findByHdx(u2);
					if(!hdnCts.isEmpty()) {
						for(HdxCt hdxCt : hdnCts) {
							Optional<Product> o = productRepository.findById(hdxCt.getProduct().getId());
							if(o.isPresent()) {
								o.get().setQuantity(hdxCt.getQuantity() + o.get().getQuantity());
								productRepository.save(o.get());
							}
						}
					
						
						hdxCtRepository.deleteAll(hdnCts);
					}
					for (HdxCtRequest e : request.getHdxCtRequest()) {
						HdxCt hdnCt = MapperUtils.map(e, HdxCt.class);
						
						Optional<Product> optional2 = productRepository.findById(e.getProduct().getId());
						if(optional2.isPresent()) {
							hdnCt.setProduct(optional2.get());
							//Trừ số lượng của product
							optional2.get().setQuantity(optional2.get().getQuantity() -  e.getQuantity());
								productRepository.save(optional2.get());
						}
						hdnCt.setHdx(u2);
						hdnCt.setCreateBy(e.getCreateBy() != null ? e.getCreateBy() : UserLogonService.getUsername());
						hdnCt.setCreateDate(e.getCreateDate() != null ? e.getCreateDate() : LocalDateTime.now());
						hdnCt.setUpdateBy(e.getUpdateBy() != null ? e.getUpdateBy() :UserLogonService.getUsername());
						hdnCt.setModifyDate(e.getModifyDate() != null ? e.getModifyDate() : LocalDateTime.now());
						hdnCt.setStatus(1);
						hdxCtRepository.save(hdnCt);
						
						
					}
				}
				
				if(!request.getHdxCtTonRequest().isEmpty()) {
					List<HdxCtTon> hdnCts = hdxCtTonRepository.findByHdx(u2);
					if(!hdnCts.isEmpty()) {
						for(HdxCtTon hdxCt : hdnCts) {
							Optional<Product> o = productRepository.findById(hdxCt.getProduct().getId());
							if(o.isPresent()) {
								o.get().setQuantity(hdxCt.getQuantity() + o.get().getQuantity());
								productRepository.save(o.get());
							}
						}
						hdxCtTonRepository.deleteAll(hdnCts);
					}
					for (HdxCtTonRequest e : request.getHdxCtTonRequest()) {
						HdxCtTon hdnCt = MapperUtils.map(e, HdxCtTon.class);
						
						Optional<Product> optional1 = productRepository.findById(e.getProduct().getId());
						if(optional1.isPresent()) {
							hdnCt.setProduct(optional1.get());
							//Trừ số lượng của product
							optional1.get().setQuantity(optional1.get().getQuantity() -  e.getQuantity());
								productRepository.save(optional1.get());
						}
						hdnCt.setHdx(u2);
						hdnCt.setCreateBy(e.getCreateBy() != null ? e.getCreateBy() : UserLogonService.getUsername());
						hdnCt.setCreateDate(e.getCreateDate() != null ? e.getCreateDate() : LocalDateTime.now());
						hdnCt.setUpdateBy(e.getUpdateBy() != null ? e.getUpdateBy() :UserLogonService.getUsername());
						hdnCt.setModifyDate(e.getModifyDate() != null ? e.getModifyDate() : LocalDateTime.now());
						hdnCt.setStatus(1);
						hdxCtTonRepository.save(hdnCt);
						
						
					}
				}
				u2.setUpdateBy(UserLogonService.getUsername());
				u2.setModifyDate(LocalDateTime.now());
				u2.setOwe(request.getOwe());
				u2.setPay(request.getPay());
				u2.setTotalMoney(request.getTotal());
				u2.setTotalBill(request.getTotalBill());
				u2.setStatus(request.getStatus());
				
				Hdx u1 = hdxRepository.save(u2);
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
	public ResponseBodyDto<Object> findAllSearch(String keySearch,Integer status,Pageable pageable) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		List<Hdx> page = hdxRepository.SearchHdx(keySearch,status, pageable.getPageNumber(),pageable.getPageSize());
		Integer count = hdxRepository.countSearch(keySearch,status, pageable.getPageNumber(),pageable.getPageSize());
		List<HdxResponse> hdxResponses = MapperUtils.mapAll(page, HdxResponse.class);
		
		for (HdxResponse r : hdxResponses) {
			List<HdxCt> hdxCts = hdxCtRepository.findByIdHdx(r.getId());
			List<HdxCtTon> hdxCtTons = hdxCtTonRepository.findByIdHdx(r.getId());
			r.setHdxCt(hdxCts);
			r.setHdxCtTon(hdxCtTons);
		}
		
		dto.setData(hdxResponses);
		dto.setTotalRecords(count);
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}
	
	@Override
	public HdxResponse findByIdHdx(Long id) {
		try {
			Optional<Hdx> optional = hdxRepository.findById(id);
			if(optional.isPresent()) {
				HdxResponse hdxResponses = MapperUtils.map(optional.get(), HdxResponse.class);
			
					List<HdxCt> hdxCts = hdxCtRepository.findByIdHdx(hdxResponses.getId());
					List<HdxCtTon> hdxCtTons = hdxCtTonRepository.findByIdHdx(hdxResponses.getId());
					hdxResponses.setHdxCt(hdxCts);
					hdxResponses.setHdxCtTon(hdxCtTons);
				return hdxResponses;
			}
			
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Hdx> optional = hdxRepository.findById(id);
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
		Optional<Hdx> optional = hdxRepository.findById(id);
		if(optional.isPresent()) {
			List<HdxCt> optional1 = hdxCtRepository.findByHdx(optional.get());
			if(!optional1.isEmpty()) {
//				hdxCtRepository.deleteAll(optional1);
				for (HdxCt hdxCt : optional1) {
					hdxCt.setStatus(0);
					hdxCtRepository.save(hdxCt);
				}
			}
			List<HdxCtTon> optional2 = hdxCtTonRepository.findByHdx(optional.get());
			if(!optional2.isEmpty()) {
				for (HdxCtTon hdxCt : optional2) {
					hdxCt.setStatus(0);
					hdxCtTonRepository.save(hdxCt);
				}
//				hdxCtTonRepository.deleteAll(optional2);
			}
			Hdx hd = optional.get();
			hd.setStatus(0L);
			hdxRepository.save(hd);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}

	@Override
	public Integer totalOwe(Long idCustomer) {
		Optional<Customer> cuOptional= customerRepository.findById(idCustomer);
		if(cuOptional.isPresent()) {
			Hdx hdx = hdxRepository.findTopByStatusAndCustomerOrderByIdDesc(1L, cuOptional.get());
			if(hdx != null) {
				int total = (int) hdx.getTotalMoney();
				return total;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

}
