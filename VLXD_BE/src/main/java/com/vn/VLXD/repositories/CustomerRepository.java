package com.vn.VLXD.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.ProductType;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query(value = "select s from Customer s where :keySearch is null or s.name like %:keySearch%")
	Page<Customer> findAllSearch(String keySearch,Pageable pageable);
	
	@Query(value = "{CALL CustomerSearch(:Text,:villageId,:page,:size,:orderBy)}", nativeQuery = true)
	List<Customer> findAllTest(@Param("Text")String Text,@Param("villageId")Integer villageId,  @Param("page")Integer page,@Param("size")Integer size,@Param("orderBy")String orderBy);
	
	@Query(value = "{CALL CustomerCount(:Text,:villageId,:page,:size,:orderBy)}", nativeQuery = true)
	Integer CustomerCount(@Param("Text")String Text,@Param("villageId")Integer villageId, @Param("page")Integer page,@Param("size")Integer size,@Param("orderBy")String orderBy);

	@Query(value = "select distinct h.village.id from Customer h where h.village = :village")
	List<Long> lstIdVillageInCustomer(Village village);
}
