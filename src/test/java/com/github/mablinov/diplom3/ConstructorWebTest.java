package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ConstructorWebTest {
    private WebDriver driver;
    private StartPage startPage;

    @Before
    public void setUp() {
        // открой браузер Chrome
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "B:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
        //   options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // перейди на страницу тестового приложения
        driver.get("https://stellarburgers.nomoreparties.site");
        // создай объект класса страницы стенда
    }

    @Test
    public void shouldMoveToSauceSection() {
        startPage = new StartPage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickSauce();
        assertTrue("Переход в раздел Соусы не произошел", startPage.checkSectionIsActive());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
