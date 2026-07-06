package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "giorni_scheda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiornoScheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome del giorno è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @Min(value = 1, message = "L'ordine deve essere almeno 1")
    @Column(nullable = false)
    private Integer ordine;

    @Column(length = 1000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "scheda_id", nullable = false)
    private Scheda scheda;

    @ManyToMany
    @JoinTable(
            name = "giorno_scheda_categorie",
            joinColumns = @JoinColumn(name = "giorno_scheda_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_muscolare_id")
    )
    private List<CategoriaMuscolare> categorieMuscolari;
}