package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "categorie_muscolari")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaMuscolare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome della categoria muscolare è obbligatorio")
    @Column(nullable = false, unique = true)
    private String nome;
}