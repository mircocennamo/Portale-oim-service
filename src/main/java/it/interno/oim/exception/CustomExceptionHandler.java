package it.interno.oim.exception;

import it.interno.oim.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseDto<Object> buildResponse(HttpStatus status, String messaggio){

        return ResponseDto.builder()
                .code(status.value())
                .error(messaggio)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request){

        String message = ex.getConstraintViolations().iterator().next().getMessage();
        return handleExceptionInternal(ex, buildResponse(HttpStatus.BAD_REQUEST, message), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotFoundException.class)
    public ResponseEntity<Object> ruoloNotFoundException(RuoloNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.NOT_FOUND, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(OrganizzazioneNotFoundException.class)
    public ResponseEntity<Object> organizzazioneNotFoundException(OrganizzazioneNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.NOT_FOUND, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotCreatedException.class)
    public ResponseEntity<Object> ruoloNotCreatedException(RuoloNotCreatedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotUpdatedException.class)
    public ResponseEntity<Object> ruoloNotUpdatedException(RuoloNotUpdatedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotDeletedException.class)
    public ResponseEntity<Object> ruoloNotDeletedException(RuoloNotDeletedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtenteNotFoundException.class)
    public ResponseEntity<Object> utenteNotFoundException(UtenteNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.NOT_FOUND, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtenteNotCreatedException.class)
    public ResponseEntity<Object> utenteNotCreatedException(UtenteNotCreatedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtenteNotUpdatedException.class)
    public ResponseEntity<Object> utenteNotUpdatedException(UtenteNotUpdatedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtenteNotDeletedException.class)
    public ResponseEntity<Object> utenteNotDeletedException(UtenteNotDeletedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtenteNotUnlockedException.class)
    public ResponseEntity<Object> utenteNotUnlockedException(UtenteNotUnlockedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UtentePasswordNotResettedException.class)
    public ResponseEntity<Object> utentePasswordNotResettedException(UtentePasswordNotResettedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotAssociatedException.class)
    public ResponseEntity<Object> ruoloNotAssociatedException(RuoloNotAssociatedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RuoloNotRemovedException.class)
    public ResponseEntity<Object> ruoloNotRemovedException(RuoloNotRemovedException ex, WebRequest request){
        return handleExceptionInternal(ex, buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
