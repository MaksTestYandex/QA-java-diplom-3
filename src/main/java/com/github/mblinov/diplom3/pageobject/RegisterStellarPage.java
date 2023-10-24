package com.github.mblinov.diplom3.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterStellarPage {
    private final WebDriver driver;

    // локаторы формы Регистрации
    // Поле ввода всех 3х полей
    private final By inputField = By.xpath(".//input[@class='text input__textfield text_type_main-default']");

    // Кнопка Зарегистрироваться
    private final By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    //Сообщение что пароль некорректен
    private final By inputErrorMassage = By.xpath(".//*[text() = 'Некорректный пароль']");


    public RegisterStellarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Регистрация']")));
    }

    public boolean checkEnterLogo() {
        WebElement element = driver.findElement(inputErrorMassage);
        return element.isDisplayed();
    }


    // Заполнение поля Имя
    public void clickName() {
        driver.findElements(inputField).get(0).click();
    }

    public void inputName(String name) {
        driver.findElements(inputField).get(0).sendKeys(name);
    }

    // Заполнение поля Email
    public void clickEmail() {
        driver.findElements(inputField).get(1).click();
    }

    public void inputEmail(String email) {
        driver.findElements(inputField).get(1).sendKeys(email);
    }

    // Заполнение поля Пароль
    public void clickPassword() {
        driver.findElements(inputField).get(2).click();
    }

    public void inputPassword(String password) {
        driver.findElements(inputField).get(2).sendKeys(password);
    }

    // Кликнуть на кнопку Регистрация
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void registerFormInput(String name, String email, String password) {
        clickName();
        inputName(name);
        clickEmail();
        inputEmail(email);
        clickPassword();
        inputPassword(password);
        clickRegistrationButton();
    }

}
