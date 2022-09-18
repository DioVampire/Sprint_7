package ru.yandex.praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreateOrderWithValidDataTest {

    OrderClient orderClient;
    private String[] colorChosen;


    public CreateOrderWithValidDataTest(String[] colorChosen) {
        this.colorChosen = colorChosen;
    }

    @Parameterized.Parameters
    public static Object[][] getOrdersWithCorrectData() {
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}},
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Create four orders with different valid data")
    @Description("The test checks orders created with different data and scooter colors combination")
    public void createOrderWithValidData(){
        Order order = Order.getRandomOrder(colorChosen);
        int trackNumber = orderClient.create(order)
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
        assertNotNull(trackNumber);
        assertNotEquals(0, trackNumber);
    }

}
