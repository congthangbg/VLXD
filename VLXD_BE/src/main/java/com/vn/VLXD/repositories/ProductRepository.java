package com.vn.VLXD.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Product;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "select s from Product s where :keySearch is null or s.name like %:keySearch%")
	Page<Product> findAllSearch(String keySearch,Pageable pageable);
	
	List<Product> findByProductType(ProductType productType);
	
	@Query(value = "{CALL ProductSearch(:text,:type,:page,:size,:sort)}",nativeQuery = true)
	List<Product> findAllSearchCallStore(String text,Integer type,Integer page,Integer size,String sort);
	
	@Query(value = "{CALL ProductSearchCount(:text,:type,:page,:size,:sort)}",nativeQuery = true)
	Integer countAllSearchCallStore(String text,Integer type,Integer page,Integer size,String sort);
}
