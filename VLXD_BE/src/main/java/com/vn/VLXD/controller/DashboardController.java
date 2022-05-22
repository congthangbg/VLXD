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
import com.vn.VLXD.repositories.DashboardRepository;
import com.vn.VLXD.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
@Api(value = "dashboard", description = "REST APIs dashboard !!!!")
@EnableTransactionManagement
public class DashboardController {
	@Autowired
	DashboardRepository dashboardRepository;

   
    @GetMapping("/billNotPay")
    public ResponseBodyDto<Object> billNotPay()throws Exception {
    	ResponseBodyDto<Object> dto=new ResponseBodyDto<>();
    	Integer count = dashboardRepository.countByStatus(1);
    	dto.setData(count);
        return dto;
    }
    
    @GetMapping("/moneyNotPay")
    public ResponseBodyDto<Object> moneyNotPay()throws Exception {
    	ResponseBodyDto<Object> dto=new ResponseBodyDto<>();
    	Integer count = dashboardRepository.sumTotalBillByStatus();
    	dto.setData(count);
        return dto;
    }
 
    @GetMapping("/moneyPay")
    public ResponseBodyDto<Object> moneyPay()throws Exception {
    	ResponseBodyDto<Object> dto=new ResponseBodyDto<>();
    	Integer count = dashboardRepository.sumTotalBill();
    	dto.setData(count);
        return dto;
    }
  
}
