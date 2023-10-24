package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.RegisterStellarPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class RegistrationWebTest {
    private static final Random random = new Random();
    private WebDriver driver;
    private RegisterStellarPage orderStellarBurgers;

    private LoginStellarPage loginStellarPage;

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String PASSWORD = "123456";
    private static final String NOVALIDPASSWORD = "123";

    @Before
    public void setUp() {
        // открой браузер Chrome
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "B:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
        //   options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        // перейди на страницу тестового приложения
        driver.get("https://stellarburgers.nomoreparties.site/register");
        // создай объект класса страницы стенда
    }

    @Test
    public void shouldCreateUser() {
        orderStellarBurgers = new RegisterStellarPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        orderStellarBurgers.waitForLoadRegisterPage();
        orderStellarBurgers.registerFormInput(NAME, EMAIL, PASSWORD);
        loginStellarPage.waitForLoadLoginPage();
        assertTrue("Проверьте  заполненные поля", loginStellarPage.checkEnterLogo());
    }

    @Test
    public void shouldCheckValidPassword() {
        orderStellarBurgers = new RegisterStellarPage(driver);
        orderStellarBurgers.waitForLoadRegisterPage();
        orderStellarBurgers.registerFormInput(NAME, EMAIL, NOVALIDPASSWORD);
        assertTrue("Некорректный пароль", orderStellarBurgers.checkEnterLogo());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
