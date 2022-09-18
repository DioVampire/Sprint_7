package ru.yandex.praktikum.order;

public class OrdersResponse {

    private OrderDetails[] orders;
    private PageInfo pageInfo;
    private AvailableStations[] availableStations;

    public OrderDetails[] getOrders() {
        return orders;
    }

    public void setOrders(OrderDetails[] orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public AvailableStations[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(AvailableStations[] availableStations) {
        this.availableStations = availableStations;
    }

}
