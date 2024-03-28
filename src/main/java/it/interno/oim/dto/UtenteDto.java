package it.interno.oim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtenteDto {
    private String codiceUtente;
    private String cognome;
    private String nome;
    private String email;
    private String codiceUfficio;
    private String descrizioneUfficio;
    private String telefono;
    private String descrizioneQualifica;
    private String categoria;
    private String descrizioneEnte;
    private LocalDate dataNascita;
    private String luogoNascita;
    private String sesso;
    private String codiceFiscale;
    private String emailSecondaria;
    private String descrizioneRuolo;
}
