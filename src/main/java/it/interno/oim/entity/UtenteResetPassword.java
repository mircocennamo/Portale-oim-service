package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtenteResetPassword {

    private ArrayList<String> schemas = new ArrayList<>();
    @JsonProperty("Operations")
    private ArrayList<Operation> operations = new ArrayList<>();

    public UtenteResetPassword(String email) {
        this.schemas.add("urn:ietf:params:scim:api:messages:2.0:PatchOp");
        this.operations.add(new Operation(email));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Operation {
        private String op = "replace";
        private String path = "urn:ietf:params:scim:schemas:extension:oracle:2.0:IDM:User:passwd";
        private Value value;

        public Operation(String email){
            this.value = new Value(email);
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Value {
        private String value = "auto-generate";
        private String sendNotification = "true";
        private String sendNotificationTo;

        public Value(String sendNotificationTo) {
            this.sendNotificationTo = sendNotificationTo;
        }
    }

}