package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.api.RequestUserBody;
import com.github.mblinov.diplom3.api.RequestUserLoginBody;
import com.github.mblinov.diplom3.api.UserRequest;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.AccountProfilePage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountProfileWebTest {
    private static final Random random = new Random();

    private final RequestUserBody userBody = new RequestUserBody(EMAIL, PASSWORD, NAME);
    private final RequestUserLoginBody userLoginBody = RequestUserLoginBody.from(userBody);

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String PASSWORD = "123456";

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void before() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());
    }

    @After
    public void after() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user moves to account profile")
    @Description("Load start page, click Enter-button, login, click AccountProfile-button | assert: Profile logo is displayed")
    public void shouldEnterInAccountProfile() {
        WebDriver driver = factory.getDriver();
        StartPage startPage = new StartPage(driver);
        LoginStellarPage loginStellarPage = new LoginStellarPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickAccountProfileButton();
        accountProfilePage.waitForLoadAccountProfilePage();
        assertTrue("Станица личного кабинета с кнопкой Профиль не загрузилась", accountProfilePage.checkProfileLogo());
    }

    @Test
    @DisplayName("Check that user moves from account profile to constructor page")
    @Description("Load start page, click Enter-button, login, click AccountProfile-button, click Constructor-button | assert: CreateOrder button is displayed")
    public void shouldMoveFromAccountProfileToConstructor() {
        WebDriver driver = factory.getDriver();
        StartPage startPage = new StartPage(driver);
        LoginStellarPage loginStellarPage = new LoginStellarPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickAccountProfileButton();
        accountProfilePage.waitForLoadAccountProfilePage();
        startPage.clickConstructorButton();
        startPage.waitForLoadStartPage();
        assertTrue("Страница с кнопкой Оформить заказ не загрузилась", startPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Check that user moves from account profile to constructor page")
    @Description("Load start page, click Enter-button, login, click AccountProfile-button, click StellarBurger logo | assert: CreateOrder button is displayed")
    public void shouldMoveFromAccountProfileThroughStellarBurgerLogo() {
        WebDriver driver = factory.getDriver();
        StartPage startPage = new StartPage(driver);
        LoginStellarPage loginStellarPage = new LoginStellarPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickAccountProfileButton();
        accountProfilePage.waitForLoadAccountProfilePage();
        startPage.clickStellarBurgersLogo();
        startPage.waitForLoadStartPage();
        assertTrue("Страница с кнопкой Оформить заказ не загрузилась", startPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Check that user is logout")
    @Description("Load start page, click Enter-button, login, click AccountProfile-button, click Logout-button | assert: Enter logo is displayed")
    public void shouldLogoutFromAccount() {
        WebDriver driver = factory.getDriver();
        StartPage startPage = new StartPage(driver);
        LoginStellarPage loginStellarPage = new LoginStellarPage(driver);
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.clickAccountProfileButton();
        accountProfilePage.waitForLoadAccountProfilePage();
        accountProfilePage.clickLogoutButton();
        loginStellarPage.waitForLoadLoginPage();
        assertTrue("Страница с кнопкой Вход не загрузилась", loginStellarPage.checkEnterLogo());
    }
}
