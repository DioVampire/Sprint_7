package ru.yandex.praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCourierWithoutRequiredFields {
    CourierClient courierClient;
    private int courierId;
    private int expectedCode;
    private String expectedMessage;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        expectedCode = 400;
        expectedMessage = "Недостаточно данных для входа";
    }

    @After
    public void teardown() {
        courierClient.delete(courierId)

                .extract()
                .path("ok");
    }

    @Test
    @DisplayName("Authorize a courier with empty login field")
    @Description("The test checks the authorization of a courier with blank login field")
    public void courierWithEmptyLoginTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setLogin("");
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Authorize a courier with empty password field")
    @Description("The test checks the authorization of a courier with blank password field")
    public void courierWithEmptyPasswordTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setPassword("");
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Authorize a courier without login")
    @Description("The test checks the authorization of a courier without login field")
    public void courierWithoutLoginTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setLogin(null);
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Authorize a courier without password field")
    @Description("The test checks the authorization of a courier without password field")
    public void courierWithoutPasswordTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setPassword(null);
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage, actualMessage);
    }

}
