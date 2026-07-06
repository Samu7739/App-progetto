package com.gymfocus.repository;

import com.gymfocus.model.Allenamento;
import com.gymfocus.model.EsercizioScheda;
import com.gymfocus.model.SerieAllenamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieAllenamentoRepository extends JpaRepository<SerieAllenamento, Long> {

    List<SerieAllenamento> findByAllenamento(Allenamento allenamento);

    List<SerieAllenamento> findByEsercizioSchedaOrderByAllenamentoDataDesc(EsercizioScheda esercizioScheda);
}