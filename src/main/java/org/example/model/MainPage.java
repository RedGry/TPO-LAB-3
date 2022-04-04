package org.example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.example.Utils;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void doLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.CORRECT_PASSWORD);
    }

    public void doWrongLogin() {
        tryLogin(Utils.CORRECT_LOGIN, Utils.WRONG_PASSWORD);
    }

    public void doLogout() {
        Actions actions = new Actions(driver);
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header_login\"]/div/div[3]"))).perform();
        actions.moveToElement(Utils.getElementBySelector(driver, By.xpath("//*[@id=\"header_login\"]/div/div[3]/div[2]/div[1]/ul/li[10]/a"))).click().perform();
        Utils.waitUntilPageLoads(driver, 10);
    }

    public void doFastSearch() {
        WebElement fastSearchButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"bc_tags\"]/div[1]"));
        fastSearchButton.click();
        WebElement russiaRadio = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"fl_quick_search\"]/div[1]/div[3]/div[2]/ul/li[3]/label/span"));
        WebElement searchButton = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"fl_quick_search\"]/div[1]/div[4]/button[1]"));
        russiaRadio.click();
        searchButton.click();
    }

    public String goToModelViewPage() {
        WebElement firstModel = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"mls_models\"]/div[1]/div[1]/a/picture/img"));
        String modelName = Utils.getElementBySelector(driver, By.xpath("//*[@id=\"mls_models\"]/div[1]/div[1]/div[1]/div[1]/a")).getText();
        firstModel.click();
        return modelName;
    }

    public void inviteFriend() {
        Utils.waitUntilPageLoads(driver, 10);
        Utils.getElementBySelector(driver, By.xpath("//a[@href=\"/followers/labitmo/followers\"]")).click();
        Utils.waitUntilPageLoads(driver, 10);
        Utils.getElementBySelector(driver, By.xpath("//a[@href=\"/free-tokens/refer-a-friend\"]")).click();
        Utils.waitUntilPageLoads(driver, 10);
        WebElement emailInput = Utils.getElementBySelector(driver, By.xpath("//input[@id=\"send_invitation_friends_email\"]"));
        emailInput.clear();
        emailInput.sendKeys("abit@ifmo.ru");
        Utils.getElementBySelector(driver, By.xpath("/html/body/div[1]/div[8]/div/div/div/div/div/div[3]/div[3]/div[1]/form/div/div[2]/button")).click();
        Utils.waitUntilPageLoads(driver, 10);
    }

    private void tryLogin(CharSequence login, CharSequence password) {
        WebElement loginButton = Utils.getElementBySelector(driver, By.xpath(".//*[@id='header_login']/a"));
        loginButton.click();
        WebElement loginInput = Utils.getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_username']"));
        WebElement loginPassword = Utils.getElementBySelector(driver, By.xpath(".//*[@id='header_log_in_log_in_password']"));
        WebElement authButton = Utils.getElementBySelector(driver, By.xpath(".//*[@id='header_login_popup']/form/div/div[2]/button"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(login);
        loginPassword.sendKeys(password);
        authButton.click();
    }
}

