package ru.yandex.praktikum.order;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.config.Config;
import ru.yandex.praktikum.courier.Courier;

public class OrderClient extends Config {

    private final String ROOT = "/orders";

    public ValidatableResponse create(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();

    }

    public ValidatableResponse getOrders() {
        return getSpec()
                .get(ROOT)
                .then().log().all();

    }


}
