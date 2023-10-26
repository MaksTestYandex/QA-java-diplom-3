package com.github.mblinov.diplom3.apirequests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static final String BASE_PATH = "api";

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH)
                .build();
    }

}
