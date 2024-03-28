package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class UtenteUnlock {
    private ArrayList<String> schemas = new ArrayList<>();
    @JsonProperty("Operations")
    private ArrayList<Operation> operations = new ArrayList<>();

    public UtenteUnlock() {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:PatchOp");
        this.operations.add(new Operation());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Operation {
        private String op = "replace";
        private String path = "urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:User:locked";
        private Value value = new Value();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Value {
        private int value = 0;
    }
}