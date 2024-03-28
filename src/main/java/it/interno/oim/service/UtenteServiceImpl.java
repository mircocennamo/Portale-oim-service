package it.interno.oim.service;

import it.interno.oim.client.OimCalls;
import it.interno.oim.config.OimProperties;
import it.interno.oim.dto.UtenteDto;
import it.interno.oim.entity.*;
import it.interno.oim.exception.*;
import it.interno.oim.mapper.UtenteMapper;
import it.interno.oim.repository.SsdSecurityRepository;
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
public class UtenteServiceImpl implements UtenteService{

    @Autowired
    private OrganizzazioneService organizzazioneService;
    @Autowired
    private SsdSecurityRepository repository;
    @Autowired
    private OimProperties oimProperties;

    @Override
    public String ricercaIdUtente(String codiceUtente) {

        ResponseEntity<Object> response = OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getUtente() + "?filter=userName+eq+" + codiceUtente + "&attributes=id", oimProperties.getAuth(), HttpMethod.GET, new HttpHeaders());
        List<LinkedHashMap> results = (ArrayList)((LinkedHashMap)response.getBody()).get("Resources");

        if(results.isEmpty())
            throw new UtenteNotFoundException("L'utente non è presente in OIM");

        return results.get(0).get("id").toString();
    }

    @Override
    @Transactional
    public void creazioneUtente(UtenteDto input) {

        UtenteInserimento utenteInserimento = UtenteMapper.mapToOimInsertEntity(input);

        try{
            utenteInserimento.setOrganization(new UtenteInserimento.Organization(organizzazioneService.ricercaOrganizzazioneId(null)));
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getUtente(), oimProperties.getAuth(), HttpMethod.POST, new HttpHeaders(), utenteInserimento);
        }catch (Exception ex){
            this.cancellaUtenteInErrore(input.getCodiceUtente());
            throw new UtenteNotCreatedException("L'utente non è stato creato");
        }
    }

    @Override
    @Transactional
    public void modificaUtente(UtenteDto input) {

        UtenteAggiornamento utenteAggiornamento = UtenteMapper.mapToOimUpdateEntity(input);

        try{
            utenteAggiornamento.getDatiUtente().setOrganization(new UtenteAggiornamento.Organization(organizzazioneService.ricercaOrganizzazioneId(null)));
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getUtente() + "/" + this.ricercaIdUtente(input.getCodiceUtente()), oimProperties.getAuth(), HttpMethod.PUT, new HttpHeaders(), utenteAggiornamento);
        }catch (Exception ex){
            throw new UtenteNotCreatedException("L'utente non è stato aggiornato");
        }
    }

    @Override
    @Transactional
    public void renameUtente(String codiceUtenteOld, String codiceUtenteNew) {

        UtenteRinominazione utenteRinominazione = UtenteMapper.mapToOimRenameEntity(codiceUtenteNew);
        try{
            String idUtente = this.ricercaIdUtente(codiceUtenteOld);
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getUtente() + "/" + idUtente, oimProperties.getAuth(), new HttpHeaders(), utenteRinominazione);
        }catch(Exception ex){
            throw new UtenteNotUpdatedException("L'utente non è stato aggiornato");
        }
    }

    @Override
    @Transactional
    public void deleteUtente(String codiceUtente) {

        String data = Utils.getCurrentTimestamp().toString().replace("-", "").replace(":", "").replace(" ", "").substring(0,14);
        try{
            String idUtente = this.ricercaIdUtente(codiceUtente);

            this.renameUtente(codiceUtente, "REM." + codiceUtente + "." + data);
            OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getUtente() + "/" + idUtente, oimProperties.getAuth(), HttpMethod.DELETE, new HttpHeaders());
        }catch(Exception ex){
            // Rollback della modifica del codice utente
            this.renameUtente("REM." + codiceUtente + "." + data, codiceUtente);
            throw new UtenteNotDeletedException("L'utente non è stato eliminato");
        }
    }

    @Override
    @Transactional
    public void unlockUser(String codiceUtente) {

        try{
            String idUtente = this.ricercaIdUtente(codiceUtente);
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getUtente() + "/" + idUtente, oimProperties.getAuth(), new HttpHeaders(), new UtenteUnlock());
        }catch(Exception ex){
            throw new UtenteNotUnlockedException("L'utente non è stato sbloccato");
        }
    }

    @Override
    @Transactional
    public void resetPassword(String codiceUtente) {

        try{

            ResponseEntity<Object> response = OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getUtente() + "?filter=userName+eq+" + codiceUtente + "&attributes=emails", oimProperties.getAuth(), HttpMethod.GET, new HttpHeaders());
            String email = ((LinkedHashMap)((ArrayList)((LinkedHashMap)((ArrayList)((LinkedHashMap)response.getBody()).get("Resources")).get(0)).get("emails")).get(0)).get("value").toString();

            String idUtente = this.ricercaIdUtente(codiceUtente);
            OimCalls.patch(oimProperties.getBaseUrl() + oimProperties.getUtente() + "/" + idUtente, oimProperties.getAuth(), new HttpHeaders(), new UtenteResetPassword(email));
        }catch(Exception ex){
            throw new UtentePasswordNotResettedException("La password non è stata resettata");
        }
    }

    @Override
    @Transactional
    public void cancellaUtenteInErrore(String codiceUtente){

        Integer idUtente = Integer.parseInt(this.ricercaIdUtente(codiceUtente));
        repository.deleteOimUser(idUtente);
    }
}
