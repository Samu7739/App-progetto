package com.gymfocus.config;

import com.gymfocus.model.CategoriaMuscolare;
import com.gymfocus.model.Esercizio;
import com.gymfocus.repository.CategoriaMuscolareRepository;
import com.gymfocus.repository.EsercizioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CategoriaMuscolareRepository categoriaMuscolareRepository,
            EsercizioRepository esercizioRepository
    ) {
        return args -> {

            String[] categorie = {
                    "Petto",
                    "Schiena",
                    "Spalle",
                    "Trapezi",
                    "Bicipiti",
                    "Tricipiti",
                    "Avambracci",
                    "Quadricipiti",
                    "Femorali",
                    "Glutei",
                    "Polpacci",
                    "Addominali",
                    "Lombari",
                    "Cardio"
            };

            for (String nomeCategoria : categorie) {
                categoriaMuscolareRepository.findByNome(nomeCategoria)
                        .orElseGet(() -> {
                            CategoriaMuscolare categoria = new CategoriaMuscolare();
                            categoria.setNome(nomeCategoria);
                            return categoriaMuscolareRepository.save(categoria);
                        });
            }

            aggiungiEserciziPetto(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziSchiena(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziSpalle(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziTrapezi(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziBicipiti(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziTricipiti(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziAvambracci(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziQuadricipiti(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziFemorali(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziGlutei(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziPolpacci(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziAddominali(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziLombari(categoriaMuscolareRepository, esercizioRepository);
            aggiungiEserciziCardio(categoriaMuscolareRepository, esercizioRepository);
        };
    }

    private void aggiungiEsercizio(
            String nome,
            String nomeCategoria,
            CategoriaMuscolareRepository categoriaMuscolareRepository,
            EsercizioRepository esercizioRepository
    ) {
        CategoriaMuscolare categoria = categoriaMuscolareRepository.findByNome(nomeCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata: " + nomeCategoria));

        esercizioRepository.findByNomeAndCategoriaMuscolare(nome, categoria)
                .orElseGet(() -> {
                    Esercizio esercizio = new Esercizio();
                    esercizio.setNome(nome);
                    esercizio.setCategoriaMuscolare(categoria);
                    esercizio.setDescrizione(null);
                    esercizio.setImmagineUrl(null);
                    esercizio.setVideoUrl(null);
                    return esercizioRepository.save(esercizio);
                });
    }

    private void aggiungiEserciziPetto(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Petto";

        aggiungiEsercizio("Panca piana bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Panca piana manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Panca inclinata manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Chest press", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Croci ai cavi", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pec deck", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Push-up", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Dip alle parallele per petto", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziSchiena(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Schiena";

        aggiungiEsercizio("Lat machine presa larga", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Lat machine presa stretta", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Trazioni", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pulley basso", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Rematore manubrio", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Rematore bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Seated row machine", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pulldown braccia tese", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stacco da terra", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziSpalle(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Spalle";

        aggiungiEsercizio("Lento avanti bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Lento avanti manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Shoulder press macchina", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Arnold press", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Alzate laterali manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Alzate laterali ai cavi", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Alzate posteriori manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Reverse pec deck", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Face pull", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziTrapezi(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Trapezi";

        aggiungiEsercizio("Scrollate manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Scrollate bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Scrollate alla macchina", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Farmer walk", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Tirate al mento", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Face pull alto", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Rack pull", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziBicipiti(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Bicipiti";

        aggiungiEsercizio("Curl bilanciere EZ", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl manubri alternato", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl martello", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl panca Scott", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl ai cavi basso", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl inclinato manubri", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl concentrato", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl inverso", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziTricipiti(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Tricipiti";

        aggiungiEsercizio("Pushdown al cavo con barra", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pushdown al cavo con corda", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("French press bilanciere EZ", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Skull crusher", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Estensioni sopra la testa manubrio", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Dip su panca", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Panca presa stretta", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Kickback al cavo", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziAvambracci(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Avambracci";

        aggiungiEsercizio("Wrist curl", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Reverse wrist curl", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Curl inverso bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Hammer curl", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Farmer walk", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Dead hang", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Plate hold", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziQuadricipiti(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Quadricipiti";

        aggiungiEsercizio("Squat bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Front squat", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Hack squat", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pressa 45 gradi", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pressa orizzontale", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Leg extension", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Affondi camminati", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Bulgarian split squat", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Squat al multipower", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Goblet squat", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziFemorali(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Femorali";

        aggiungiEsercizio("Leg curl sdraiato", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Leg curl seduto", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Leg curl in piedi", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stacco rumeno", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stacco gambe tese", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Good morning", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Nordic curl", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Pull-through al cavo", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziGlutei(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Glutei";

        aggiungiEsercizio("Hip thrust bilanciere", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Hip thrust macchina", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Glute bridge", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Abductor machine", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Slanci glutei al cavo", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Kickback machine", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Bulgarian split squat", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Step-up", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Cable pull-through", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziPolpacci(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Polpacci";

        aggiungiEsercizio("Calf raise in piedi", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Calf raise seduto", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Calf raise alla pressa", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Calf raise al multipower", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Calf raise monopodalico", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Tibialis raise", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziAddominali(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Addominali";

        aggiungiEsercizio("Crunch", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Crunch inverso", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Leg raise", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Hanging leg raise", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Cable crunch", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Plank", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Side plank", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Russian twist", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Ab wheel", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Mountain climber", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziLombari(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Lombari";

        aggiungiEsercizio("Hyperextension", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Back extension", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Good morning", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stacco da terra", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stacco rumeno", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Superman", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Bird dog", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Reverse hyperextension", categoria, catRepo, esercizioRepo);
    }

    private void aggiungiEserciziCardio(CategoriaMuscolareRepository catRepo, EsercizioRepository esercizioRepo) {
        String categoria = "Cardio";

        aggiungiEsercizio("Tapis roulant camminata", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Tapis roulant corsa", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Tapis roulant inclinato", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Cyclette", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Ellittica", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Stair climber", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Vogatore", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Corda", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Camminata all’aperto", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("Corsa all’aperto", categoria, catRepo, esercizioRepo);
        aggiungiEsercizio("HIIT corpo libero", categoria, catRepo, esercizioRepo);
    }
}