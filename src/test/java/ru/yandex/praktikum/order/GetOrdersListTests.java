package ru.yandex.praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class GetOrdersListTests {

    OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Get orders list")
    @Description("Get orders list without any predefined data")
    public void getOrdersTest(){
        OrdersResponse response = orderClient.getOrders()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().as(OrdersResponse.class);
        OrderDetails[] actualResponse = response.getOrders();
        assertNotNull(actualResponse);
    }

    @Test
    @DisplayName("Get orders list for a specific order")
    @Description("Get orders list with an order's id")
    public void getOrdersWithOrderIdTest(){
        Order order = Order.getRandomOrder(null);
        orderClient.create(order);
        OrdersResponse response = orderClient. getOrders()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().as(OrdersResponse.class);
        int OrderId = response.getOrders()[0].getId();
        assertNotEquals(0, OrderId);
    }

}
