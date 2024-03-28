package it.interno.oim.service;

import it.interno.oim.dto.RuoloDto;

public interface RuoloService {
    String ricercaIdRuolo(String nomeRuolo);
    void creazioneRuolo(RuoloDto input);
    void modificaRuolo(RuoloDto input);
    void renameRuolo(String nomeRuoloOld, String nomeRuoloNew);
    void deleteRuolo(String nomeRuolo);
}
