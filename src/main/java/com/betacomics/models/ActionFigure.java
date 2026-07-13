package com.betacomics.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "action_figures")
@PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("ACTION_FIGURE")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ActionFigure extends Product {

    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String material;
    
    @Column(nullable = false)
    private double height;
    
    @Column(nullable = false)
    private double width;
    
    @Column(nullable = false)
    private double depth;
}