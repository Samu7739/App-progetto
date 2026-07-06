package com.gymfocus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreaSchedaCompletaRequest {
	@NotNull(message = "L'utente è obbligatorio")
	private Long utenteId;

    @NotBlank(message = "Il nome della scheda è obbligatorio")
    private String nome;

    @NotBlank(message = "L'obiettivo è obbligatorio")
    private String obiettivo;

    @Min(value = 1, message = "Il numero di giorni deve essere almeno 1")
    private Integer numeroGiorni;

    private String note;

 

    private List<GiornoSchedaRequest> giorni;
}