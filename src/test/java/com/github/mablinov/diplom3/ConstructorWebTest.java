package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.pageobject.StartPage;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ConstructorWebTest {
    private StartPage startPage;
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void shouldMoveToSauceSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickSauce();
        assertTrue("Переход в раздел Соусы не произошел", startPage.checkSectionIsActive());
    }

    @Test
    public void shouldMoveToIngredientsSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickIngredients();
        assertTrue("Переход в раздел Начинки не произошел", startPage.checkSectionIsActive());
    }

    @Test
    public void shouldMoveToBunsSection() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        startPage.loadWindow();
        startPage.clickIngredients();
        startPage.clickBun();
        assertTrue("Переход в раздел Булки не произошел", startPage.checkSectionIsActive());
    }
}
