package com.github.mblinov.diplom3.api;

import com.github.mblinov.diplom3.TestVariables;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    public static final String BASE_PATH = "api";

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(TestVariables.URL)
                .setBasePath(BASE_PATH)
                .build();
    }

}
