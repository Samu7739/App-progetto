package com.gymfocus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreaAllenamentoRequest {

    @NotNull(message = "La scheda è obbligatoria")
    private Long schedaId;

    @NotNull(message = "Il giorno della scheda è obbligatorio")
    private Long giornoSchedaId;

    private String note;

    private List<SerieAllenamentoRequest> serie;
}