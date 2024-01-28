package ru.nikidzawa.geo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.nikidzawa.geo.parser.JsonParser;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;
import ru.nikidzawa.geo.responses.exceptions.ParserException;
import ru.nikidzawa.geo.responses.exceptions.YandexApiEx;

@Service
public class GeoService {
    public static final String baseUrl = "https://geocode-maps.yandex.ru/1.x";
    public static final String apiKey = "?apikey=c61b6035-6160-4b3a-95e9-4aa048af8dd3";
    public static final String format = "&format=json";

    @Cacheable(value = "geocodeCache", key = "#geocode.toLowerCase()")
    public Address[] getAddresses(String geocode) {
        ResponseEntity<String> responseEntity = getResponse(geocode);
        JsonParser jsonParser = new JsonParser(responseEntity.getBody());
        return jsonParser.getAddresses();
    }

    @Cacheable(value = "addressCache", key = "#address.toLowerCase()")
    public Coordinates[] getCoordinates(String address) {
        ResponseEntity<String> responseEntity = getResponse(address);
        JsonParser jsonParser = new JsonParser(responseEntity.getBody());
        return jsonParser.getCoordinates();
    }

    private static ResponseEntity<String> getResponse(String address) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(
                    baseUrl + apiKey + "&geocode=" + address + format,
                    HttpMethod.GET,
                    null,
                    String.class
            );
        } catch (HttpClientErrorException.Forbidden | HttpClientErrorException.BadRequest ex) {
            try {
                JsonNode rootNode = new ObjectMapper().readTree(ex.getResponseBodyAsString());
                throw new YandexApiEx(rootNode.path("message").asText());
            } catch (JsonProcessingException exception) {
                throw new ParserException();
            }
        }
    }
}