package com.betacomics.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "board_games")
@PrimaryKeyJoinColumn(name = "product_id")
@DiscriminatorValue("BOARD_GAME")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BoardGame extends Product {

    @Column(nullable = false)
    private String brand;

    @Column(name = "min_players", nullable = false)
    private int minPlayers;
    
    @Column(name = "max_players", nullable = false)
    private int maxPlayers;
    
    @Column(name = "average_play_time")
    private Integer averagePlayTime;
    
    @Column(name = "recommended_age")
    private Integer recommendedAge;
}