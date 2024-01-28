package ru.nikidzawa.geo.responses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Exception> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Exception.builder()
                        .status(HttpStatus.NOT_FOUND.toString())
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler(ParserException.class)
    public ResponseEntity<Exception> handleJsonException(ParserException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Exception.builder()
                        .status(HttpStatus.CONFLICT.toString())
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler(YandexApiEx.class)
    public ResponseEntity<Exception> handleJsonException(YandexApiEx ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Exception.builder()
                        .status(HttpStatus.FORBIDDEN.toString())
                        .message(ex.getMessage())
                        .build());
    }
}
