package com.betacomics.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_figures")
@PrimaryKeyJoinColumn(name = "product_id")
public class ActionFigure extends Product {

    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String material;
    
    @Column(nullable = false)
    private Double height;
    
    @Column(nullable = false)
    private Double width;
    
    @Column(nullable = false)
    private Double depth;
    
}