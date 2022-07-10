package site.nomoreparties.stellarburgers.models.orders;

import java.util.ArrayList;

public class CreateOrderRequest {
    /**
     * POJO для создания заказа
     */

    private ArrayList<String> ingredients;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrderRequest setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "createOrderRequest{" +
                "ingredients=" + ingredients +
                '}';
    }
}
