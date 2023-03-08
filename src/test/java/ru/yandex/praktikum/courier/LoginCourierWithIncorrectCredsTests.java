package ru.yandex.praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCourierWithIncorrectCredsTests {
    CourierClient courierClient;
    private int courierId;
    private int expectedCode;
    private String expectedMessage;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        expectedCode = 404;
        expectedMessage = "Учетная запись не найдена";
    }

    @After
    public void teardown() {
        courierClient.delete(courierId)

                .extract()
                .path("ok");
    }

    @Test
    @DisplayName("Authorize a courier with wrong login")
    @Description("The test checks the authorization of a courier while using wrong login")
    public void courierWithWrongLoginTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setLogin(courier.getLogin() + RandomStringUtils.randomAlphanumeric(5));
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

    @Test
    @DisplayName("Authorize a courier with wrong password")
    @Description("The test checks the authorization of a courier while using wrong password")
    public void courierWithWrongPasswordTest() {
        Courier courier = Courier.getRandomCourier();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        creds.setPassword(courier.getPassword() + RandomStringUtils.randomAlphanumeric(5));
        ValidatableResponse loginFailed = courierClient.login(creds);
        int actualCode = loginFailed.extract().statusCode();
        String actualMessage = loginFailed.extract().path("message").toString();
        assertEquals("Статус ответа", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }
}
