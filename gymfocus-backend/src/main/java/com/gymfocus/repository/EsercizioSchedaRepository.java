package com.gymfocus.repository;

import com.gymfocus.model.EsercizioScheda;
import com.gymfocus.model.GiornoScheda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsercizioSchedaRepository extends JpaRepository<EsercizioScheda, Long> {

    List<EsercizioScheda> findByGiornoSchedaOrderByOrdineAsc(GiornoScheda giornoScheda);
}