package com.gymfocus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class GiornoSchedaRequest {

    @NotBlank(message = "Il nome del giorno è obbligatorio")
    private String nome;

    @Min(value = 1, message = "L'ordine del giorno deve essere almeno 1")
    private Integer ordine;

    private String note;

    private List<Long> categorieMuscolariIds;

    private List<EsercizioSchedaRequest> esercizi;
}