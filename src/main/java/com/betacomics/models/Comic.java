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
@Table(name = "comics")
@PrimaryKeyJoinColumn(name = "product_id")
public class Comic extends Product {

    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String publisher;
    
    @Column(name = "volume_number")
    private Integer volumeNumber;
    
    @Column(nullable = false)
    private Integer pages;
}
