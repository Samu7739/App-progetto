package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "esercizi_scheda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsercizioScheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giorno_scheda_id", nullable = false)
    private GiornoScheda giornoScheda;

    @ManyToOne
    @JoinColumn(name = "esercizio_id", nullable = false)
    private Esercizio esercizio;

    @Min(value = 1, message = "L'ordine deve essere almeno 1")
    @Column(nullable = false)
    private Integer ordine;

    @Min(value = 1, message = "Il numero di serie deve essere almeno 1")
    @Column(nullable = false)
    private Integer serie;

    @NotBlank(message = "Le ripetizioni target sono obbligatorie")
    @Column(nullable = false)
    private String ripetizioniTarget;

    @Min(value = 0, message = "Il recupero non può essere negativo")
    private Integer recuperoSecondi;

    @Column(length = 1000)
    private String note;
}