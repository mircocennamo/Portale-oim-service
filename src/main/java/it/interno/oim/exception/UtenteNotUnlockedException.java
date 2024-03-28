package it.interno.oim.exception;

public class UtenteNotUnlockedException extends RuntimeException{
    public UtenteNotUnlockedException(String message) {
        super(message);
    }
}
