package com.gymfocus.repository;

import com.gymfocus.model.CategoriaMuscolare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaMuscolareRepository extends JpaRepository<CategoriaMuscolare, Long> {

    Optional<CategoriaMuscolare> findByNome(String nome);
}