package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
	
	@Query(value = "select s from Village s where :keySearch is null or s.villageName like %:keySearch%")
	Page<Village> findAllSearch(String keySearch,Pageable pageable);
}
