package com.vn.VLXD.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Hdn;
import com.vn.VLXD.entities.Product;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface HdnRepository extends JpaRepository<Hdn, Long> {
	
	@Query(value = "select s from Hdn s where :keySearch is null or s.supplier.name like %:keySearch%")
	Page<Hdn> findAllSearch(String keySearch,Pageable pageable);
	

	List<Hdn> findBySupplier(Supplier supplier);
}
