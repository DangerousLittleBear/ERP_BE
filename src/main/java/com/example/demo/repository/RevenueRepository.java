package com.example.demo.repository;

import com.example.demo.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    List<Revenue> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Revenue> findByCategory(String category);
    
    List<Revenue> findBySource(String source);
}
