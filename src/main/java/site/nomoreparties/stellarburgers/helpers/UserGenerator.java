package site.nomoreparties.stellarburgers.helpers;

import com.github.javafaker.Faker;
import site.nomoreparties.stellarburgers.models.users.User;

public class UserGenerator {
    /**
     * Генератор случайных данных пользователей для регистрации
     */

    public static User getRandomUser() {
        Faker faker = new Faker();
        return new User()
                .setEmail(faker.internet().emailAddress())
                .setName(faker.name().firstName())
                .setPassword(faker.internet().password());
    }

}
