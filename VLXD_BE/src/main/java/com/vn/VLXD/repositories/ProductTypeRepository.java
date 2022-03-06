package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	
	@Query(value = "select s from ProductType s where :keySearch is null or s.typeName like %:keySearch%")
	Page<ProductType> findAllSearch(String keySearch,Pageable pageable);
}
