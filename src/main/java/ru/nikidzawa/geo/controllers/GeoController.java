package ru.nikidzawa.geo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;
import ru.nikidzawa.geo.responses.exceptions.NotFoundException;
import ru.nikidzawa.geo.services.GeoService;

@RestController
@RequestMapping("/geo/api")
public class GeoController {

    public static final String getCoordinates = "getCoordinates";
    public static final String getAddresses = "getAddresses";

    @Autowired
    GeoService geoService;

    @GetMapping(getCoordinates)
    public Coordinates[] getCoordinates(@RequestParam("address") String address) {
        Coordinates[] coordinates = geoService.getCoordinates(address);
        if (coordinates.length == 0) {throw new NotFoundException();}
        return coordinates;
    }

    @GetMapping(getAddresses)
    public Address[] getAddresses(@RequestParam("coordinates") String coordinates) {
        Address[] addresses = geoService.getAddresses(coordinates);
        if (addresses.length == 0) {throw new NotFoundException();}
        return addresses;
    }
}
