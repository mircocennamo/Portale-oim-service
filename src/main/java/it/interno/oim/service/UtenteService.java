package it.interno.oim.service;

import it.interno.oim.dto.UtenteDto;

public interface UtenteService {
    String ricercaIdUtente(String codiceUtente);
    void creazioneUtente(UtenteDto input);
    void modificaUtente(UtenteDto input);
    void renameUtente(String codiceUtenteOld, String codiceUtenteNew);
    void deleteUtente(String codiceUtente);
    void unlockUser(String codiceUtente);
    void resetPassword(String codiceUtente);
    void cancellaUtenteInErrore(String codiceUtente);
}
