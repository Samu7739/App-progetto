package com.gymfocus.repository;

import com.gymfocus.model.CategoriaMuscolare;
import com.gymfocus.model.Esercizio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EsercizioRepository extends JpaRepository<Esercizio, Long> {

    List<Esercizio> findByCategoriaMuscolare(CategoriaMuscolare categoriaMuscolare);

    Optional<Esercizio> findByNomeAndCategoriaMuscolare(String nome, CategoriaMuscolare categoriaMuscolare);
}