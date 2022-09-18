package ru.yandex.praktikum.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Courier getRandomCourier() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "P@ssw0rd",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithEmptyFirstName() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "P@ssw0rd",
                ""
        );
    }

    public static Courier getCourierWithoutFirstName() {
        String login = RandomStringUtils.randomAlphanumeric(10);
        String password = "P@ssw0rd";

        return new Courier(login, password, null);
    }

    public static Courier getCourierWithEmptyLogin() {
        return new Courier(
                "",
                "P@ssw0rd",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithoutLogin() {
        String password = "P@ssw0rd";
        String firstName = RandomStringUtils.randomAlphanumeric(10);

        return new Courier(null, password, firstName);
    }

    public static Courier getCourierWithEmptyPassword() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getCourierWithoutPassword() {
        String login = RandomStringUtils.randomAlphanumeric(10);
        String firstName = RandomStringUtils.randomAlphanumeric(10);

        return new Courier(login, null, firstName);
    }

    public static Courier getCourierWithTheSameLogin(String theSameLogin) {
        return new Courier(
                theSameLogin,
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }



}
