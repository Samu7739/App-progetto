package com.gymfocus.repository;

import com.gymfocus.model.GiornoScheda;
import com.gymfocus.model.Scheda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiornoSchedaRepository extends JpaRepository<GiornoScheda, Long> {

    List<GiornoScheda> findBySchedaOrderByOrdineAsc(Scheda scheda);
}