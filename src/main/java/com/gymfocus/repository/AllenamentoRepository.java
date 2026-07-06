package com.gymfocus.repository;

import com.gymfocus.model.Allenamento;
import com.gymfocus.model.GiornoScheda;
import com.gymfocus.model.Scheda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllenamentoRepository extends JpaRepository<Allenamento, Long> {

    List<Allenamento> findBySchedaOrderByDataDesc(Scheda scheda);

    List<Allenamento> findByGiornoSchedaOrderByDataDesc(GiornoScheda giornoScheda);
}