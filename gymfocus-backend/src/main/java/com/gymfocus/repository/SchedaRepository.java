package com.gymfocus.repository;

import com.gymfocus.model.Scheda;
import com.gymfocus.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedaRepository extends JpaRepository<Scheda, Long> {

    List<Scheda> findByUtente(Utente utente);
}