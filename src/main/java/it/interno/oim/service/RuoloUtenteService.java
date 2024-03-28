package it.interno.oim.service;

public interface RuoloUtenteService {
    void associazioneRuolo(String codiceUtente, String nomeRuolo);
    void rimozioneRuolo(String codiceUtente, String nomeRuolo);
}
