package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "esercizi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome dell'esercizio è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String descrizione;

    private String immagineUrl;

    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "categoria_muscolare_id", nullable = false)
    private CategoriaMuscolare categoriaMuscolare;
}