package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schede")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome della scheda è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "L'obiettivo è obbligatorio")
    @Column(nullable = false)
    private String obiettivo;

    @Min(value = 1, message = "Il numero di giorni deve essere almeno 1")
    @Column(nullable = false)
    private Integer numeroGiorni;

    @Column(length = 1000)
    private String note;

    private LocalDateTime dataCreazione;

    private LocalDateTime dataUltimaModifica;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @PrePersist
    public void prePersist() {
        this.dataCreazione = LocalDateTime.now();
        this.dataUltimaModifica = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataUltimaModifica = LocalDateTime.now();
    }
}