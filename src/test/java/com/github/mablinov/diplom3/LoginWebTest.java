package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class LoginWebTest {
    private static final Random random = new Random();
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;

    // private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String EMAIL = "testUser77@yandex.ru";
    private static final String PASSWORD = "123456";

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that user is login through -Войти в аккаунт- button  ")
    @Description("Load startpage, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromStartPage() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Check that user is login through -Личный кабинет- button  ")
    @Description("Load startpage, click PrivateOffice-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromPrivateOffice() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickPrivateOfficeButton();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Check that user is login through registration form button  ")
    @Description("Load startpage, move to registration form, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromRegistrationButton() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.clickRegistrationButtonOnLoginPage();
        loginStellarPage.clickEnterButton();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Check that user is login through recovery form button  ")
    @Description("Load startpage, move to recovery form, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromForgotPasswordButton() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.clickForgotPasswordButton();
        loginStellarPage.clickEnterButton();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());
    }
}
