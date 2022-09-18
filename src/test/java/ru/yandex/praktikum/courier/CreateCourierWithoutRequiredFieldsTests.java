package ru.yandex.praktikum.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateCourierWithoutRequiredFieldsTests {

    CourierClient courierClient;
    private int expectedCode;
    private String expectedMessage;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        expectedCode = 400;
        expectedMessage = "Недостаточно данных для создания учетной записи";
    }

    @Test
    @DisplayName("Create courier with empty login field")
    @Description("The test checks the creation of a courier with empty login field")
    public void courierWithEmptyLogin() {
        Courier courier = Courier.getCourierWithEmptyLogin();
        ValidatableResponse creationFailed = courierClient.create(courier);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

    @Test
    @DisplayName("Create courier without login")
    @Description("The test checks the creation of a courier without login field")
    public void courierWithoutLogin() {
        Courier courier = Courier.getCourierWithoutLogin();
        ValidatableResponse creationFailed = courierClient.create(courier);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

    @Test
    @DisplayName("Create courier with empty password")
    @Description("The test checks the creation of a courier with blank password field")
    public void courierWithEmptyPassword() {
        Courier courier = Courier.getCourierWithEmptyPassword();
        ValidatableResponse creationFailed = courierClient.create(courier);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

    @Test
    @DisplayName("Create courier without password")
    @Description("The test checks the creation of a courier without password field")
    public void courierWithoutPassword() {
        Courier courier = Courier.getCourierWithoutPassword();
        ValidatableResponse creationFailed = courierClient.create(courier);
        int actualCode = creationFailed.extract().statusCode();
        String actualMessage = creationFailed.extract().path("message").toString();
        assertEquals("Статус код", expectedCode, actualCode);
        assertEquals("Текст ошибки", expectedMessage,  actualMessage);
    }

}
