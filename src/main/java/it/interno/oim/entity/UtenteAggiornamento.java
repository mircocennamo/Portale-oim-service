package it.interno.oim.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UtenteAggiornamento {

    private ArrayList<String> schemas = new ArrayList<>();
    @JsonProperty("urn:ietf:params:scim:schemas:core:2.0:User")
    private DatiUtente datiUtente = new DatiUtente();

    public UtenteAggiornamento() {
        this.schemas.addAll(Arrays.asList(
                "urn:ietf:params:scim:schemas:extension:oracle:2.0:OIG:User",
                "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User",
                "urn:ietf:params:scim:schemas:core:2.0:User"
        ));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class DatiUtente {

        private String userName;
        private Name name;
        private String displayName;
        private ArrayList<Email> emails = new ArrayList<>();
        private ArrayList<Address> addresses = new ArrayList<>();
        private ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        private String userType = "EMP";
        private String title;
        @JsonProperty("urn:ietf:params:scim:schemas:extension:enterprise:2.0:User")
        private Department department;
        @JsonProperty("urn:ietf:params:scim:schemas:extension:oracle:2.0:OIG:User")
        private Organization organization;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Address {
        private String type;
        private String streetAddress;
        private String region;
        private String postalCode;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Email {
        private String type;
        private String value;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class PhoneNumber {
        private String type;
        private String value;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Department {
        private String department;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Name {
        private String givenName;
        private String familyName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Organization {
        private UtenteInserimento.HomeOrganization homeOrganization;

        public Organization(String organizzatione) {
            this.homeOrganization = new UtenteInserimento.HomeOrganization(organizzatione);
        }
    }

    @NoArgsConstructor
    @Data
    public static class HomeOrganization {
        private String value;
        @JsonProperty("$ref")
        private String $ref;

        public HomeOrganization(String value) {
            this.value = value;
            this.$ref = "http://192.168.15.165:14000/iam/governance/scim/v1/Organizations/" + value;
        }
    }
}
