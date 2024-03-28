package it.interno.oim.service;

import it.interno.oim.client.OimCalls;
import it.interno.oim.config.OimProperties;
import it.interno.oim.entity.RuoloAssociazione;
import it.interno.oim.entity.RuoloRimozione;
import it.interno.oim.exception.RuoloNotAssociatedException;
import it.interno.oim.exception.RuoloNotRemovedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuoloUtenteServiceImpl implements RuoloUtenteService{

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RuoloService ruoloService;
    @Autowired
    private OimProperties oimProperties;

    @Override
    @Transactional
    public void associazioneRuolo(String codiceUtente, String nomeRuolo) {

        try{
            String idUtente = utenteService.ricercaIdUtente(codiceUtente);
            String idRuolo = ruoloService.ricercaIdRuolo(nomeRuolo);

            RuoloAssociazione ruoloAssociazione = new RuoloAssociazione();
            ruoloAssociazione.getOperations().add(new RuoloAssociazione.Operation(new RuoloAssociazione.Value(idUtente)));
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "/" + idRuolo, oimProperties.getAuth(), new HttpHeaders(), ruoloAssociazione);
        }catch (Exception ex){
            throw new RuoloNotAssociatedException("Il ruolo non è stato associato");
        }
    }

    @Override
    @Transactional
    public void rimozioneRuolo(String codiceUtente, String nomeRuolo) {

        try{
            String idUtente = utenteService.ricercaIdUtente(codiceUtente);
            String idRuolo = ruoloService.ricercaIdRuolo(nomeRuolo);

            RuoloRimozione ruoloRimozione = new RuoloRimozione();
            ruoloRimozione.getOperations().add(new RuoloRimozione.Operation(new RuoloRimozione.Value(idUtente)));
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getRuolo() + "/" + idRuolo, oimProperties.getAuth(), new HttpHeaders(), ruoloRimozione);
        }catch (Exception ex){
            throw new RuoloNotRemovedException("Il ruolo non è stato rimosso");
        }
    }
}
