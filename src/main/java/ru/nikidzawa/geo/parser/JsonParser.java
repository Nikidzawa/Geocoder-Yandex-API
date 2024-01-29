package ru.nikidzawa.geo.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;
import ru.nikidzawa.geo.responses.exceptions.NotFoundException;
import ru.nikidzawa.geo.responses.exceptions.ParserException;

@Getter
public class JsonParser {
    JsonNode featureMembers;
    int size;

    public JsonParser(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            featureMembers = rootNode
                    .path("response")
                    .path("GeoObjectCollection")
                    .path("featureMember");
            size = featureMembers.size();
            if (size == 0) {throw new NotFoundException();}
        } catch (NullPointerException | JsonProcessingException ex) {
            throw new ParserException();
        }
    }

    public Address[] getAddresses() {
        try {
            Address[] addresses = new Address[size];
            for (int i = 0; i < size; i++) {
                JsonNode geoObject = featureMembers.get(i).path("GeoObject");

                String text = geoObject
                        .path("metaDataProperty")
                        .path("GeocoderMetaData")
                        .path("text")
                        .asText();

                addresses[i] = Address.builder()
                        .address(text)
                        .build();
            }
            return addresses;
        } catch (NullPointerException ex) {
            throw new ParserException();
        }
    }

    public Coordinates[] getCoordinates() {
        try {
            Coordinates[] coordinates = new Coordinates[size];
            for (int i = 0; i < size; i++) {
                JsonNode geoObject = featureMembers.get(i).path("GeoObject");

                String pos = geoObject
                        .path("Point")
                        .path("pos")
                        .asText();

                coordinates[i] = Coordinates.builder()
                        .coordinates(pos)
                        .build();
            }
            return coordinates;
        } catch (NullPointerException ex) {
            throw new ParserException();
        }
    }
}