package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.pageobject.StartPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ConstructorWebTest {
    private StartPage startPage;
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that Sauce-button is highlighted")
    @Description("Load startpage, click Sauce-button  | assert: Button highlight is displayed")
    public void shouldMoveToSauceSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickSauce();
        assertTrue("Переход в раздел Соусы не произошел", startPage.checkSectionIsActive());
    }

    @Test
    @DisplayName("Check that Fillings-button is highlighted")
    @Description("Load startpage, click Fillings-button  | assert: Button highlight is displayed")
    public void shouldMoveToIngredientsSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickFillings();
        assertTrue("Переход в раздел Начинки не произошел", startPage.checkSectionIsActive());
    }

    @Test
    @DisplayName("Check that Buns-button is highlighted")
    @Description("Load startpage, click Bunss-button  | assert: Button highlight is displayed")
    public void shouldMoveToBunsSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickFillings();
        startPage.clickBuns();
        assertTrue("Переход в раздел Булки не произошел", startPage.checkSectionIsActive());
    }
}
