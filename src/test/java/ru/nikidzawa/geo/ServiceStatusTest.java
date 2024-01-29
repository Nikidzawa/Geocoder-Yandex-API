package ru.nikidzawa.geo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ServiceStatusTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/geo-status";
    }

    @Test
    public void getServiceHealth() {
        given()
                .get("/health")
                .then()
                .log().body()
                .contentType(ContentType.JSON);
    }
    @Test
    public void getServiceInfo() {
        given()
                .get("/info")
                .then()
                .log().body()
                .contentType(ContentType.JSON);
    }
    @Test
    public void getServiceMetrics() {
        given()
                .get("/metrics")
                .then()
                .log().body()
                .contentType(ContentType.JSON);
    }
}
