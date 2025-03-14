package com.example.demo.repository;

import com.example.demo.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    Optional<Sales> findByOrderNumber(String orderNumber);
    
    List<Sales> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Sales> findByCustomerName(String customerName);
    
    List<Sales> findByStatus(Sales.SalesStatus status);
    
    List<Sales> findBySalesPersonId(Long salesPersonId);
}
