package com.vn.VLXD.services.Report;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportHoaDonBanHang {
	
	public byte[] generatePDFReport() throws JRException, IOException {
		
		InputStream employeeReportStream
		  = getClass().getResourceAsStream("/Hoadonbanhang.jrxml");
		JasperReport jasperReport
		  = JasperCompileManager.compileReport(employeeReportStream);
		
//		JasperReport jasperReport = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/Hoadonbanhang.jrxml").getAbsolutePath());
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(new ArrayList<>());
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "TuanLM15"); 
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

//			HttpHeaders headers = new HttpHeaders();
//			//set the PDF format
//			headers.setContentType(MediaType.APPLICATION_PDF);
//			headers.setContentDispositionFormData("filename", "employees-details.pdf");

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
