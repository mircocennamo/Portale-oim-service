package it.interno.oim.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.oim.service.OrganizzazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/organizzazione", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Organizzazione")
public class OrganizzazioneController {

    @Autowired
    private OrganizzazioneService organizzazioneService;

    @Operation(summary = "Api per recuperare l'id dell'organizzazione Xellerate Users")
    @GetMapping
    public String ricercaOrganizzazioneId(@RequestParam String organizzazione){
        return organizzazioneService.ricercaOrganizzazioneId(organizzazione);
    }

}
