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
import com.vn.VLXD.dto.request.VillageRequest;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;
import com.vn.VLXD.repositories.SupplierRepository;
import com.vn.VLXD.repositories.VillageRepository;
import com.vn.VLXD.services.SupplierService;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.services.VillageService;
import com.vn.VLXD.utils.MapperUtils;

@Service
@Transactional
public class VillageServiceImpl implements VillageService{

	@Autowired
	VillageRepository villageRepository;

	@Override
	public ResponseBodyDto<Object> save(VillageRequest request) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		if(request.getId() == 0 ) {
			Village village = MapperUtils.map(request, Village.class);	
			village.setCreateBy(UserLogonService.getUsername());
			village.setCreateDate(new Timestamp(System.currentTimeMillis()));
			village.setUpdateBy(UserLogonService.getUsername());
			village.setModifyDate(new Timestamp(System.currentTimeMillis()));
			Village village2=  villageRepository.save(village);
			dto.setData(village2);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			Optional<Village> optional = villageRepository.findById(request.getId());
			if(optional.isPresent()) {
				Village village = optional.get();
				if(request.getVillageName() != null) {
					village.setVillageName(request.getVillageName());
				}
				village.setUpdateBy(UserLogonService.getUsername());
				village.setModifyDate(new Timestamp(System.currentTimeMillis()));
				Village village2 = villageRepository.save(village);
				dto.setData(village2);
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
		Page<Village> page = villageRepository.findAllSearch(keySearch, pageable);
		dto.setData(page.getContent());
		dto.setTotalRecords(page.getTotalElements());
		dto.setMessage(MessageConstant.MSG_OK);
		dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		return dto;
	}

	@Override
	public ResponseBodyDto<Object> findById(Long id) {
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		Optional<Village> optional = villageRepository.findById(id);
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
		Optional<Village> optional = villageRepository.findById(id);
		if(optional.isPresent()) {
			villageRepository.deleteById(id);
			dto.setMessage(MessageConstant.MSG_OK);
			dto.setMessageCode(MessageConstant.MSG_OK_CODE);
		}else {
			dto.setMessage(MessageConstant.MSG_10);
			dto.setMessageCode(MessageConstant.MSG_10_CODE);
		}
		return dto;
	}
	
	
	
}
