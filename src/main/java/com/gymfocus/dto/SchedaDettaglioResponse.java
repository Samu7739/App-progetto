package com.gymfocus.dto;

import lombok.Data;

import java.util.List;

@Data
public class SchedaDettaglioResponse {

    private Long id;

    private String nome;

    private String obiettivo;

    private Integer numeroGiorni;

    private String note;

    private List<GiornoSchedaResponse> giorni;
}