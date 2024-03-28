package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
@Data
public class RuoloInserimento {

    private ArrayList<String> schemas = new ArrayList<>();
    private String displayName;
    @JsonProperty("urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:Group")
    private GroupDescriprion groupDescriprion = new GroupDescriprion();
    @JsonProperty("urn:ietf:params:scim:schemas:extension:oracle:2.0:OIG:Group")
    private GroupNamespace groupNamespace = new GroupNamespace();

    public RuoloInserimento() {
        this.schemas.addAll(Arrays.asList(
                "urn:ietf:params:scim:schemas:core:2.0:Group",
                "urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:Group",
                "urn:ietf:params:scim:schemas:extension:oracle:2.0:OIG:Group"
        ));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GroupDescriprion {
        private String description;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GroupNamespace {
        private String namespace = "Default";
    }
}
