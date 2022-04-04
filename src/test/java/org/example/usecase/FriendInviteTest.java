package org.example.usecase;

import org.example.Utils;
import org.example.model.AdultConfirmationPage;
import org.example.model.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FriendInviteTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void friendInviteTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            AdultConfirmationPage adultConfirmationPage = new AdultConfirmationPage(webDriver);
            MainPage mainPage = new MainPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            adultConfirmationPage.acceptAdultConfirmation();
            mainPage.doLogin();
            mainPage.inviteFriend();
            WebElement successMessage = Utils.getElementBySelector(webDriver, By.xpath("/html/body/div[1]/div[8]/div/div/div/div/div/div[3]/div[3]/div[1]/div[2]"));
            assertEquals("Ваше приглашение отправлено.", successMessage.getText());
        });
        drivers.forEach(WebDriver::quit);
    }
}

