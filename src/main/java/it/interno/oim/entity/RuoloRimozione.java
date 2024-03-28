package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class RuoloRimozione {

    private ArrayList<String> schemas = new ArrayList<>();
    @JsonProperty("Operations")
    private ArrayList<Operation> operations = new ArrayList<>();

    public RuoloRimozione() {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:PatchOp");
    }

    @AllArgsConstructor
    @Data
    public static class Operation {
        private String op = "remove";
        private String path = "urn:ietf:params:scim:schemas:core:2.0:Group:members";
        private ArrayList<Value> value = new ArrayList<>();

        public Operation(Value value) {
            this.value.add(value);
        }
    }

    @NoArgsConstructor
    @Data
    public static class Value {
        private String value;
        @JsonProperty("$ref")
        private String $ref;

        public Value(String value) {
            this.value = value;
            this.$ref = "https://svilorahttp.ced.local:4443/idaas/im/scim/v1/Users/" + value;
        }
    }

}
