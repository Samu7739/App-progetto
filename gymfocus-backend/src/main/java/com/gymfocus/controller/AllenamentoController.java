package com.gymfocus.controller;

import com.gymfocus.dto.AllenamentoCreatoResponse;
import com.gymfocus.dto.AllenamentoResponse;
import com.gymfocus.dto.CreaAllenamentoRequest;
import com.gymfocus.service.AllenamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allenamenti")
public class AllenamentoController {

    private final AllenamentoService allenamentoService;

    public AllenamentoController(AllenamentoService allenamentoService) {
        this.allenamentoService = allenamentoService;
    }

    @PostMapping
    public AllenamentoCreatoResponse creaAllenamento(@Valid @RequestBody CreaAllenamentoRequest request) {
        return allenamentoService.creaAllenamento(request);
    }

    @GetMapping("/{id}")
    public AllenamentoResponse getAllenamentoById(@PathVariable Long id) {
        return allenamentoService.getAllenamentoById(id);
    }

    @GetMapping("/scheda/{schedaId}")
    public List<AllenamentoResponse> getAllenamentiByScheda(@PathVariable Long schedaId) {
        return allenamentoService.getAllenamentiByScheda(schedaId);
    }

    @GetMapping("/giorno/{giornoSchedaId}")
    public List<AllenamentoResponse> getAllenamentiByGiorno(@PathVariable Long giornoSchedaId) {
        return allenamentoService.getAllenamentiByGiorno(giornoSchedaId);
    }
}