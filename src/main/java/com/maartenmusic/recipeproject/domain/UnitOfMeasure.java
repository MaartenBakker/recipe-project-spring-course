package com.maartenmusic.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public UnitOfMeasure(String description) {
        this.description = description;
    }

}
