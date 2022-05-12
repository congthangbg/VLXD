package com.vn.VLXD.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Hdn;
import com.vn.VLXD.entities.HdnCt;
import com.vn.VLXD.entities.Hdx;
import com.vn.VLXD.entities.HdxCt;
import com.vn.VLXD.entities.HdxCtTon;
import com.vn.VLXD.entities.Product;
import com.vn.VLXD.entities.Supplier;
import com.vn.VLXD.entities.Village;

@Repository
public interface HdxCtTonRepository extends JpaRepository<HdxCtTon, Long> {
	
//	@Query(value = "select s from Hdn s where :keySearch is null or s.id like %:keySearch%")
//	Page<Hdn> findAllSearch(String keySearch,Pageable pageable);
	List<HdxCtTon> findByHdx(Hdx hdn);
	
	@Query(value = "select * from HDX_CT_TON s where s.hdx_id = :idHdx",nativeQuery = true)
	List<HdxCtTon> findByIdHdx(Long idHdx);
	
	@Query(value = "select distinct h.product.id from HdxCtTon h where h.product = :product")
	List<Long> lstIdProduct(Product product);
}
