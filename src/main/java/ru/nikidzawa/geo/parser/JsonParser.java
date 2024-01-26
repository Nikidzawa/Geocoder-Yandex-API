package ru.nikidzawa.geo.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.nikidzawa.geo.responses.Address;
import ru.nikidzawa.geo.responses.Coordinates;

@Getter
public class JsonParser {
    JsonNode featureMembers;
    int size;

    public JsonParser(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            featureMembers = rootNode
                    .path("response")
                    .path("GeoObjectCollection")
                    .path("featureMember");
            size = featureMembers.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Address[] getAddresses() {
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
    }

    public Coordinates[] getCoordinates() {
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
    }
}
