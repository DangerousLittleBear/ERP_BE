package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    private String address;
    
    private String contactPerson;
    
    private String contactNumber;
    
    private Integer capacity;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "warehouse")
    private List<Stock> stocks = new ArrayList<>();
} 