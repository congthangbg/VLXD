package com.vn.VLXD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class VLXDApplication {

	public static void main(String[] args) {
		SpringApplication.run(VLXDApplication.class, args);
	}

}
