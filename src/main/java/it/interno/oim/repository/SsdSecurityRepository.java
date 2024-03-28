package it.interno.oim.repository;

import it.interno.oim.dto.UtenteDto;
import it.interno.oim.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SsdSecurityRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SsdSecurityRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Object deleteOimUser(Integer idUtente){

        String query = "SELECT DELETE_OIM_USER(?) FROM DUAL";
        return jdbcTemplate.queryForList(query, idUtente);
    }

    public Object insertUtenteOim(UtenteDto input){

        String query = "INSERT INTO SEC_OIM_USER_STAGING " +
                "(USERNAME, NOME, COGNOME, DATADINASCITA, LUOGODINASCITA, SESSO, CF, FORZA_POLIZIA, EMAIL, " +
                "EMAIL_SECONDARIA, TELEFONO, COD_UFFICIO, UFFICIO, QUALIFICA, RUOLO, DATA_RICHIESTA, STATO) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.queryForList(query, input.getCodiceUtente(), input.getNome(), input.getCognome(),
                input.getDataNascita(), input.getLuogoNascita(), input.getSesso(), input.getCodiceFiscale(),
                input.getDescrizioneEnte(), input.getEmail(), input.getEmailSecondaria(), input.getTelefono(),
                input.getCodiceUfficio(), input.getDescrizioneUfficio(), input.getDescrizioneQualifica(),
                input.getDescrizioneRuolo(), Utils.getCurrentTimestamp(), "COMPLETO");
    }

    public Object updateUtenteOim(UtenteDto input){

        String query = "UPDATE SEC_UTENTE_OIM " +
                "SET FORZA_POLIZIA = ? " +
                ",EMAIL = ? " +
                ",TELEFONO = ? " +
                ",COD_UFFICIO = ? " +
                ",UFFICIO = ? " +
                ",QUALIFICA = ? " +
                ",DATA_AGGIORNAMENTO = SYSDATE " +
                ",STATO = 'COMPLETO' " +
                ",EMAIL_SECONDARIA = ? " +
                ",RUOLO = ? " +
                "WHERE USERNAME = ?";

        return jdbcTemplate.queryForList(query, input.getDescrizioneEnte(), input.getEmail(), input.getTelefono(),
                input.getCodiceUfficio(), input.getDescrizioneUfficio(), input.getDescrizioneQualifica(),
                input.getEmailSecondaria(), input.getDescrizioneRuolo(), input.getCodiceUtente());
    }

    public Object deleteUtenteOim(String codiceUtente){

        String query = "DELECTE FROM SEC_UTENTE_OIM WHERE USERNAME = ?";
        return jdbcTemplate.queryForList(query, codiceUtente);
    }

}
