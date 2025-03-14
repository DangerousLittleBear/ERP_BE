package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "revenues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    private String source;
    
    private String category;
    
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member recordedBy;
} 