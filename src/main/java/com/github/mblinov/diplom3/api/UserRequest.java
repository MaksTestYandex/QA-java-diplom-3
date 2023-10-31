package com.github.mblinov.diplom3.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserRequest {
    public static final String USER_PATH = "/auth/";

    @Step("Создание нового пользователя")
    public ValidatableResponse createNewUser(RequestUserBody userBody) {
        return given()
                .spec(RequestSpec.requestSpecification())
                .body(userBody)
                .when()
                .log().all()
                .post(USER_PATH + "register")
                .then()
                .log().all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(RequestUserLoginBody userLoginBody) {
        return given()
                .spec(RequestSpec.requestSpecification())
                .body(userLoginBody)
                .when()
                .log().all()
                .post(USER_PATH + "login")
                .then()
                .log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .spec(RequestSpec.requestSpecification())
                .header("Authorization", token)
                .when()
                .log().all()
                .delete(USER_PATH + "user")
                .then()
                .log().all();
    }
}
