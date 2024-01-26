package ru.nikidzawa.geo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;
import ru.nikidzawa.geo.services.GeoService;

@RestController
@RequestMapping("/geo/api")
public class GeoController {

    public static final String getCoordinates = "getCoordinates";
    public static final String getAddresses = "getAddresses";

    @Autowired
    GeoService geoService;

    @GetMapping(getCoordinates)
    public Coordinates[] getCoordinates(@RequestParam("coordinates") String address) {
        Coordinates[] coordinates = geoService.getCoordinates(address);
        if (coordinates.length == 0) {
            return null;
        } else return coordinates;
    }

    @GetMapping(getAddresses)
    public Address[] getAddresses (@RequestParam("address") String coordinates) {
        Address[] addresses = geoService.getAddresses(coordinates);
        if (addresses.length == 0) {
            return null;
        } else return addresses;
    }
 }
