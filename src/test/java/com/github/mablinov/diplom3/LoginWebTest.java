package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.apirequests.RequestUserBody;
import com.github.mblinov.diplom3.apirequests.RequestUserLoginBody;
import com.github.mblinov.diplom3.apirequests.UserRequest;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.StartPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginWebTest {
    private static final Random random = new Random();
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;

    private final RequestUserBody userBody = new RequestUserBody(EMAIL, PASSWORD, NAME);
    private final RequestUserLoginBody userLoginBody = RequestUserLoginBody.from(userBody);

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String PASSWORD = "123456";

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that user is login through -Войти в аккаунт- button  ")
    @Description("Load startpage, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromStartPage() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickEnterButtonOnStartPage();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user is login through -Личный кабинет- button  ")
    @Description("Load startpage, click PrivateOffice-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromPrivateOffice() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

        WebDriver driver = factory.getDriver();
        startPage = new StartPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        startPage.loadWindow();
        startPage.clickPrivateOfficeButton();
        loginStellarPage.waitForLoadLoginPage();
        loginStellarPage.loginFormInput(EMAIL, PASSWORD);
        startPage.waitForLoadStartPage();
        assertTrue("Проверьте  заполненные поля, войти не удалось", startPage.checkCreateOrderButton());

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user is login through registration form button  ")
    @Description("Load startpage, move to registration form, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromRegistrationButton() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user is login through recovery form button  ")
    @Description("Load startpage, move to recovery form, click Enter-button, try to login | assert: Change Name of button is displayed")
    public void shouldEnterFromForgotPasswordButton() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }
}
