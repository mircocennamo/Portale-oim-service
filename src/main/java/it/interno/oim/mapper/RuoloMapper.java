package it.interno.oim.mapper;

import it.interno.oim.dto.RuoloDto;
import it.interno.oim.entity.RuoloAggiornamento;
import it.interno.oim.entity.RuoloRinominazione;
import it.interno.oim.entity.RuoloInserimento;

public interface RuoloMapper {

    static RuoloInserimento mapToOimInsertEntity(RuoloDto input){
        RuoloInserimento ruoloInserimento = new RuoloInserimento();
        ruoloInserimento.setDisplayName(input.getNomeRuolo());
        ruoloInserimento.getGroupDescriprion().setDescription(input.getDescrizioneRuolo());
        return ruoloInserimento;
    }

    static RuoloRinominazione mapToOimRenameEntity(String nomeRuoloNew){
        RuoloRinominazione ruoloRinominazione = new RuoloRinominazione();
        ruoloRinominazione.setDisplayName(nomeRuoloNew);
        return ruoloRinominazione;
    }

    static RuoloAggiornamento mapToOimUpdateEntity(RuoloDto input){
        RuoloAggiornamento ruoloAggiornamento = new RuoloAggiornamento();
        ruoloAggiornamento.getOperations().add(new RuoloAggiornamento.Operation(input.getDescrizioneRuolo()));
        return ruoloAggiornamento;
    }

}
