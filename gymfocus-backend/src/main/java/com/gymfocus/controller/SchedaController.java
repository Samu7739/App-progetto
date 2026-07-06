package com.gymfocus.controller;

import com.gymfocus.dto.CreaSchedaCompletaRequest;
import com.gymfocus.dto.SchedaCreataResponse;
import com.gymfocus.model.Scheda;
import com.gymfocus.model.Utente;
import com.gymfocus.repository.SchedaRepository;
import com.gymfocus.repository.UtenteRepository;
import com.gymfocus.service.SchedaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.gymfocus.dto.SchedaDettaglioResponse;
import java.util.List;

@RestController
@RequestMapping("/api/schede")
public class SchedaController {

    private final SchedaService schedaService;
    private final SchedaRepository schedaRepository;
    private final UtenteRepository utenteRepository;

    public SchedaController(
            SchedaService schedaService,
            SchedaRepository schedaRepository,
            UtenteRepository utenteRepository
    ) {
        this.schedaService = schedaService;
        this.schedaRepository = schedaRepository;
        this.utenteRepository = utenteRepository;
    }

    @PostMapping("/completa")
    public SchedaCreataResponse creaSchedaCompleta(@Valid @RequestBody CreaSchedaCompletaRequest request) {
        return schedaService.creaSchedaCompleta(request);
    }

    @GetMapping
    public List<Scheda> getSchede() {
        return schedaRepository.findAll();
    }

    @GetMapping("/utente/{utenteId}")
    public List<Scheda> getSchedeByUtente(@PathVariable Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));

        return schedaRepository.findByUtente(utente);
    }

    @GetMapping("/{id}")
    public SchedaDettaglioResponse getSchedaById(@PathVariable Long id) {
        return schedaService.getDettaglioScheda(id);
    }

    @DeleteMapping("/{id}")
    public void eliminaScheda(@PathVariable Long id) {
        schedaRepository.deleteById(id);
    }
}