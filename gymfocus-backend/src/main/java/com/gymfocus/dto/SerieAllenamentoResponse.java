package com.gymfocus.dto;

import lombok.Data;

@Data
public class SerieAllenamentoResponse {

    private Long id;

    private Long esercizioSchedaId;

    private String nomeEsercizio;

    private Integer numeroSerie;

    private Double peso;

    private Integer ripetizioni;
}