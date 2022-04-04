package org.example.usecase;

import org.example.Utils;
import org.example.model.AdultConfirmationPage;
import org.example.model.MainPage;
import org.example.model.ModelViewPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ViewWebcamModelTest {


    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void viewWebcamModelTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            AdultConfirmationPage adultConfirmationPage = new AdultConfirmationPage(webDriver);
            MainPage mainPage = new MainPage(webDriver);
            ModelViewPage modelViewPage = new ModelViewPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            adultConfirmationPage.acceptAdultConfirmation();
            String modelName = mainPage.goToModelViewPage();
            Utils.waitUntilPageLoads(webDriver, 5);
            String selectedModelName = modelViewPage.getModelName();
            assertEquals(modelName, selectedModelName);
        });
        drivers.forEach(WebDriver::quit);
    }

    @Test
    void navigateWebcamModelTest() {
        List<WebDriver> drivers = Utils.getDrivers();
        drivers.parallelStream().forEach(webDriver -> {
            AdultConfirmationPage adultConfirmationPage = new AdultConfirmationPage(webDriver);
            MainPage mainPage = new MainPage(webDriver);
            ModelViewPage modelViewPage = new ModelViewPage(webDriver);
            webDriver.get(Utils.BASE_URL);
            adultConfirmationPage.acceptAdultConfirmation();
            mainPage.goToModelViewPage();
            Utils.waitUntilPageLoads(webDriver, 10);
            String modelName = modelViewPage.getModelName();
            modelViewPage.goToNextModel();
            Utils.waitUntilPageLoads(webDriver, 10);
            modelViewPage.goToPreviousModel();
            Utils.waitUntilPageLoads(webDriver, 10);
            String previousModelName = modelViewPage.getModelName();
            assertEquals(modelName, previousModelName);
        });
        drivers.forEach(WebDriver::quit);
    }
}

