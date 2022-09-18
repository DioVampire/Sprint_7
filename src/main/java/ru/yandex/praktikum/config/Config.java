package ru.yandex.praktikum.config;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Config {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1";

     protected RequestSpecification getSpec() {
         return given().log().all()
                 .header("Content-Type", "application/json")
                 .baseUri(BASE_URL);
     }

}
