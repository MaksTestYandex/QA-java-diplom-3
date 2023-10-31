package com.github.mblinov.diplom3.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountProfilePage {
    private final WebDriver driver;

    private final By profileLogo = By.xpath(".//*[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//*[text() = 'Выход']");

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку выхода")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Ожидание загрузки профиля")
    public void waitForLoadAccountProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLogo));
    }

    @Step("Проверка отображения профиля")
    public boolean checkProfileLogo() {
        WebElement element = driver.findElement(profileLogo);
        return element.isDisplayed();
    }

}
