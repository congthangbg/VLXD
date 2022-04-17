package com.vn.VLXD.repositories;

import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Customer;
import com.vn.VLXD.entities.Hdn;
import com.vn.VLXD.entities.Hdx;

@Repository
public interface HdxRepository extends JpaRepository<Hdx, Long> {
	
//	@Query(value = "select s from Hdn s where :keySearch is null or s.id like %:keySearch%")
//	Page<Hdn> findAllSearch(String keySearch,Pageable pageable);
	@Query(value = "{Call SearchHdx(:text,:status,:page,:size,'')}",nativeQuery = true)
	List<Hdx> SearchHdx(String text,Integer status,Integer page,Integer size);
	
	@Query(value = "{Call SearchCountHdx(:text,:status,:page,:size,'')}",nativeQuery = true)
	Integer countSearch(String text,Integer status,Integer page,Integer size);
	
	@Query(value = "select top(1)x.TOTAL_MONEY from Hdx x where x.status = 1 and x.CUSTOMER_ID =:idCustomer order by x.id desc",nativeQuery =  true)
	Integer totalOwe(Long idCustomer);
	
	Hdx findTopByStatusAndCustomerOrderByIdDesc(Long status,Customer customer);
	
	@Query(value = "select distinct s.customer.id from Hdx s")
	List<Long> findListIdCustomer();
	
}
