package ru.nikidzawa.geo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;
import ru.nikidzawa.geo.responses.exceptions.NotFoundException;
import ru.nikidzawa.geo.responses.exceptions.ParserException;
import ru.nikidzawa.geo.services.GeoService;

@RestController
@RequestMapping("/geo/api")
public class GeoController {

    public static final String getCoordinates = "getCoordinates";
    public static final String getAddresses = "getAddresses";

    @Autowired
    GeoService geoService;

    @GetMapping(getCoordinates)
    public Coordinates[] getCoordinates(@RequestParam("address") String address) throws ParserException {
        try {
            Coordinates[] coordinates = geoService.getCoordinates(address);
            if (coordinates.length == 0) {throw new NotFoundException("Ничего не найдено");}
            return coordinates;
        } catch (ParserException | JsonProcessingException ex) {
            throw new ParserException("Ошибка парсинга");
        }
    }

    @GetMapping(getAddresses)
    public Address[] getAddresses(@RequestParam("coordinates") String coordinates) throws ParserException {
        try {
            Address[] addresses = geoService.getAddresses(coordinates);
            if (addresses.length == 0) {throw new NotFoundException("Ничего не найдено");}
            return addresses;
        } catch (ParserException | JsonProcessingException ex) {
            throw new ParserException("Ошибка парсинга");
        }
    }
}
