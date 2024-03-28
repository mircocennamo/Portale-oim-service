package it.interno.oim.service;

import static it.interno.oim.client.OimCalls.*;

import it.interno.oim.client.OimCalls;
import it.interno.oim.config.OimProperties;
import it.interno.oim.dto.RuoloDto;
import it.interno.oim.entity.RuoloAggiornamento;
import it.interno.oim.entity.RuoloRinominazione;
import it.interno.oim.entity.RuoloInserimento;
import it.interno.oim.exception.RuoloNotCreatedException;
import it.interno.oim.exception.RuoloNotDeletedException;
import it.interno.oim.exception.RuoloNotFoundException;
import it.interno.oim.exception.RuoloNotUpdatedException;
import it.interno.oim.mapper.RuoloMapper;
import it.interno.oim.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RuoloServiceImpl implements RuoloService{

    @Autowired
    private OimProperties oimProperties;

    @Override
    public String ricercaIdRuolo(String nomeRuolo) {

        ResponseEntity<Object> response = OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "?attributes=id,displayName&filter=displayName+eq+" + nomeRuolo, oimProperties.getAuth(), HttpMethod.GET, new HttpHeaders());
        List<LinkedHashMap> results = (ArrayList)((LinkedHashMap)response.getBody()).get("Resources");

        if(results.isEmpty())
            throw new RuoloNotFoundException("Il ruolo non è presente in OIM");

        return results.get(0).get("id").toString();
    }

    @Override
    @Transactional
    public void creazioneRuolo(RuoloDto input) {

        RuoloInserimento ruolo = RuoloMapper.mapToOimInsertEntity(input);

        try {
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getRuolo(), oimProperties.getAuth(), HttpMethod.POST, new HttpHeaders(), ruolo);
        }catch(Exception ex){
            throw new RuoloNotCreatedException("Il ruolo non è stato inserito");
        }
    }

    @Override
    @Transactional
    public void modificaRuolo(RuoloDto input) {

        RuoloAggiornamento ruolo = RuoloMapper.mapToOimUpdateEntity(input);
        try{
            String idRuolo = this.ricercaIdRuolo(input.getNomeRuolo());
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "/" + idRuolo, oimProperties.getAuth(), new HttpHeaders(), ruolo);
        }catch(Exception ex){
            throw new RuoloNotUpdatedException("Il ruolo non è stato aggiornato");
        }
    }

    @Override
    @Transactional
    public void renameRuolo(String nomeRuoloOld, String nomeRuoloNew) {

        RuoloRinominazione ruolo = RuoloMapper.mapToOimRenameEntity(nomeRuoloNew);
        try{
            String idRuolo = this.ricercaIdRuolo(nomeRuoloOld);
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "/" + idRuolo, oimProperties.getAuth(), HttpMethod.PUT, new HttpHeaders(), ruolo);
        }catch(Exception ex){
            throw new RuoloNotUpdatedException("Il ruolo non è stato rinominato");
        }
    }

    @Override
    @Transactional
    /*
    TODO: CONTROLLARE SE LA RENAME DEVE ESSERE FATTA EFFETTIVAMENTE
    PROVARE AD INSERIRE UN RUOLO, CANCELLARLO E REINSERIRLO CON LO STESSO NOME,
    SE NON FACENDO LA RENAME VA TUTTO BENE ALLORA NON SERVE
     */
    public void deleteRuolo(String nomeRuolo) {

        String data = Utils.getCurrentTimestamp().toString().replace("-", "").replace(":", "").replace(" ", "").substring(0,14);
        try{
            String idRuolo = this.ricercaIdRuolo(nomeRuolo);

            this.renameRuolo(nomeRuolo, "REM." + nomeRuolo + "." + data);
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "/" + idRuolo, oimProperties.getAuth(), HttpMethod.DELETE, new HttpHeaders());
        }catch(Exception ex){
            // Rollback della modifica del nome ruolo
            this.renameRuolo("REM." + nomeRuolo + "." + data, nomeRuolo);
            throw new RuoloNotDeletedException("Il ruolo non è stato eliminato");
        }

    }
}
