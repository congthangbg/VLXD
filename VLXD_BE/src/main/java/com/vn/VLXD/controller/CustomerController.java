package com.vn.VLXD.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.dto.request.CustomerRequest;
import com.vn.VLXD.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
@Api(value = "CustomerController", description = "REST APIs danh sách khách hàng !!!!")
@EnableTransactionManagement
public class CustomerController {
	@Autowired
	CustomerService service;
	
//	@Autowired
//	optionRepo optionRepo;
//	
//	@GetMapping("/option")
//	public List<optionBoySport> getAll() {
//		List<optionBoySport> list = optionRepo.findAll();
//		return list;
//	}
	
	
    @GetMapping("")
    @ApiOperation(value = "Danh sách All")
    public ResponseBodyDto<Object> findAllSearch(
    		@RequestParam(value = "keySearch",required = false) String keySearch,
    		@RequestParam(value = "page",required = false) Optional<Integer> page,
    		@RequestParam(value = "size",required = false) Optional<Integer> size ) {
    	int currentPage = page.orElse(0);
    	int limit = size.orElse(1000);
    	Pageable pageable = PageRequest.of(currentPage, limit, Sort.by("id").descending());
    	ResponseBodyDto<Object> dto = service.findAllSearch(keySearch, pageable);
    	
        return dto;
    }
    @GetMapping("/callCustomer")
    @ApiOperation(value = "Danh sách All")
    public ResponseBodyDto<Object> callCustomer(
    		@RequestParam(value = "keySearch",required = false) String keySearch,
    		@RequestParam(value = "villageId",required = false) Integer villageId,
    		@RequestParam(value = "page",required = false) Optional<Integer> page,
    		@RequestParam(value = "size",required = false) Optional<Integer> size,
    		@RequestParam(value = "order",required = false,defaultValue = "id") Optional<String> order ) throws Exception {
    	int currentPage = page.orElse(0);
    	int limit = size.orElse(1000);
    	ResponseBodyDto<Object> lst = service.findAllTest(keySearch,villageId ,currentPage,limit,order.get());
    	
        return lst;
    }
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "Thêm or Sửa")
    public ResponseBodyDto<Object> saveOrUpdate(
    		@RequestBody CustomerRequest request )throws Exception {
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
