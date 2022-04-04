package org.example.usecase;

import org.example.Utils;
import org.example.model.AdultConfirmationPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AdultConfirmationTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void acceptAdultConfirmationTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            webDriver.get(Utils.BASE_URL);
            AdultConfirmationPage page = new AdultConfirmationPage(webDriver);
            page.acceptAdultConfirmation();
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void dismissAdultConfirmationTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            webDriver.get(Utils.BASE_URL);
            AdultConfirmationPage page = new AdultConfirmationPage(webDriver);
            page.dismissAdultConfirmation();
        });
        drivers.forEach(WebDriver::quit);
    }
}

