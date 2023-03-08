package ru.yandex.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.config.Config;

import static io.restassured.RestAssured.given;

public class CourierClient extends Config {

    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String COURIER = ROOT + "/{courierId}";

    public ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();

    }


    public ValidatableResponse login(CourierCredentials creds) {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();

    }

    public ValidatableResponse delete(int courierId) {
        return getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
