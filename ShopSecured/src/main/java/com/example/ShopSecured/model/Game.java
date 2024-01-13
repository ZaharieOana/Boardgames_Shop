package com.example.ShopSecured.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GAMES")
public class Game{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotBlank(message = "Name is required!")
    @NonNull
    @Column(unique = true)
    private String name;

//    @NotBlank(message = "Type is required!")
//    private String type;

    @ManyToOne
    private GameType type;

    //@Positive(message = "Price must be a positive number!")
    private Integer price;


}
