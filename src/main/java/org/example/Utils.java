package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "drivers/chromedriver.exe";
    public static final String FIREFOX_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_SYSTEM_PROPERTY_PATH = "drivers/geckodriver";
    public static final String BASE_URL = "https://bongacams.com/";

    public static final String CORRECT_LOGIN = "labitmo";
    public static final String CORRECT_PASSWORD = "Horny_itmo_girl1337";
    public static final String WRONG_PASSWORD = "ktu-kruto";

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get("bongacams.properties"));
            for (String property : properties) {
                if (property.startsWith("WEB_DRIVER")) {
                    switch (property.toLowerCase().split("=")[1]) {
                        case "chrome":
                            drivers.add(getChromeDriver()); return drivers;
                        case "firefox":
                            drivers.add(getFirefoxDriver()); return drivers;
                        case "both":
                            drivers.add(getChromeDriver());
                            drivers.add(getFirefoxDriver());
                            return drivers;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Web driver is not specified");
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Chrome driver not set properly");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(FIREFOX_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Firefox driver not set properly");
        }
        return new FirefoxDriver();
    }

    public static void enterSiteAndAcceptAdultConfirmation(WebDriver driver) {
        driver.get(Utils.BASE_URL);
        WebElement continueButton = getElementBySelector(driver, By.xpath(".//div[@id='warning_popup']/div/div/div[2]/div[4]/a[1]"));
        continueButton.click();
    }

    public static void login(WebDriver driver) {
        WebElement loginButton = getElementBySelector(driver, By.xpath(".//*[@id='header_login']/a"));
        loginButton.click();
        WebElement loginInput = getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_username']"));
        WebElement loginPassword = getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_password']"));
        WebElement authButton = getElementBySelector(driver, By.xpath(".//*[@id='header_login_popup']/form/div/div[2]/button"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(CORRECT_LOGIN);
        loginPassword.sendKeys(CORRECT_PASSWORD);
        authButton.click();
    }

    public static void prepareDrivers() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        System.setProperty(FIREFOX_SYSTEM_PROPERTY_NAME, FIREFOX_SYSTEM_PROPERTY_PATH);
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, 10);
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitUntilPageLoads(WebDriver driver, long timeout) {
        WebDriverWait waitDriver = new WebDriverWait(driver, timeout);
        waitDriver.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
