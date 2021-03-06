package site.nomoreparties.stellarburgers.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.models.orders.OrderCreateRequest;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrdersClient extends BaseRestClient {
    /**
     * Класс "клиент" заказов.
     */

    private static final String ORDERS_PATH = "/api/orders/";
    private static final String INGREDIENTS_PATH = "/api/ingredients";


    @Step("Создание заказа с ингридиентами {orderCreateRequest.ingredients}")
    public ValidatableResponse createOrder(OrderCreateRequest orderCreateRequest, String bearerToken) {
        return given()
                .spec(getOrderCreateRequest(bearerToken))
                .log().all()
                .body(orderCreateRequest)
                .when()
                .post(ORDERS_PATH)
                .then()
                .log().all();
    }

    @Step("Получение списка ингредиентов")
    public ValidatableResponse getIngredients() {
        return given()
                .spec(getBaseSpecRequest())
                .log().all()
                .when()
                .get(INGREDIENTS_PATH)
                .then()
                .log().all();
    }

    @Step("Получение списка заказов пользователя")
    public ValidatableResponse getUserOrdersList(String bearerToken) {
        return given()
                .spec(getUserOrdersListRequest(bearerToken))
                .log().all()
                .when()
                .get(ORDERS_PATH)
                .then()
                .log().all();
    }
}
