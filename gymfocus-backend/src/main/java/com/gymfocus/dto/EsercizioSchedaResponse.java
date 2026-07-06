package com.gymfocus.dto;

import lombok.Data;

@Data
public class EsercizioSchedaResponse {

    private Long id;

    private Long esercizioId;

    private String nomeEsercizio;

    private String categoriaMuscolare;

    private Integer ordine;

    private Integer serie;

    private String ripetizioniTarget;

    private Integer recuperoSecondi;

    private String note;
}