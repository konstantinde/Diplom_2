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

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class UserRegisterTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient;
    User user;
    RegisterLoginResponse registerResponse;
    ResultResponse resultResponse;
    public static final String USER_REGISTER_MSG_FALSE = "Email, password and name are required fields";

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
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
    @Description("Регистрация пользователя")
    public void shouldRegisterUser() {
        ValidatableResponse registerResponse = userClient.register(user);
        this.registerResponse = registerResponse.extract().as(RegisterLoginResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(this.registerResponse.isSuccess()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя, который уже зарегистрирован")
    public void shouldNotRegisterExistUser() {
        // Регистрация пользователя
        registerResponse = userClient.register(user).extract().as(RegisterLoginResponse.class);
        // Повторная регистрация этого же пользователя
        ValidatableResponse registerExistUser = userClient.register(user);
        resultResponse = registerExistUser.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerExistUser.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo("User already exists");
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя без email (null)")
    public void shouldNotRegisterUserWithoutEmail() {
        ValidatableResponse registerResponse = userClient.register(user.setEmail(null));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя c пустым email")
    public void shouldNotRegisterUserWithEmptyEmail() {
        ValidatableResponse registerResponse = userClient.register(user.setEmail(""));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя без name (null)")
    public void shouldNotRegisterUserWithoutName() {
        ValidatableResponse registerResponse = userClient.register(user.setName(null));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя c пустым name")
    public void shouldNotRegisterUserWithEmptyName() {
        ValidatableResponse registerResponse = userClient.register(user.setName(""));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя без password (null)")
    public void shouldNotRegisterUserWithoutPassword() {
        ValidatableResponse registerResponse = userClient.register(user.setPassword(null));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }

    @Test
    @Description("Регистрация пользователя c пустым password")
    public void shouldNotRegisterUserWithEmptyPassword() {
        ValidatableResponse registerResponse = userClient.register(user.setPassword(""));
        resultResponse = registerResponse.extract().as(ResultResponse.class);

        softAssertions.assertThat(registerResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(resultResponse.getMessage()).isEqualTo(USER_REGISTER_MSG_FALSE);
        softAssertions.assertAll();
    }
}
