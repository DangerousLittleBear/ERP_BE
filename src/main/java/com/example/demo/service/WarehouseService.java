package com.example.demo.service;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> getActiveWarehouses() {
        return warehouseRepository.findByActive(true);
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Optional<Warehouse> getWarehouseByCode(String code) {
        return warehouseRepository.findByCode(code);
    }

    public List<Warehouse> getWarehousesByName(String name) {
        return warehouseRepository.findByNameContaining(name);
    }

    @Transactional
    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Transactional
    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));

        warehouse.setCode(warehouseDetails.getCode());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setAddress(warehouseDetails.getAddress());
        warehouse.setContactPerson(warehouseDetails.getContactPerson());
        warehouse.setContactNumber(warehouseDetails.getContactNumber());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setActive(warehouseDetails.getActive());

        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public Warehouse activateWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));

        warehouse.setActive(true);
        return warehouseRepository.save(warehouse);
    }

    @Transactional
    public Warehouse deactivateWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));

        warehouse.setActive(false);
        return warehouseRepository.save(warehouse);
    }
}
