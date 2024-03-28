package it.interno.oim.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.oim.service.RuoloUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ruolo-utente", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Ruolo Utente")
public class RuoloUtenteController {

    @Autowired
    private RuoloUtenteService utenteService;

    @Operation(summary = "API per associare ruolo e utente")
    @PostMapping("/associazione")
    public void associazioneRuolo(@RequestParam String codiceUtente,
                                  @RequestParam String nomeRuolo){
        utenteService.associazioneRuolo(codiceUtente, nomeRuolo);
    }

    @Operation(summary = "API per disassociare ruolo e utente")
    @PostMapping("/disassociazione")
    public void rimozioneRuolo(@RequestParam String codiceUtente,
                               @RequestParam String nomeRuolo){
        utenteService.rimozioneRuolo(codiceUtente, nomeRuolo);
    }
}
