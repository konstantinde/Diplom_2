package site.nomoreparties.stellarburgers.users;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.clients.UserClient;
import site.nomoreparties.stellarburgers.helpers.UserGenerator;
import site.nomoreparties.stellarburgers.models.ResultResponse;
import site.nomoreparties.stellarburgers.models.users.RegisterLoginResponse;
import site.nomoreparties.stellarburgers.models.users.User;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class UserLoginTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient;
    User user;
    RegisterLoginResponse registerResponse;
    RegisterLoginResponse loginResponse;
    ResultResponse resultResponse;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        registerResponse = userClient.register(user).extract().as(RegisterLoginResponse.class);
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
    @Description("Логин под существующим пользователем")
    public void shouldLoginExistUser() {
        User userCredential = new User(user.getEmail(), user.getPassword());
        ValidatableResponse loginResponse = userClient.login(userCredential);
        this.loginResponse = loginResponse.extract().as(RegisterLoginResponse.class);

        softAssertions.assertThat(loginResponse.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(this.loginResponse.isSuccess()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Description("Логин под несуществующим пользователем")
    public void shouldNotLoginNotExistUser() {
        // конкатенируем имя к почте и паролю, чтобы создать несуществующего в системе пользователя
        User userCredential = new User(user.getName() + user.getEmail(),
                user.getName() + user.getPassword());
        ValidatableResponse loginResponse = userClient.login(userCredential);
        resultResponse = loginResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(loginResponse.extract().statusCode()).isEqualTo(SC_UNAUTHORIZED);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo("email or password are incorrect");
        softAssertions.assertAll();
    }
}
