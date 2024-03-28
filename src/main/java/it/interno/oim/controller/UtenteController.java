package it.interno.oim.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.interno.oim.dto.UtenteDto;
import it.interno.oim.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/utente", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Operation(summary = "API per recuperare l'id dell'utente")
    @GetMapping
    public String ricercaUtenteId(@RequestParam String codiceUtente){
        return utenteService.ricercaIdUtente(codiceUtente);
    }

    @Operation(summary = "API per l'inserimento dell'utente")
    @PostMapping
    public void creazioneUtente(@RequestBody UtenteDto input){
        utenteService.creazioneUtente(input);
    }

    @Operation(summary = "API per l'aggiornamento dell'utente")
    @PutMapping
    public void modificaUtente(@RequestBody UtenteDto input){
        utenteService.modificaUtente(input);
    }

    @Operation(summary = "API per la rinominazione dell'utente")
    @PutMapping("/rename")
    public void renameUtente(@RequestParam String codiceUtenteOld,
                             @RequestParam String codiceUtenteNew){
        utenteService.renameUtente(codiceUtenteOld, codiceUtenteNew);
    }

    @Operation(summary = "API per la cancellazione dell'utente")
    @DeleteMapping
    public void deleteUtente(@RequestParam String codiceUtente){
        utenteService.deleteUtente(codiceUtente);
    }

    @Operation(summary = "API per lo sblocco dell'utente")
    @PutMapping("/unlock")
    public void unlockUser(@RequestParam String codiceUtente){
        utenteService.unlockUser(codiceUtente);
    }

    @Operation(summary = "API per il reset della password dell'utente")
    @PutMapping("/reset-password")
    public void resetPassword(@RequestParam String codiceUtente){
        utenteService.resetPassword(codiceUtente);
    }

    @Operation(summary = "API per la cancellazione dell'utente in errore")
    @DeleteMapping("/utente-errore")
    public void cancellaUtenteInErrore(@RequestParam String codiceUtente){
        utenteService.cancellaUtenteInErrore(codiceUtente);
    }
}
