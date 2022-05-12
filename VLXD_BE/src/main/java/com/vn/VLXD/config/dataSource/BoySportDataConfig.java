//package com.vn.VLXD.config.dataSource;
//
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.vn.VLXD.DbBoySport.repository",
//entityManagerFactoryRef ="boySportEntityManagerFactory" ,transactionManagerRef = "boySportTransactionManager")
//public class BoySportDataConfig {
//	
//	@Bean
//	@ConfigurationProperties("spring.boysport.datasource")
//	public DataSourceProperties boySportDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean
//	public DataSource boySportDataSource() {
//		return boySportDataSourceProperties().initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
//	}
//	@Bean(name = "boySportEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(boySportDataSource()).packages("com.vn.VLXD.DbBoySport.model").build();
//	}
//	@Bean(name = "boySportTransactionManager")
//	public PlatformTransactionManager platformTransactionManager(
//			final @Qualifier("boySportEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
//		return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
//	}
//	
//}
