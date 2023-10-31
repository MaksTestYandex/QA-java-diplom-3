package com.github.mblinov.diplom3.pageobject;

import com.github.mblinov.diplom3.TestVariables;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterStellarPage {
    private final WebDriver driver;

    private final By inputField = By.xpath(".//input[@class='text input__textfield text_type_main-default']");
    private final By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By inputErrorMassage = By.xpath(".//*[text() = 'Некорректный пароль']");

    public RegisterStellarPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void openWindow() {
        driver.get(TestVariables.URL + "register");
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Регистрация']")));
    }

    @Step("Проверка наличия сообщения об ошибке")
    public boolean checkErrorMessage() {
        WebElement element = driver.findElement(inputErrorMassage);
        return element.isDisplayed();
    }

    @Step("Нажатие на поле с логином")
    public void clickName() {
        driver.findElements(inputField).get(0).click();
    }

    @Step("Заполнение поля с логином")
    public void inputName(String name) {
        driver.findElements(inputField).get(0).sendKeys(name);
    }

    @Step("Нажатие на поле с Email")
    public void clickEmail() {
        driver.findElements(inputField).get(1).click();
    }

    @Step("Заполнение поля с Email")
    public void inputEmail(String email) {
        driver.findElements(inputField).get(1).sendKeys(email);
    }

    @Step("Нажатие на поле пароля")
    public void clickPassword() {
        driver.findElements(inputField).get(2).click();
    }

    @Step("Заполнение поля пароля")
    public void inputPassword(String password) {
        driver.findElements(inputField).get(2).sendKeys(password);
    }

    @Step("Нажатие на кнопку регистрации")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Загрузка окна регистрации")
    public void loadWindow() {
        openWindow();
        waitForLoadRegisterPage();
    }

    @Step("Заполнение формы регистрации")
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
