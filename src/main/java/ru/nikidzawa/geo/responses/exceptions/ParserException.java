package ru.nikidzawa.geo.responses.exceptions;

public class ParserException extends RuntimeException {
    public ParserException () {
        super("Ошибка парсинга");
    }
}
