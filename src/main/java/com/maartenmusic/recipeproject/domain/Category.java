package com.maartenmusic.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;

    @ManyToMany(mappedBy = "categories"
//            ,fetch = FetchType.EAGER
    )
    private Set<Recipe> recipes = new HashSet<>();

    public Category(String description) {
        this.description = description;
    }

}
