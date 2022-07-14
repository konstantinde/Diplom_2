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
import site.nomoreparties.stellarburgers.models.orders.OrderCreateResponse;
import site.nomoreparties.stellarburgers.models.orders.UserOrdersListResponse;
import site.nomoreparties.stellarburgers.models.users.RegisterLoginResponse;
import site.nomoreparties.stellarburgers.models.users.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class GetUserOrdersListTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient;
    OrdersClient ordersClient;
    User user;
    ResultResponse resultResponse;
    RegisterLoginResponse registerResponse;
    IngredientsData ingredientsData;
    UserOrdersListResponse userOrdersListResponse;


    @Before
    public void setUp() {
        // Регистрируем нового пользователя
        userClient = new UserClient();
        ordersClient = new OrdersClient();
        user = UserGenerator.getRandomUser();
        registerResponse = userClient.register(user).extract().as(RegisterLoginResponse.class);
        // Получаем список ингредиентов
        ingredientsData = ordersClient.getIngredients().extract().as(IngredientsData.class);
        // Формируем случайный список хэшей ингредиентов для заказа (булочка, соус, начинка)


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
    @Description("Получаем список заказов авторизованного пользователя")
    public void shouldGetOrderListAuthorizationUser() {
        // Создаем пару заказов для этого пользователя
        OrderCreateRequest firstOrderRequest = new OrderCreateRequest();
        OrderCreateRequest secondOrderRequest = new OrderCreateRequest();
        ValidatableResponse first = ordersClient.createOrder(firstOrderRequest.setIngredients(OrderGenerator
                .getRandomOrder(ingredientsData.getData())), registerResponse.getAccessToken());
        ValidatableResponse second = ordersClient.createOrder(secondOrderRequest.setIngredients(OrderGenerator
                .getRandomOrder(ingredientsData.getData())), registerResponse.getAccessToken());
        OrderCreateResponse firstOrderResponse = first.extract().as(OrderCreateResponse.class);
        OrderCreateResponse secondOrderResponse = second.extract().as(OrderCreateResponse.class);
        // Получаем список созданных заказов пользователя
        ValidatableResponse getUserOrderList = ordersClient.getUserOrdersList(registerResponse.getAccessToken());
        userOrdersListResponse = getUserOrderList.extract().as(UserOrdersListResponse.class);

        softAssertions.assertThat(getUserOrderList.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(userOrdersListResponse.getOrders().size()).isEqualTo(2);
        softAssertions.assertThat(firstOrderResponse.getOrder().get_id())
                .isEqualTo(userOrdersListResponse.getOrders().get(0).get_id());
        softAssertions.assertThat(secondOrderResponse.getOrder().get_id())
                .isEqualTo(userOrdersListResponse.getOrders().get(1).get_id());
        softAssertions.assertAll();
    }

    @Test
    @Description("Получаем список заказов неавторизованного пользователя")
    public void shouldNotGetOrdersListNotAuthorizationUser() {
        // Создаем заказ
        OrderCreateRequest orderRequest = new OrderCreateRequest();
        ordersClient.createOrder(orderRequest.setIngredients(OrderGenerator
                .getRandomOrder(ingredientsData.getData())), registerResponse.getAccessToken());
        // Получаем список заказов неавторизованного пользователя (передаем пустой токен)
        ValidatableResponse getUserOrderList = ordersClient.getUserOrdersList("");
        resultResponse = getUserOrderList.extract().as(ResultResponse.class);

        softAssertions.assertThat(getUserOrderList.extract().statusCode()).isEqualTo(SC_UNAUTHORIZED);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo("You should be authorised");
        softAssertions.assertAll();
    }
}
