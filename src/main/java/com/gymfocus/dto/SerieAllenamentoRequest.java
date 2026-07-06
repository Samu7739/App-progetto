package com.gymfocus.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SerieAllenamentoRequest {

    @NotNull(message = "L'esercizio della scheda è obbligatorio")
    private Long esercizioSchedaId;

    @Min(value = 1, message = "Il numero della serie deve essere almeno 1")
    private Integer numeroSerie;

    @DecimalMin(value = "0.0", message = "Il peso non può essere negativo")
    private Double peso;

    @Min(value = 0, message = "Le ripetizioni non possono essere negative")
    private Integer ripetizioni;
}