package com.vn.VLXD.controller.Report;


import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.VLXD.dto.response.HdxResponse;
import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.repositories.CustomerRepository;
import com.vn.VLXD.services.HdxService;
import com.vn.VLXD.services.Report.ReportHoaDonBanHang;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("api/report")
@CrossOrigin("*")
public class ReportHoaDonBhController {
	@Autowired
	ReportHoaDonBanHang reportHoaDonBanHang;
	@Autowired
	HdxService service;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadInvoice(@PathVariable Long id) throws JRException, IOException {
		
		HdxResponse hdxResponse = service.findByIdHdx(id);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(Arrays.asList(hdxResponse), false);

		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(hdxResponse.getHdxCt());
		JRBeanCollectionDataSource itemsJRBean2 = new JRBeanCollectionDataSource(hdxResponse.getHdxCtTon());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("hdxCt", itemsJRBean);
		parameters.put("hdxCtTon", itemsJRBean2);


		try {
			ClassPathResource resource = new ClassPathResource("/reports/Hoadonbanhang.jrxml");
//			InputStream employeeReportStream = getClass().getResourceAsStream("/Hoadonbanhang.jrxml");
			InputStream employeeReportStream=  resource.getInputStream();
//					new ClassPathResource("/Hoadonbanhang.jrxml").getInputStream();
			JasperReport compileReport
			  = JasperCompileManager.compileReport(employeeReportStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
			byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=hoadon.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}
	

}
