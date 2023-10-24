package com.github.mblinov.diplom3.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    private final WebDriver driver;
    private final By stellarBurgersLogo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By assembleBurgerText = By.xpath(".//*[text() = 'Соберите бургер']");

    private final By enterButtonOnStartPage = By.xpath(".//*[text() = 'Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//*[text() = 'Оформить заказ']");
    private final By privateOfficeButton = By.xpath(".//*[text() = 'Личный Кабинет']");
    private final By сonstructorButton = By.xpath(".//*[text() = 'Конструктор']");

    public void clickEnterButtonOnStartPage() {
        driver.findElement(enterButtonOnStartPage).click();
    }

    public void clickPrivateOfficeButton() {
        driver.findElement(privateOfficeButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(сonstructorButton).click();
    }

    public void clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }

    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerText));
    }

    public boolean checkCreateOrderButton() {
        WebElement element = driver.findElement(createOrderButton);
        return element.isDisplayed();
    }

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }
}
