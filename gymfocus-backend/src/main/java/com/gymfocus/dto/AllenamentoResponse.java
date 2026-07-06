package com.gymfocus.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AllenamentoResponse {

    private Long id;

    private LocalDateTime data;

    private Long schedaId;

    private String nomeScheda;

    private Long giornoSchedaId;

    private String nomeGiorno;

    private String note;

    private List<SerieAllenamentoResponse> serie;
}