package com.gymfocus.controller;

import com.gymfocus.model.CategoriaMuscolare;
import com.gymfocus.repository.CategoriaMuscolareRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategoriaMuscolareController {

    private final CategoriaMuscolareRepository categoriaMuscolareRepository;

    public CategoriaMuscolareController(CategoriaMuscolareRepository categoriaMuscolareRepository) {
        this.categoriaMuscolareRepository = categoriaMuscolareRepository;
    }

    @GetMapping
    public List<CategoriaMuscolare> getCategorie() {
        return categoriaMuscolareRepository.findAll();
    }
}