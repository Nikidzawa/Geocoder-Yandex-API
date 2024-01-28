package ru.nikidzawa.geo.responses.exceptions;

public class YandexApiEx extends RuntimeException {
    public YandexApiEx (String message) {
        super("Ошибка яндекс апи: " + message);
    }
}
