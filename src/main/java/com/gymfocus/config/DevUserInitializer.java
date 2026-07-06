package com.gymfocus.config;

import com.gymfocus.model.Utente;
import com.gymfocus.repository.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevUserInitializer {

    @Bean
    CommandLineRunner initDevUser(UtenteRepository utenteRepository) {
        return args -> {

            String emailDev = "dev@gymfocus.it";

            boolean utenteGiaEsiste = utenteRepository.findByEmail(emailDev).isPresent();

            if (!utenteGiaEsiste) {
                Utente utente = new Utente();

                utente.setNome("Utente");
                utente.setCognome("Provvisorio");
                utente.setEmail(emailDev);
                utente.setPassword("password");

                utenteRepository.save(utente);

                System.out.println("Utente provvisorio creato: dev@gymfocus.it");
            }
        };
    }
}