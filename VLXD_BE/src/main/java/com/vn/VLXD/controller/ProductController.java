package com.vn.VLXD.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.ProductRequest;
import com.vn.VLXD.dto.request.ProductTypeRequest;
import com.vn.VLXD.dto.request.SupplierRequest;
import com.vn.VLXD.dto.request.UnitRequest;
import com.vn.VLXD.dto.request.VillageRequest;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.services.ProductService;
import com.vn.VLXD.services.ProductTypeService;
import com.vn.VLXD.services.SupplierService;
import com.vn.VLXD.services.UnitService;
import com.vn.VLXD.services.UserDetailsImpl;
import com.vn.VLXD.services.UserLogonService;
import com.vn.VLXD.services.VillageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
@Api(value = "ProductController", description = "REST APIs Danh sách sản phẩm !!!!")
public class ProductController {
	@Autowired
	ProductService service;
	
    @GetMapping("")
    @ApiOperation(value = "Danh sách All")
    public ResponseBodyDto<Object> findAllSearch(
    		@RequestParam(value = "keySearch",required = false) String keyString,
    		@RequestParam(value = "page",required = false) Optional<Integer> page,
    		@RequestParam(value = "size",required = false) Optional<Integer> size ) {
    	int currentPage = page.orElse(0);
    	int limit = size.orElse(1000);
    	Pageable pageable = PageRequest.of(currentPage, limit, Sort.by("id").descending());
    	ResponseBodyDto<Object> dto = service.findAllSearch(keyString, pageable);
    	
        return dto;
    }
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "Thêm or Sửa")
    public ResponseBodyDto<Object> saveOrUpdate(
    		@RequestBody ProductRequest request )throws Exception {
    	ResponseBodyDto<Object> dto = service.save(request);
        return dto;
    }
    @GetMapping("/findById")
    public ResponseBodyDto<Object> findById(
    		@RequestParam(value = "id",required =  true)Long id )throws Exception {
    	ResponseBodyDto<Object> dto = service.findById(id);
        return dto;
    }
    @PostMapping("/delete")
    public ResponseBodyDto<Object> delete(
    		@RequestParam(value = "id",required =  true)Long id )throws Exception {
    	ResponseBodyDto<Object> dto = service.deleteById(id);
        return dto;
    }
}
