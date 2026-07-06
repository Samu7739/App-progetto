package com.gymfocus.dto;

import lombok.Data;

import java.util.List;

@Data
public class GiornoSchedaResponse {

    private Long id;

    private String nome;

    private Integer ordine;

    private String note;

    private List<String> categorieMuscolari;

    private List<EsercizioSchedaResponse> esercizi;
}