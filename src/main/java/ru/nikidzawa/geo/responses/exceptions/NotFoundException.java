package ru.nikidzawa.geo.responses.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException () {super("К сожалению, мы ничего не нашли");}
}
