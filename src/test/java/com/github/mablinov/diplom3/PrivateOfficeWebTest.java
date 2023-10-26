package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.apirequests.RequestUserBody;
import com.github.mblinov.diplom3.apirequests.RequestUserLoginBody;
import com.github.mblinov.diplom3.apirequests.UserRequest;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.PrivateOfficePage;
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

public class PrivateOfficeWebTest {
    private static final Random random = new Random();
    private StartPage startPage;
    private LoginStellarPage loginStellarPage;
    private PrivateOfficePage privateOfficePage;
    private final RequestUserBody userBody = new RequestUserBody(EMAIL, PASSWORD, NAME);
    private final RequestUserLoginBody userLoginBody = RequestUserLoginBody.from(userBody);

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String PASSWORD = "123456";

//    private static final String EMAIL = "testUser77@yandex.ru";
//    private static final String PASSWORD = "123456";
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that user moves to private office")
    @Description("Load startpage, click Enter-button, login, click PrivateOffice-button | assert: Profile logo is displayed")
    public void shouldEnterInPrivateOffice() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user moves from private office to constructorpage")
    @Description("Load startpage, click Enter-button, login, click PrivateOffice-button, click Constructor-button  | assert: CreateOrder button is displayed")
    public void shouldMoveFromPrivateOfficeToConstructor() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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
        assertTrue("Перейти не удалось", startPage.checkCreateOrderButton());

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user moves from private office to constructorpage")
    @Description("Load startpage, click Enter-button, login, click PrivateOffice-button, click StellarBurger logo  | assert: CreateOrder button is displayed")
    public void shouldMoveFromPrivateOfficeThroughStellarBurgerLogo() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user is logout")
    @Description("Load startpage, click Enter-button, login, click PrivateOffice-button, click Logout-button  | assert: Enter logo is displayed")
    public void shouldLogoutFromAccount() {
        UserRequest userRequest = new UserRequest();
        ValidatableResponse createNewUser = userRequest.createNewUser(userBody);
        assertEquals("Status code failure!", HttpURLConnection.HTTP_OK, createNewUser.extract().statusCode());

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

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

}
