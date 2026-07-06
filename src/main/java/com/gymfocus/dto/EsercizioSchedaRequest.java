package com.gymfocus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EsercizioSchedaRequest {

    @NotNull(message = "L'esercizio è obbligatorio")
    private Long esercizioId;

    @Min(value = 1, message = "L'ordine dell'esercizio deve essere almeno 1")
    private Integer ordine;

    @Min(value = 1, message = "Il numero di serie deve essere almeno 1")
    private Integer serie;

    @NotBlank(message = "Le ripetizioni target sono obbligatorie")
    private String ripetizioniTarget;

    @Min(value = 0, message = "Il recupero non può essere negativo")
    private Integer recuperoSecondi;

    private String note;
}