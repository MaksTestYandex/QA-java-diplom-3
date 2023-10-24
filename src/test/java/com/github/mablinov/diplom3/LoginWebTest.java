package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.RegisterStellarPage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class LoginWebTest {
    private static final Random random = new Random();
    private WebDriver driver;
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;

    private static final String NAME = "Ivan";
   // private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String EMAIL = "testUser77@yandex.ru";
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
        driver.get("https://stellarburgers.nomoreparties.site");
        // создай объект класса страницы стенда
    }

    @Test
    public void shouldEnterFromStartPage() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL,PASSWORD);
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }
    @Test
    public void shouldEnterFromPrivateOffice() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickPrivateOfficeButton();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL,PASSWORD);
        startPage.waitForLoadLoginPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }
    @Test
    public void shouldEnterFromRegistrationButton() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.clickRegistrationButtonOnLoginPage();
        loginStellarPage.clickEnterButton();
        loginStellarPage.loginFormInput(EMAIL,PASSWORD);
        startPage.waitForLoadLoginPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }
    @Test
    public void shouldEnterFromForgotPasswordButton() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.clickForgotPasswordButton();
        loginStellarPage.clickEnterButton();
        loginStellarPage.loginFormInput(EMAIL,PASSWORD);
        startPage.waitForLoadLoginPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
