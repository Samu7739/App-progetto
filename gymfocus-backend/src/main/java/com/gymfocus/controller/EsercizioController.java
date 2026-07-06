package com.gymfocus.controller;

import com.gymfocus.model.CategoriaMuscolare;
import com.gymfocus.model.Esercizio;
import com.gymfocus.repository.CategoriaMuscolareRepository;
import com.gymfocus.repository.EsercizioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esercizi")
public class EsercizioController {

    private final EsercizioRepository esercizioRepository;
    private final CategoriaMuscolareRepository categoriaMuscolareRepository;

    public EsercizioController(
            EsercizioRepository esercizioRepository,
            CategoriaMuscolareRepository categoriaMuscolareRepository
    ) {
        this.esercizioRepository = esercizioRepository;
        this.categoriaMuscolareRepository = categoriaMuscolareRepository;
    }

    @GetMapping
    public List<Esercizio> getEsercizi() {
        return esercizioRepository.findAll();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Esercizio> getEserciziByCategoria(@PathVariable Long idCategoria) {
        CategoriaMuscolare categoria = categoriaMuscolareRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con id: " + idCategoria));

        return esercizioRepository.findByCategoriaMuscolare(categoria);
    }
}