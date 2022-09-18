package ru.yandex.praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCourierWithTheSameFieldsTests {
    CourierClient courierClient;
    private int expectedCode;
    private String expectedMessage;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        expectedCode = 409;
        expectedMessage = "Этот логин уже используется. Попробуйте другой.";
    }

    @After
    public void teardown() {
        courierClient.delete(courierId)
                .extract()
                .path("ok");
    }

    @Test
    @DisplayName("Create courier with all the same fields")
    @Description("The test checks the creation of a courier with all the same fields")
    public void courierWithTheSameFields() {
        Courier courier = Courier.getRandomCourier();
        Courier theSameCourier = courier;
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId =  courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        ValidatableResponse creationFailed = courierClient.create(theSameCourier);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

    @Test
    @DisplayName("Create courier with the same login")
    @Description("The test checks the creation of a courier with the same login, other fields are different")
    public void courierWithTheSameLogin() {
        Courier courier = Courier.getRandomCourier();
        Courier theSameLogin = Courier.getCourierWithTheSameLogin(courier.getLogin());
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId =  courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
        ValidatableResponse creationFailed = courierClient.create(theSameLogin);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

}
