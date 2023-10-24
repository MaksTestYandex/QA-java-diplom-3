package com.github.mblinov.diplom3.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivateOfficePage {

    private final WebDriver driver;

    private final By profileLogo = By.xpath(".//*[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//*[text() = 'Выход']");

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void waitForLoadPrivateOfficePage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLogo));
    }

    public boolean checkProfileLogo() {
        WebElement element = driver.findElement(profileLogo);
        return element.isDisplayed();
    }

    public PrivateOfficePage(WebDriver driver) {
        this.driver = driver;
    }


}
