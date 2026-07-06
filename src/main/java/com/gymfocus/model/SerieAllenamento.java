package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "serie_allenamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieAllenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "allenamento_id", nullable = false)
    private Allenamento allenamento;

    @ManyToOne
    @JoinColumn(name = "esercizio_scheda_id", nullable = false)
    private EsercizioScheda esercizioScheda;

    @Min(value = 1, message = "Il numero della serie deve essere almeno 1")
    @Column(nullable = false)
    private Integer numeroSerie;

    @DecimalMin(value = "0.0", message = "Il peso non può essere negativo")
    @Column(nullable = false)
    private Double peso;

    @Min(value = 0, message = "Le ripetizioni non possono essere negative")
    @Column(nullable = false)
    private Integer ripetizioni;
}