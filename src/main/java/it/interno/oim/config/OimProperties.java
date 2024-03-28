package it.interno.oim.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@ConfigurationProperties(value = "oim")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OimProperties {

    String baseUrl;
    String ricercaOrganizzazione;
    String utente;
    String ruolo;
    String username;
    String password;

    String auth;

    public String getAuth(){

        if(!StringUtils.isBlank(auth))
            return auth;

        auth = username + ":" + password;
        auth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
        return auth;
    }
}

