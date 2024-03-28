package it.interno.oim.service;

import it.interno.oim.client.OimCalls;
import it.interno.oim.config.OimProperties;
import it.interno.oim.entity.OrganizzazioneRicerca;
import it.interno.oim.exception.OrganizzazioneNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class OrganizzazioneServiceImpl implements OrganizzazioneService{

    @Autowired
    private OimProperties oimProperties;

    @Override
    public String ricercaOrganizzazioneId(String organizzazione) {

        ResponseEntity<Object> response = OimCalls.exchange(oimProperties.getBaseUrl() + oimProperties.getRicercaOrganizzazione(), oimProperties.getAuth(), HttpMethod.POST, new HttpHeaders(), StringUtils.isBlank(organizzazione) ? new OrganizzazioneRicerca() : new OrganizzazioneRicerca(organizzazione));
        List<LinkedHashMap> results = (ArrayList)((LinkedHashMap)response.getBody()).get("Resources");

        if(results.isEmpty())
            throw new OrganizzazioneNotFoundException("L'organizzazione non è presente in OIM");

        return results.get(0).get("id").toString();
    }
}
