package com.smtaps.demoApi.entity;

import java.util.List;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shopper {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "shopper_id", nullable = false, unique = true)
    private String shopperId;
    
    @OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL)
    private List<ShelfItem> shelf;
    
    
}
