package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Status;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	
	@Query(value = "select s from Status s where :keySearch is null or s.name like %:keySearch%")
	Page<Status> findAllSearch(String keySearch,Pageable pageable);
}
