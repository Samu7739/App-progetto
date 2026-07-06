package com.gymfocus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "allenamenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "scheda_id", nullable = false)
    private Scheda scheda;

    @ManyToOne
    @JoinColumn(name = "giorno_scheda_id", nullable = false)
    private GiornoScheda giornoScheda;

    @Column(length = 1000)
    private String note;

    @PrePersist
    public void prePersist() {
        if (this.data == null) {
            this.data = LocalDateTime.now();
        }
    }
}