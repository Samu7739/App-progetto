package com.gymfocus.service;

import com.gymfocus.dto.CreaSchedaCompletaRequest;
import com.gymfocus.dto.EsercizioSchedaRequest;
import com.gymfocus.dto.GiornoSchedaRequest;
import com.gymfocus.dto.SchedaCreataResponse;
import com.gymfocus.model.CategoriaMuscolare;
import com.gymfocus.model.Esercizio;
import com.gymfocus.model.EsercizioScheda;
import com.gymfocus.model.GiornoScheda;
import com.gymfocus.model.Scheda;
import com.gymfocus.model.Utente;
import com.gymfocus.repository.CategoriaMuscolareRepository;
import com.gymfocus.repository.EsercizioRepository;
import com.gymfocus.repository.EsercizioSchedaRepository;
import com.gymfocus.repository.GiornoSchedaRepository;
import com.gymfocus.repository.SchedaRepository;
import com.gymfocus.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.gymfocus.dto.EsercizioSchedaResponse;
import com.gymfocus.dto.GiornoSchedaResponse;
import com.gymfocus.dto.SchedaDettaglioResponse;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchedaService {

    private final SchedaRepository schedaRepository;
    private final UtenteRepository utenteRepository;
    private final GiornoSchedaRepository giornoSchedaRepository;
    private final CategoriaMuscolareRepository categoriaMuscolareRepository;
    private final EsercizioRepository esercizioRepository;
    private final EsercizioSchedaRepository esercizioSchedaRepository;

    public SchedaService(
            SchedaRepository schedaRepository,
            UtenteRepository utenteRepository,
            GiornoSchedaRepository giornoSchedaRepository,
            CategoriaMuscolareRepository categoriaMuscolareRepository,
            EsercizioRepository esercizioRepository,
            EsercizioSchedaRepository esercizioSchedaRepository
    ) {
        this.schedaRepository = schedaRepository;
        this.utenteRepository = utenteRepository;
        this.giornoSchedaRepository = giornoSchedaRepository;
        this.categoriaMuscolareRepository = categoriaMuscolareRepository;
        this.esercizioRepository = esercizioRepository;
        this.esercizioSchedaRepository = esercizioSchedaRepository;
    }

    @Transactional
    public SchedaCreataResponse creaSchedaCompleta(CreaSchedaCompletaRequest request) {

        Utente utente = utenteRepository.findById(request.getUtenteId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + request.getUtenteId()));

        Scheda scheda = new Scheda();
        scheda.setNome(request.getNome());
        scheda.setObiettivo(request.getObiettivo());
        scheda.setNumeroGiorni(request.getNumeroGiorni());
        scheda.setNote(request.getNote());
        scheda.setUtente(utente);

        Scheda schedaSalvata = schedaRepository.save(scheda);

        if (request.getGiorni() != null) {
            for (GiornoSchedaRequest giornoRequest : request.getGiorni()) {

                GiornoScheda giornoScheda = new GiornoScheda();
                giornoScheda.setNome(giornoRequest.getNome());
                giornoScheda.setOrdine(giornoRequest.getOrdine());
                giornoScheda.setNote(giornoRequest.getNote());
                giornoScheda.setScheda(schedaSalvata);

                List<CategoriaMuscolare> categorie = new ArrayList<>();

                if (giornoRequest.getCategorieMuscolariIds() != null) {
                    for (Long idCategoria : giornoRequest.getCategorieMuscolariIds()) {
                        CategoriaMuscolare categoria = categoriaMuscolareRepository.findById(idCategoria)
                                .orElseThrow(() -> new RuntimeException("Categoria muscolare non trovata con id: " + idCategoria));

                        categorie.add(categoria);
                    }
                }

                giornoScheda.setCategorieMuscolari(categorie);

                GiornoScheda giornoSalvato = giornoSchedaRepository.save(giornoScheda);

                if (giornoRequest.getEsercizi() != null) {
                    for (EsercizioSchedaRequest esercizioRequest : giornoRequest.getEsercizi()) {

                        Esercizio esercizio = esercizioRepository.findById(esercizioRequest.getEsercizioId())
                                .orElseThrow(() -> new RuntimeException("Esercizio non trovato con id: " + esercizioRequest.getEsercizioId()));

                        EsercizioScheda esercizioScheda = new EsercizioScheda();
                        esercizioScheda.setGiornoScheda(giornoSalvato);
                        esercizioScheda.setEsercizio(esercizio);
                        esercizioScheda.setOrdine(esercizioRequest.getOrdine());
                        esercizioScheda.setSerie(esercizioRequest.getSerie());
                        esercizioScheda.setRipetizioniTarget(esercizioRequest.getRipetizioniTarget());
                        esercizioScheda.setRecuperoSecondi(esercizioRequest.getRecuperoSecondi());
                        esercizioScheda.setNote(esercizioRequest.getNote());

                        esercizioSchedaRepository.save(esercizioScheda);
                    }
                }
            }
        }

        return new SchedaCreataResponse(
                schedaSalvata.getId(),
                "Scheda creata correttamente"
        );
    }
    public SchedaDettaglioResponse getDettaglioScheda(Long idScheda) {

        Scheda scheda = schedaRepository.findById(idScheda)
                .orElseThrow(() -> new RuntimeException("Scheda non trovata con id: " + idScheda));

        SchedaDettaglioResponse response = new SchedaDettaglioResponse();
        response.setId(scheda.getId());
        response.setNome(scheda.getNome());
        response.setObiettivo(scheda.getObiettivo());
        response.setNumeroGiorni(scheda.getNumeroGiorni());
        response.setNote(scheda.getNote());

        List<GiornoScheda> giorni = giornoSchedaRepository.findBySchedaOrderByOrdineAsc(scheda);

        List<GiornoSchedaResponse> giorniResponse = giorni.stream()
                .map(giorno -> {
                    GiornoSchedaResponse giornoResponse = new GiornoSchedaResponse();

                    giornoResponse.setId(giorno.getId());
                    giornoResponse.setNome(giorno.getNome());
                    giornoResponse.setOrdine(giorno.getOrdine());
                    giornoResponse.setNote(giorno.getNote());

                    List<String> categorie = giorno.getCategorieMuscolari()
                            .stream()
                            .map(CategoriaMuscolare::getNome)
                            .collect(Collectors.toList());

                    giornoResponse.setCategorieMuscolari(categorie);

                    List<EsercizioScheda> esercizi = esercizioSchedaRepository
                            .findByGiornoSchedaOrderByOrdineAsc(giorno);

                    List<EsercizioSchedaResponse> eserciziResponse = esercizi.stream()
                            .sorted(Comparator.comparing(EsercizioScheda::getOrdine))
                            .map(esercizioScheda -> {
                                EsercizioSchedaResponse esercizioResponse = new EsercizioSchedaResponse();

                                esercizioResponse.setId(esercizioScheda.getId());
                                esercizioResponse.setEsercizioId(esercizioScheda.getEsercizio().getId());
                                esercizioResponse.setNomeEsercizio(esercizioScheda.getEsercizio().getNome());
                                esercizioResponse.setCategoriaMuscolare(
                                        esercizioScheda.getEsercizio().getCategoriaMuscolare().getNome()
                                );
                                esercizioResponse.setOrdine(esercizioScheda.getOrdine());
                                esercizioResponse.setSerie(esercizioScheda.getSerie());
                                esercizioResponse.setRipetizioniTarget(esercizioScheda.getRipetizioniTarget());
                                esercizioResponse.setRecuperoSecondi(esercizioScheda.getRecuperoSecondi());
                                esercizioResponse.setNote(esercizioScheda.getNote());

                                return esercizioResponse;
                            })
                            .collect(Collectors.toList());

                    giornoResponse.setEsercizi(eserciziResponse);

                    return giornoResponse;
                })
                .collect(Collectors.toList());

        response.setGiorni(giorniResponse);

        return response;
    }
}