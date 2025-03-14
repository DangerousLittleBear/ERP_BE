package com.example.demo.service;

import com.example.demo.model.Sales;
import com.example.demo.repository.SalesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSalesById(Long id) {
        return salesRepository.findById(id);
    }

    public Optional<Sales> getSalesByOrderNumber(String orderNumber) {
        return salesRepository.findByOrderNumber(orderNumber);
    }

    public List<Sales> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return salesRepository.findByOrderDateBetween(startDate, endDate);
    }

    public List<Sales> getSalesByCustomerName(String customerName) {
        return salesRepository.findByCustomerName(customerName);
    }

    public List<Sales> getSalesByStatus(Sales.SalesStatus status) {
        return salesRepository.findByStatus(status);
    }

    public List<Sales> getSalesBySalesPerson(Long salesPersonId) {
        return salesRepository.findBySalesPersonId(salesPersonId);
    }

    @Transactional
    public Sales saveSales(Sales sales) {
        return salesRepository.save(sales);
    }

    @Transactional
    public void deleteSales(Long id) {
        salesRepository.deleteById(id);
    }

    @Transactional
    public Sales updateSales(Long id, Sales salesDetails) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found with id: " + id));

        sales.setOrderNumber(salesDetails.getOrderNumber());
        sales.setOrderDate(salesDetails.getOrderDate());
        sales.setCustomerName(salesDetails.getCustomerName());
        sales.setCustomerContact(salesDetails.getCustomerContact());
        sales.setTotalAmount(salesDetails.getTotalAmount());
        sales.setStatus(salesDetails.getStatus());
        sales.setSalesPerson(salesDetails.getSalesPerson());
        sales.setItems(salesDetails.getItems());

        return salesRepository.save(sales);
    }

    @Transactional
    public Sales updateSalesStatus(Long id, Sales.SalesStatus status) {
        Sales sales = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found with id: " + id));

        sales.setStatus(status);
        return salesRepository.save(sales);
    }
}
