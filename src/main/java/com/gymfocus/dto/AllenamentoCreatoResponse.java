package com.gymfocus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllenamentoCreatoResponse {

    private Long idAllenamento;

    private String messaggio;
}