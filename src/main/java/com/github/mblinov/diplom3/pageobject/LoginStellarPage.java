package com.github.mblinov.diplom3.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStellarPage {
    private final WebDriver driver;

    private final By enterLogo = By.xpath(".//*[text() = 'Вход']");

    private final By inputField = By.xpath(".//input[@class='text input__textfield text_type_main-default']");
    private final By enterButton = By.xpath(".//*[text() = 'Войти']");
    private final By forgotPasswordButton = By.xpath(".//*[text() = 'Восстановить пароль']");
    private final By registrationButtonOnLoginPage = By.xpath(".//*[text() = 'Зарегистрироваться']");

    public LoginStellarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLogo));
    }

    public boolean checkEnterLogo() {
        WebElement element = driver.findElement(enterLogo);
        return element.isDisplayed();
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    public void clickRegistrationButtonOnLoginPage() {
        driver.findElement(registrationButtonOnLoginPage).click();
    }

    public void clickForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click();
    }

    // Заполнение поля Email
    public void clickEmail() {
        driver.findElements(inputField).get(0).click();
    }

    public void inputEmail(String email) {
        driver.findElements(inputField).get(0).sendKeys(email);
    }

    // Заполнение поля Пароль
    public void clickPassword() {
        driver.findElements(inputField).get(1).click();
    }

    public void inputPassword(String password) {
        driver.findElements(inputField).get(1).sendKeys(password);
    }

    public void loginFormInput(String email, String password) {
        clickEmail();
        inputEmail(email);
        clickPassword();
        inputPassword(password);
        clickEnterButton();
    }
}

