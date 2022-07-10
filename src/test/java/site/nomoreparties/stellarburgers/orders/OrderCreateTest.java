package site.nomoreparties.stellarburgers.orders;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.clients.OrdersClient;
import site.nomoreparties.stellarburgers.clients.UserClient;
import site.nomoreparties.stellarburgers.helpers.OrderGenerator;
import site.nomoreparties.stellarburgers.helpers.UserGenerator;
import site.nomoreparties.stellarburgers.models.ResultResponse;
import site.nomoreparties.stellarburgers.models.orders.IngredientsData;
import site.nomoreparties.stellarburgers.models.orders.OrderCreateRequest;
import site.nomoreparties.stellarburgers.models.orders.OrderResponse;
import site.nomoreparties.stellarburgers.models.users.RegisterLoginResponse;
import site.nomoreparties.stellarburgers.models.users.User;

import java.util.List;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;


public class OrderCreateTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient;
    OrdersClient ordersClient;
    User user;
    RegisterLoginResponse registerResponse;
    OrderResponse orderResponse;
    ResultResponse resultResponse;
    OrderCreateRequest orderCreateRequest;


    @Before
    public void setUp() {
        // Регистрируем нового пользователя
        userClient = new UserClient();
        ordersClient = new OrdersClient();
        orderCreateRequest = new OrderCreateRequest();
        user = UserGenerator.getRandomUser();
        registerResponse = userClient.register(user).extract().as(RegisterLoginResponse.class);
        // Получаем список ингредиентов
        IngredientsData ingredientsData = ordersClient.getIngredients().extract().as(IngredientsData.class);
        // Формируем случайный список хэшей ингредиентов для заказа (булочка, соус, начинка)
        orderCreateRequest.setIngredients(OrderGenerator.getRandomOrder(ingredientsData.getData()));

    }

    @After
    public void tearDown() {
        // Удаляем пользователя, там где он был создан, т.е. success = true на запрос регистрации
        try {
            if (registerResponse.isSuccess()) {
                userClient.delete(registerResponse.getAccessToken());
            }
        } catch (NullPointerException e) {
            System.out.println("Пользователь не создавался, удалять некого");
        }
    }

    @Test
    @Description("Создание заказа пользователем с авторизацией")
    public void shouldCreateOrderWithAuthorization() {
        // Создаем заказ с авторизацией
        ValidatableResponse createUserOrder = ordersClient.createOrder(orderCreateRequest,
                registerResponse.getAccessToken());
        orderResponse = createUserOrder.extract().as(OrderResponse.class);

        softAssertions.assertThat(createUserOrder.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(orderResponse.isSuccess()).isTrue();
        softAssertions.assertThat(orderResponse.getOrder().getStatus()).isEqualTo("done");
        softAssertions.assertThat(user.getName()).isEqualTo(orderResponse.getOrder().getOwner().getName());
        softAssertions.assertThat(user.getEmail()).isEqualTo(orderResponse.getOrder().getOwner().getEmail());
        softAssertions.assertThat(orderResponse.getOrder().getPrice()).isNotEqualTo(0);
        softAssertions.assertAll();
    }

    @Test
    @Description("Создание заказа пользователем без авторизации")
    public void shouldCreateOrderWithoutAuthorization() {
        // Создаем заказ без авторизации
        ValidatableResponse createUserOrder = ordersClient.createOrder(orderCreateRequest,"");
        orderResponse = createUserOrder.extract().as(OrderResponse.class);

        softAssertions.assertThat(createUserOrder.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(orderResponse.isSuccess()).isTrue();
        softAssertions.assertThat(orderResponse.getOrder().getNumber()).isNotEqualTo(0);
        softAssertions.assertThat(orderResponse.getName()).isNotNull();
        softAssertions.assertThat(orderResponse.getOrder().getStatus()).isNull();
        softAssertions.assertAll();
    }

    @Test
    @Description("Создание заказа без ингредиентов")
    public void shouldNotCreateOrderWithoutIngredients() {
        // Очищаем список ингредиентов
        orderCreateRequest.setIngredients(List.of());
        ValidatableResponse createUserOrder = ordersClient.createOrder(orderCreateRequest,
                registerResponse.getAccessToken());
        resultResponse = createUserOrder.extract().as(ResultResponse.class);

        softAssertions.assertThat(createUserOrder.extract().statusCode()).isEqualTo(SC_BAD_REQUEST);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo("Ingredient ids must be provided");
        softAssertions.assertAll();
    }

    @Test
    @Description("Создание заказа c невалидным хешем ингредиента")
    public void shouldNotCreateOrderWithInvalidIngredientHash() {
        // Ломаем хэш первого ингредиента
        orderCreateRequest.getIngredients().set(0, orderCreateRequest.getIngredients().get(0) + "wrongHash");
        ValidatableResponse createUserOrder = ordersClient.createOrder(orderCreateRequest,
                registerResponse.getAccessToken());

        assertEquals(createUserOrder.extract().statusCode(), SC_INTERNAL_SERVER_ERROR);
    }
}
