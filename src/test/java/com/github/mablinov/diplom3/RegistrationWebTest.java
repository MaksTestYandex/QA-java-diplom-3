package com.github.mablinov.diplom3;

import com.github.mblinov.diplom3.DriverFactory;
import com.github.mblinov.diplom3.apirequests.RequestUserBody;
import com.github.mblinov.diplom3.apirequests.RequestUserLoginBody;
import com.github.mblinov.diplom3.apirequests.UserRequest;
import com.github.mblinov.diplom3.pageobject.LoginStellarPage;
import com.github.mblinov.diplom3.pageobject.RegisterStellarPage;
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

public class RegistrationWebTest {
    private static final Random random = new Random();
    private RegisterStellarPage registerStellarPage;
    private LoginStellarPage loginStellarPage;

    private final RequestUserBody userBody = new RequestUserBody(EMAIL, PASSWORD, NAME);
    private final RequestUserLoginBody userLoginBody = RequestUserLoginBody.from(userBody);

    private static final String NAME = "Ivan";
    private static final String EMAIL = "testUser" + random.nextInt(1000) + "@yandex.ru";
    private static final String PASSWORD = "123456";
    private static final String NOVALIDPASSWORD = "123";

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Check that user account is created")
    @Description("Create user account | assert: Enter logo is displayed")
    public void shouldCreateUser() {
        WebDriver driver = factory.getDriver();

        UserRequest userRequest = new UserRequest();

        registerStellarPage = new RegisterStellarPage(driver);
        loginStellarPage = new LoginStellarPage(driver);
        registerStellarPage.loadWindow();
        registerStellarPage.registerFormInput(NAME, EMAIL, PASSWORD);
        loginStellarPage.waitForLoadLoginPage();
        assertTrue("Проверьте  заполненные поля", loginStellarPage.checkEnterLogo());

        ValidatableResponse loginUser = userRequest.loginUser(userLoginBody);
        ValidatableResponse deleteUser = userRequest.deleteUser(loginUser.extract().path("accessToken"));
        assertEquals("Status code failure!", HttpURLConnection.HTTP_ACCEPTED, deleteUser.extract().statusCode());
    }

    @Test
    @DisplayName("Check that user account is not created")
    @Description("Try to create user account with not valid password | assert: Enter logo is not displayed")
    public void shouldCheckValidPassword() {
        WebDriver driver = factory.getDriver();
        registerStellarPage = new RegisterStellarPage(driver);
        registerStellarPage.loadWindow();
        registerStellarPage.registerFormInput(NAME, EMAIL, NOVALIDPASSWORD);
        assertTrue("Некорректный пароль", registerStellarPage.checkEnterLogo());
    }
}
