package it.interno.oim.exception;

public class UtentePasswordNotResettedException extends RuntimeException{
    public UtentePasswordNotResettedException(String message) {
        super(message);
    }
}
