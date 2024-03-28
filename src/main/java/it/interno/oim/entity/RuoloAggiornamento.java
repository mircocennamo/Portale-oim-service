package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RuoloAggiornamento {

    private ArrayList<String> schemas = new ArrayList<>();
    @JsonProperty("Operations")
    private List<Operation> operations = new ArrayList<>();

    public RuoloAggiornamento() {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:PatchOp");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Operation {
        private String op = "replace";
        private String path = "urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:Group:description";
        private String value;

        public Operation(String value) {
            this.value = value;
        }
    }
}
