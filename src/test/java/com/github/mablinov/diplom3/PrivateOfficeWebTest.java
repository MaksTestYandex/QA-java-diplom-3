package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.PrivateOfficePage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class PrivateOfficeWebTest {
    private WebDriver driver;
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;

    private PrivateOfficePage privateOfficePage;

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser77@yandex.ru";
    private static final String PASSWORD = "123456";

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
    public void shouldEnterInPrivateOffice() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();

        assertTrue("Станица личного кабинета не загрузилась", privateOfficePage.checkProfileLogo());
    }
    @Test
    public void shouldMoveFromPrivateOfficeToConstructor() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        startPage.clickConstructorButton();
        startPage.waitForLoadLoginPage();

        assertTrue("Перейтии не удалось", startPage.checkCreateOrderButton());;
    }
    @Test
    public void shouldMoveFromPrivateOfficeThroughStellarBurgerLogo() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        startPage.clickStellarBurgersLogo();
        startPage.waitForLoadLoginPage();

        assertTrue("Перейтии не удалось", startPage.checkCreateOrderButton());;
    }
    @Test
    public void shouldLogoutFromAccount() {
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);

        startPage.waitForLoadLoginPage();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        privateOfficePage.clickLogoutButton();
        loginStellarPage.waitForLoadLoginPage();

        assertTrue("Страница входа в аккаунт не загрузилась", loginStellarPage.checkEnterLogo());;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
