package com.github.mblinov.diplom3.pageobject;

import com.github.mblinov.diplom3.TestVariables;
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
    private final By accountProfileButton = By.xpath(".//*[text() = 'Личный Кабинет']");
    private final By constructorButton = By.xpath(".//*[text() = 'Конструктор']");
    private final By bunsSection = By.xpath(".//span[contains(text() , 'Булки')]");
    private final By sauceSection = By.xpath(".//span[contains(text() , 'Соусы')]");
    private final By fillingsSection = By.xpath(".//span[contains(text() , 'Начинки')]");
    private final By sectionNameIsActive = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openWindow() {
        driver.get(TestVariables.URL);
    }

    public void clickEnterButtonOnStartPage() {
        driver.findElement(enterButtonOnStartPage).click();
    }

    public void clickAccountProfileButton() {
        driver.findElement(accountProfileButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }

    public void clickBuns() {
        driver.findElement(bunsSection).click();
    }

    public void clickSauce() {
        driver.findElement(sauceSection).click();
    }

    public void clickFillings() {
        driver.findElement(fillingsSection).click();
    }

    public void waitForLoadStartPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerText));
    }

    public boolean checkCreateOrderButton() {
        WebElement element = driver.findElement(createOrderButton);
        return element.isDisplayed();
    }

    public boolean checkSectionIsActive() {
        WebElement element = driver.findElement(sectionNameIsActive);
        return element.isDisplayed();
    }

    public void loadWindow() {
        openWindow();
        waitForLoadStartPage();
    }

}
