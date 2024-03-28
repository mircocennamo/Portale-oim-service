package it.interno.oim.mapper;

import it.interno.oim.dto.UtenteDto;
import it.interno.oim.entity.UtenteAggiornamento;
import it.interno.oim.entity.UtenteInserimento;
import it.interno.oim.entity.UtenteRinominazione;

public interface UtenteMapper {

    static UtenteInserimento mapToOimInsertEntity(UtenteDto input){
        UtenteInserimento utenteInserimento = new UtenteInserimento();
        utenteInserimento.setDisplayName(input.getNome() + " " + input.getCognome());
        utenteInserimento.setUserName(input.getCodiceUtente());
        utenteInserimento.setTitle(input.getDescrizioneQualifica());
        utenteInserimento.setName(new UtenteInserimento.Name(input.getNome(), input.getCognome()));
        utenteInserimento.getEmails().add(new UtenteInserimento.Email("work", input.getEmail()));
        utenteInserimento.getAddresses().add(new UtenteInserimento.Address("work", input.getDescrizioneUfficio(), input.getCategoria(), input.getCodiceUfficio()));
        utenteInserimento.getPhoneNumbers().add(new UtenteInserimento.PhoneNumber("work", input.getTelefono()));
        utenteInserimento.setDepartment(new UtenteInserimento.Department(input.getDescrizioneEnte()));
        return utenteInserimento;
    }

    static UtenteAggiornamento mapToOimUpdateEntity(UtenteDto input){
        UtenteAggiornamento utenteAggiornamento = new UtenteAggiornamento();
        utenteAggiornamento.getDatiUtente().setUserName(input.getCodiceUtente());
        utenteAggiornamento.getDatiUtente().setName(new UtenteAggiornamento.Name(input.getNome(), input.getCognome()));
        utenteAggiornamento.getDatiUtente().setDisplayName(input.getNome() + " " + input.getCognome());
        utenteAggiornamento.getDatiUtente().getEmails().add(new UtenteAggiornamento.Email("work", input.getEmail()));
        utenteAggiornamento.getDatiUtente().getAddresses().add(new UtenteAggiornamento.Address("work", input.getDescrizioneUfficio(), input.getCategoria(), input.getCodiceUfficio()));
        utenteAggiornamento.getDatiUtente().getPhoneNumbers().add(new UtenteAggiornamento.PhoneNumber("work", input.getTelefono()));
        utenteAggiornamento.getDatiUtente().setTitle(input.getDescrizioneQualifica());
        utenteAggiornamento.getDatiUtente().setDepartment(new UtenteAggiornamento.Department(input.getDescrizioneEnte()));
        return utenteAggiornamento;
    }

    static UtenteRinominazione mapToOimRenameEntity(String codiceUtente){
        UtenteRinominazione utenteRinominazione = new UtenteRinominazione();
        utenteRinominazione.getOperations().add(new UtenteRinominazione.Operation(codiceUtente));
        return utenteRinominazione;
    }

}
