package site.nomoreparties.stellarburgers.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.ANY;
import static io.restassured.http.ContentType.JSON;

public class BaseRestClient {
    /**
     * Базовый класс для работы "клиентов". Содержит эндпоинты и спецификации для запросов/ответов.
     */

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    protected RequestSpecification getBaseSpecRequest() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(JSON)
                .setAccept(ANY)
                .setBaseUri(BASE_URL)
                .build();
    }

    protected RequestSpecification getDeleteUserRequest(String bearToken) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", bearToken)
                .addFilter(new AllureRestAssured())
                .setContentType(JSON)
                .setAccept(ANY)
                .setBaseUri(BASE_URL)
                .build();
    }

    protected RequestSpecification getUpdateUserDataRequest(String bearToken) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", bearToken)
                .addFilter(new AllureRestAssured())
                .setContentType(JSON)
                .setAccept(ANY)
                .setBaseUri(BASE_URL)
                .build();
    }
}
