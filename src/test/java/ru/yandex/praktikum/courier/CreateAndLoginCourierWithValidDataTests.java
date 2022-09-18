package ru.yandex.praktikum.courier;

import static org.junit.Assert.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateAndLoginCourierWithValidDataTests {

    Courier courier;
    CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        courierClient.delete(courierId)

                .extract()
                .path("ok");
    }


    @Test
    @DisplayName("Create courier with all fields and login")
    @Description("The test checks the creation of a courier with all fields and its authorization")
    public void courierWithAllFieldsTest() {

        boolean isOk = courierClient.create(courier)
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

        assertTrue(isOk);
        assertNotEquals(0, courierId);

        }

    @Test
    @DisplayName("Create courier with empty first name and login")
    @Description("The test checks the creation of a courier with blank first name field and its authorization")
    public void courierWithEmptyFirstNameTest() {

        courier = Courier.getCourierWithEmptyFirstName();

        boolean isOk = courierClient.create(courier)
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

        assertTrue(isOk);
        assertNotEquals(0, courierId);

    }

    @Test
    @DisplayName("Create courier without first name and login")
    @Description("The test checks the creation of a courier without first name field and its authorization")
    public void courierWithoutFirstNameTest() {

        courier = Courier.getCourierWithoutFirstName();

        boolean isOk = courierClient.create(courier)
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");

        assertTrue(isOk);
        assertNotEquals(0, courierId);

    }


}
