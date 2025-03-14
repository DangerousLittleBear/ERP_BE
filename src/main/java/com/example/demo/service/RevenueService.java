package com.example.demo.service;

import com.example.demo.model.Revenue;
import com.example.demo.repository.RevenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RevenueService {
    private final RevenueRepository revenueRepository;

    public RevenueService(RevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
    }

    public List<Revenue> getAllRevenues() {
        return revenueRepository.findAll();
    }

    public Optional<Revenue> getRevenueById(Long id) {
        return revenueRepository.findById(id);
    }

    public List<Revenue> getRevenuesByDateRange(LocalDate startDate, LocalDate endDate) {
        return revenueRepository.findByDateBetween(startDate, endDate);
    }

    public List<Revenue> getRevenuesByCategory(String category) {
        return revenueRepository.findByCategory(category);
    }

    public List<Revenue> getRevenuesBySource(String source) {
        return revenueRepository.findBySource(source);
    }

    @Transactional
    public Revenue saveRevenue(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    @Transactional
    public void deleteRevenue(Long id) {
        revenueRepository.deleteById(id);
    }

    @Transactional
    public Revenue updateRevenue(Long id, Revenue revenueDetails) {
        Revenue revenue = revenueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revenue not found with id: " + id));

        revenue.setDate(revenueDetails.getDate());
        revenue.setAmount(revenueDetails.getAmount());
        revenue.setSource(revenueDetails.getSource());
        revenue.setCategory(revenueDetails.getCategory());
        revenue.setDescription(revenueDetails.getDescription());
        revenue.setRecordedBy(revenueDetails.getRecordedBy());

        return revenueRepository.save(revenue);
    }
}
