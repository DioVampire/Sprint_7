package ru.yandex.praktikum.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {

    private String login;
    private String password;

    public CourierCredentials(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier);
    }


}
