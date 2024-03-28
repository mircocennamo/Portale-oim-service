package it.interno.oim.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.oim.dto.RuoloDto;
import it.interno.oim.service.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ruolo", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Ruolo")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @Operation(summary = "API per recuperare l'id del ruolo")
    @GetMapping
    public String ricercaRuoloId(@RequestParam String nomeRuolo){
        return ruoloService.ricercaIdRuolo(nomeRuolo);
    }

    @Operation(summary = "API per l'inserimento del ruolo")
    @PostMapping
    public void creazioneRuolo(@RequestBody RuoloDto input){
        ruoloService.creazioneRuolo(input);
    }

    @Operation(summary = "API per l'aggiornamento del ruolo")
    @PutMapping
    public void modificaRuolo(@RequestBody RuoloDto input){
        ruoloService.modificaRuolo(input);
    }

    @Operation(summary = "API per la rinominazione del ruolo")
    @PutMapping("/rename")
    public void renameRuolo(@RequestParam String nomeRuoloOld,
                            @RequestParam String nomeRuoloNew){
        ruoloService.renameRuolo(nomeRuoloOld, nomeRuoloNew);
    }

    @Operation(summary = "API per la cancellazione del ruolo")
    @DeleteMapping
    public void deleteRuolo(@RequestParam String nomeRuolo){
        ruoloService.deleteRuolo(nomeRuolo);
    }
}
