package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.PrivateOfficePage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class PrivateOfficeWebTest {
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;
    private PrivateOfficePage privateOfficePage;
    private static final String EMAIL = "testUser77@yandex.ru";
    private static final String PASSWORD = "123456";
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that user is login through registration form button  ")
    @Description("Load startpage, move to registration form, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterInPrivateOffice() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();

        assertTrue("Станица личного кабинета не загрузилась", privateOfficePage.checkProfileLogo());
    }

    @Test
    public void shouldMoveFromPrivateOfficeToConstructor() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        startPage.clickConstructorButton();
        startPage.waitForLoadStartPage();

        assertTrue("Перейтии не удалось", startPage.checkCreateOrderButton());
    }

    @Test
    public void shouldMoveFromPrivateOfficeThroughStellarBurgerLogo() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        startPage.clickStellarBurgersLogo();
        startPage.waitForLoadStartPage();

        assertTrue("Перейтии не удалось", startPage.checkCreateOrderButton());
    }

    @Test
    public void shouldLogoutFromAccount() {
        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        privateOfficePage = new PrivateOfficePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickPrivateOfficeButton();
        privateOfficePage.waitForLoadPrivateOfficePage();
        privateOfficePage.clickLogoutButton();
        loginStellarPage.waitForLoadLoginPage();

        assertTrue("Страница входа в аккаунт не загрузилась", loginStellarPage.checkEnterLogo());
    }

}
