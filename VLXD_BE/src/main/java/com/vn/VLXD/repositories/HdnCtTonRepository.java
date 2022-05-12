package com.vn.VLXD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Hdn;
import com.vn.VLXD.entities.HdnCtTon;
import com.vn.VLXD.entities.Hdx;
import com.vn.VLXD.entities.HdxCtTon;
import com.vn.VLXD.entities.Product;

@Repository
public interface HdnCtTonRepository extends JpaRepository<HdnCtTon, Long> {
	
//	@Query(value = "select s from Hdn s where :keySearch is null or s.id like %:keySearch%")
//	Page<Hdn> findAllSearch(String keySearch,Pageable pageable);
	List<HdnCtTon> findByHdn(Hdn hdn);
	
	@Query(value = "select * from HDN_CT_TON s where s.hdn_id = :idHdn",nativeQuery = true)
	List<HdnCtTon> findByIdHdn(Long idHdn);
	
	@Query(value = "select distinct h.product.id from HdnCtTon h where h.product = :product")
	List<Long> lstIdProduct(Product product);
}
