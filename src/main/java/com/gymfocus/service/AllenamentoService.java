package com.gymfocus.service;
import com.gymfocus.dto.AllenamentoResponse;
import com.gymfocus.dto.SerieAllenamentoResponse;

import java.util.List;
import java.util.stream.Collectors;
import com.gymfocus.dto.AllenamentoCreatoResponse;
import com.gymfocus.dto.CreaAllenamentoRequest;
import com.gymfocus.dto.SerieAllenamentoRequest;
import com.gymfocus.model.Allenamento;
import com.gymfocus.model.EsercizioScheda;
import com.gymfocus.model.GiornoScheda;
import com.gymfocus.model.Scheda;
import com.gymfocus.model.SerieAllenamento;
import com.gymfocus.repository.AllenamentoRepository;
import com.gymfocus.repository.EsercizioSchedaRepository;
import com.gymfocus.repository.GiornoSchedaRepository;
import com.gymfocus.repository.SchedaRepository;
import com.gymfocus.repository.SerieAllenamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AllenamentoService {

    private final AllenamentoRepository allenamentoRepository;
    private final SerieAllenamentoRepository serieAllenamentoRepository;
    private final SchedaRepository schedaRepository;
    private final GiornoSchedaRepository giornoSchedaRepository;
    private final EsercizioSchedaRepository esercizioSchedaRepository;

    public AllenamentoService(
            AllenamentoRepository allenamentoRepository,
            SerieAllenamentoRepository serieAllenamentoRepository,
            SchedaRepository schedaRepository,
            GiornoSchedaRepository giornoSchedaRepository,
            EsercizioSchedaRepository esercizioSchedaRepository
    ) {
        this.allenamentoRepository = allenamentoRepository;
        this.serieAllenamentoRepository = serieAllenamentoRepository;
        this.schedaRepository = schedaRepository;
        this.giornoSchedaRepository = giornoSchedaRepository;
        this.esercizioSchedaRepository = esercizioSchedaRepository;
    }

    @Transactional
    public AllenamentoCreatoResponse creaAllenamento(CreaAllenamentoRequest request) {

        Scheda scheda = schedaRepository.findById(request.getSchedaId())
                .orElseThrow(() -> new RuntimeException("Scheda non trovata con id: " + request.getSchedaId()));

        GiornoScheda giornoScheda = giornoSchedaRepository.findById(request.getGiornoSchedaId())
                .orElseThrow(() -> new RuntimeException("Giorno scheda non trovato con id: " + request.getGiornoSchedaId()));

        Allenamento allenamento = new Allenamento();
        allenamento.setScheda(scheda);
        allenamento.setGiornoScheda(giornoScheda);
        allenamento.setNote(request.getNote());

        Allenamento allenamentoSalvato = allenamentoRepository.save(allenamento);

        if (request.getSerie() != null) {
            for (SerieAllenamentoRequest serieRequest : request.getSerie()) {

                EsercizioScheda esercizioScheda = esercizioSchedaRepository
                        .findById(serieRequest.getEsercizioSchedaId())
                        .orElseThrow(() -> new RuntimeException(
                                "Esercizio scheda non trovato con id: " + serieRequest.getEsercizioSchedaId()
                        ));

                SerieAllenamento serieAllenamento = new SerieAllenamento();
                serieAllenamento.setAllenamento(allenamentoSalvato);
                serieAllenamento.setEsercizioScheda(esercizioScheda);
                serieAllenamento.setNumeroSerie(serieRequest.getNumeroSerie());
                serieAllenamento.setPeso(serieRequest.getPeso());
                serieAllenamento.setRipetizioni(serieRequest.getRipetizioni());

                serieAllenamentoRepository.save(serieAllenamento);
            }
        }

        return new AllenamentoCreatoResponse(
                allenamentoSalvato.getId(),
                "Allenamento salvato correttamente"
        );
    }
    public AllenamentoResponse getAllenamentoById(Long idAllenamento) {

        Allenamento allenamento = allenamentoRepository.findById(idAllenamento)
                .orElseThrow(() -> new RuntimeException("Allenamento non trovato con id: " + idAllenamento));

        return convertiInResponse(allenamento);
    }

    public List<AllenamentoResponse> getAllenamentiByScheda(Long schedaId) {

        Scheda scheda = schedaRepository.findById(schedaId)
                .orElseThrow(() -> new RuntimeException("Scheda non trovata con id: " + schedaId));

        return allenamentoRepository.findBySchedaOrderByDataDesc(scheda)
                .stream()
                .map(this::convertiInResponse)
                .collect(Collectors.toList());
    }

    public List<AllenamentoResponse> getAllenamentiByGiorno(Long giornoSchedaId) {

        GiornoScheda giornoScheda = giornoSchedaRepository.findById(giornoSchedaId)
                .orElseThrow(() -> new RuntimeException("Giorno scheda non trovato con id: " + giornoSchedaId));

        return allenamentoRepository.findByGiornoSchedaOrderByDataDesc(giornoScheda)
                .stream()
                .map(this::convertiInResponse)
                .collect(Collectors.toList());
    }

    private AllenamentoResponse convertiInResponse(Allenamento allenamento) {

        AllenamentoResponse response = new AllenamentoResponse();

        response.setId(allenamento.getId());
        response.setData(allenamento.getData());

        response.setSchedaId(allenamento.getScheda().getId());
        response.setNomeScheda(allenamento.getScheda().getNome());

        response.setGiornoSchedaId(allenamento.getGiornoScheda().getId());
        response.setNomeGiorno(allenamento.getGiornoScheda().getNome());

        response.setNote(allenamento.getNote());

        List<SerieAllenamentoResponse> serieResponse = serieAllenamentoRepository
                .findByAllenamento(allenamento)
                .stream()
                .map(serie -> {
                    SerieAllenamentoResponse serieDto = new SerieAllenamentoResponse();

                    serieDto.setId(serie.getId());
                    serieDto.setEsercizioSchedaId(serie.getEsercizioScheda().getId());
                    serieDto.setNomeEsercizio(serie.getEsercizioScheda().getEsercizio().getNome());
                    serieDto.setNumeroSerie(serie.getNumeroSerie());
                    serieDto.setPeso(serie.getPeso());
                    serieDto.setRipetizioni(serie.getRipetizioni());

                    return serieDto;
                })
                .collect(Collectors.toList());

        response.setSerie(serieResponse);

        return response;
    }
}