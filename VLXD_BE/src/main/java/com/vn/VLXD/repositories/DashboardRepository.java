package com.vn.VLXD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Hdx;

@Repository
public interface DashboardRepository extends JpaRepository<Hdx, Long> {
   
    Integer countByStatus(long status);
    
    @Query("SELECT SUM(m.totalMoney - m.owe) FROM Hdx m where m.status=1")
    Integer sumTotalBillByStatus();
    
    @Query("SELECT SUM(m.pay) FROM Hdx m where m.status=2")
    Integer sumTotalBill();
    
    
}
