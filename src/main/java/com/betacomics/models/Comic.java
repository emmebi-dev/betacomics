package com.betacomics.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comics")
@PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("COMIC")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Comic extends Product {

    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String publisher;
    
    @Column(name = "volume_number")
    private Integer volumeNumber;
    
    @Column(nullable = false)
    private int pages;
}