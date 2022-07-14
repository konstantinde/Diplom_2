package site.nomoreparties.stellarburgers.helpers;

import site.nomoreparties.stellarburgers.models.orders.Ingredient;
import site.nomoreparties.stellarburgers.models.orders.IngredientsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {

    public static List<String> getRandomOrder(List<Ingredient> ingredients) {
        Random rand = new Random();
        List<String> ingredientsHashList = new ArrayList<>();
        List<String> bunList = new ArrayList<>();
        List<String> mainList = new ArrayList<>();
        List<String> sauceList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            switch (ingredient.getType()) {
                case "bun":
                    bunList.add(ingredient.get_id());
                    break;
                case "main":
                    mainList.add(ingredient.get_id());
                    break;
                case "sauce":
                    sauceList.add(ingredient.get_id());
                    break;
            }
        }
        // выбираем случайную булочку
        int index = rand.nextInt(bunList.size());
        ingredientsHashList.add(bunList.get(index));
        // выбираем случайный соус
        index = rand.nextInt(sauceList.size());
        ingredientsHashList.add(sauceList.get(index));
        // выбираем случайную начинку
        index = rand.nextInt(mainList.size());
        ingredientsHashList.add(mainList.get(index));

        //возвращаем список ингредиентов
        return ingredientsHashList;

    }
}
