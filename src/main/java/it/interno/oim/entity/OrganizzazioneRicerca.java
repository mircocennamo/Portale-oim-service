package it.interno.oim.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class OrganizzazioneRicerca {
    private ArrayList<String> schemas = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();
    private String filter;

    public OrganizzazioneRicerca() {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:SearchRequest");
        this.attributes.add("id");
        this.filter = "name eq \"Xellerate Users\"";
    }

    public OrganizzazioneRicerca(String organizzazione) {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:SearchRequest");
        this.attributes.add("id");
        this.filter = "name eq \"" + organizzazione + "\"";
    }
}
