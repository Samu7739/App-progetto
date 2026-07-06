package com.gymfocus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Column(nullable = false)
    private String cognome;

    @Email(message = "Email non valida")
    @NotBlank(message = "L'email è obbligatoria")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Column(nullable = false)
    private String password;
}