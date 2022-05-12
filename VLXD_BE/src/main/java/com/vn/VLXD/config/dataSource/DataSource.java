//package com.vn.VLXD.config.dataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.vn.VLXD.repositories",
//entityManagerFactoryRef ="vlxdEntityManagerFactory" ,transactionManagerRef = "vlxdTransactionManager")
//public class DataSource {
//	
//		@Bean
//		@ConfigurationProperties("spring.datasource")
//		@Primary
//		public DataSourceProperties vlxdDataSourceProperties() {
//			return new DataSourceProperties();
//		}
//		@Primary
//		@Bean("primaryDataSource")
//		public javax.sql.DataSource primaryDataSource() {
//			return vlxdDataSourceProperties().initializeDataSourceBuilder().type(DriverManagerDataSource.class).build();
//		}
//		@Primary
//		@Bean(name = "vlxdEntityManagerFactory")
//		public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//			return builder.dataSource(primaryDataSource()).packages("com.vn.VLXD.entities").build();
//		}
//		@Primary
//		@Bean(name = "vlxdTransactionManager")
//		public PlatformTransactionManager platformTransactionManager(
//				final @Qualifier("vlxdEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
//			return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
//		}
//
//
//}
