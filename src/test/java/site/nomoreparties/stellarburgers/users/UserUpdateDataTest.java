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
import site.nomoreparties.stellarburgers.models.users.UpdateUserDataResponse;
import site.nomoreparties.stellarburgers.models.users.User;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class UserUpdateDataTest {

    SoftAssertions softAssertions = new SoftAssertions();
    UserClient userClient;
    User user;
    RegisterLoginResponse registerResponse;
    RegisterLoginResponse loginResponse;
    UpdateUserDataResponse updateUserDataResponse;
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
    @Description("Изменение пользовательских данных с авторизацией")
    public void shouldUpdateUserDataWithAuthorization() {
        // Формируем обновлённые поля пользователя добавляя префикс 'update' к почте, логину, паролю
        user.setEmail("update" + user.getEmail());
        user.setName("update" + user.getName());
        user.setPassword("update" + user.getPassword());
        // При обновлении пользователя передаем Bearer токен полученный при регистрации
        ValidatableResponse updateUserDataResponse = userClient.update(user, registerResponse.getAccessToken());
        this.updateUserDataResponse = updateUserDataResponse.extract().as(UpdateUserDataResponse.class);
        // Создаем креды пользоватея для логина в систему, чтобы убедиться в том, что пароль также успешно изменён
        User loginUser = new User(user.getEmail(), user.getPassword());
        loginResponse = userClient.login(loginUser).extract().as(RegisterLoginResponse.class);

        softAssertions.assertThat(updateUserDataResponse.extract().statusCode()).isEqualTo(SC_OK);
        softAssertions.assertThat(this.updateUserDataResponse.isSuccess()).isTrue();
        softAssertions.assertThat(this.loginResponse.isSuccess()).isTrue();
        softAssertions.assertThat(this.updateUserDataResponse.getUser().getEmail()).isEqualTo(user.getEmail());
        softAssertions.assertThat(this.updateUserDataResponse.getUser().getName()).isEqualTo(user.getName());
        softAssertions.assertAll();
    }

    @Test
    @Description("Изменение пользовательских данных без авторизаци")
    public void shouldNotUpdateUserDataWithoutAuthorization() {
        // Формируем обновлённые поля пользователя добавляя префикс 'update' к почте, логину, паролю
        user.setEmail("update" + user.getEmail());
        user.setName("update" + user.getName());
        user.setPassword("update" + user.getPassword());
        // При обновлении пользователя вместо Bearer токена передаем пустое значение
        ValidatableResponse updateUserDataResponse = userClient.update(user, "");
        this.resultResponse = updateUserDataResponse.extract().as(ResultResponse.class);
        // Создаем креды пользоватея для логина в систему, чтобы убедиться в том, что данные не изменились
        User loginUser = new User(user.getEmail(), user.getPassword());
        ResultResponse resultLogin = userClient.login(loginUser).extract().as(ResultResponse.class);

        softAssertions.assertThat(updateUserDataResponse.extract().statusCode()).isEqualTo(SC_UNAUTHORIZED);
        softAssertions.assertThat(this.resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(this.resultResponse.getMessage()).isEqualTo("You should be authorised");
        softAssertions.assertThat(resultLogin.isSuccess()).isFalse();
        softAssertions.assertThat(resultLogin.getMessage()).isEqualTo("email or password are incorrect");
        softAssertions.assertAll();

    }

    @Test
    @Description("Изменение email на уже сушествующий в системе")
    public void shouldNotUpdateUserEmailIfEmailExist() {
        // Генерируем данные еще одного случайного пользователя
        User existUser = UserGenerator.getRandomUser();
        // регистрируем его в системе, сохраняем ответ
        RegisterLoginResponse existUserRegister = userClient.register(existUser).extract().as(RegisterLoginResponse.class);
        // Меняем у основного пользователя почту на почту только что зарегистрированного пользователя
        user.setEmail(existUser.getEmail());
        // Пытаемся обновить
        ValidatableResponse updateUserExistMailResponse = userClient.update(user, registerResponse.getAccessToken());
        this.resultResponse = updateUserExistMailResponse.extract().as(ResultResponse.class);
        // Удаляем второго пользователя
        userClient.delete(existUserRegister.getAccessToken());

        softAssertions.assertThat(updateUserExistMailResponse.extract().statusCode()).isEqualTo(SC_FORBIDDEN);
        softAssertions.assertThat(this.resultResponse.isSuccess()).isFalse();
        softAssertions.assertThat(this.resultResponse.getMessage()).isEqualTo("User with such email already exists");
        softAssertions.assertAll();
    }
}
