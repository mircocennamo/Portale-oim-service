package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RuoloRinominazione {

    private ArrayList<String> schemas = new ArrayList<>();
    private String displayName;

    public RuoloRinominazione() {
        this.schemas.addAll(Arrays.asList(
                "urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:Group",
                "urn:ietf:params:scim:schemas:core:2.0:Group",
                "urn:ietf:params:scim:schemas:extension:oracle:2.0:OIG:Group"
        ));
    }

}
