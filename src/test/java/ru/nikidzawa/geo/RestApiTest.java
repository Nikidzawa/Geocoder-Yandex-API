package ru.nikidzawa.geo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class RestApiTest {

	@BeforeAll
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.basePath = "/geo/api";
	}

	@Test
	public void testGetCoordinates() {
		given()
				.param("address", "город Сургут, ХМАО-ЮГРА")
				.when()
				.get("/getCoordinates")
				.then()
				.statusCode(200)
				.log().body()
				.contentType(ContentType.JSON)
				.body("size()", greaterThan(0));
	}

	@Test
	public void testGetAddresses() {
		given()
				.param("coordinates", "73.393032 61.241778")
				.when()
				.get("/getAddresses")
				.then()
				.statusCode(200)
				.log().body()
				.contentType(ContentType.JSON)
				.body("size()", greaterThan(0));
	}
}
