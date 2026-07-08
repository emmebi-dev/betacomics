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
@Table(name = "board_games")
@PrimaryKeyJoinColumn(name = "product_id")
public class BoardGame extends Product {

    @Column(nullable = false)
    private String brand;
	
    @Column(name = "min_players", nullable = false)
    private Integer minPlayers;
    
    @Column(name = "max_players", nullable = false)
    private Integer maxPlayers;
    
    @Column(name = "average_play_time")
    private Integer averagePlayTime;
    
    @Column(name = "recommended_age")
    private Integer recommendedAge;
}