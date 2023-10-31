package com.github.mblinov.diplom3.pageobject;

import io.qameta.allure.Step;
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

    @Step("Ожидание загрузки страницы входа")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLogo));
    }

    @Step("Проверка отображения страницы входа")
    public boolean checkEnterLogo() {
        WebElement element = driver.findElement(enterLogo);
        return element.isDisplayed();
    }

    @Step("Нажатие кнопки входа")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Нажатие кнопки регистрации")
    public void clickRegistrationButtonOnLoginPage() {
        driver.findElement(registrationButtonOnLoginPage).click();
    }

    @Step("Нажатие кнопки восстановления пароля")
    public void clickForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click();
    }

    @Step("Заполнение поля Email")
    public void clickEmail() {
        driver.findElements(inputField).get(0).click();
    }

    @Step("Заполнение поля Email")
    public void inputEmail(String email) {
        driver.findElements(inputField).get(0).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void clickPassword() {
        driver.findElements(inputField).get(1).click();
    }

    @Step("Заполнение поля Пароль")
    public void inputPassword(String password) {
        driver.findElements(inputField).get(1).sendKeys(password);
    }

    @Step("Заполнение формы входа")
    public void loginFormInput(String email, String password) {
        clickEmail();
        inputEmail(email);
        clickPassword();
        inputPassword(password);
        clickEnterButton();
    }
}

