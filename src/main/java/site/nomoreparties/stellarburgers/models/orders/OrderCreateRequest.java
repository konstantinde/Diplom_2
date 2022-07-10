package site.nomoreparties.stellarburgers.models.orders;


import java.util.List;

public class OrderCreateRequest {
    /**
     * POJO для создания заказа - список хэшей ингредиентов
     */

    private List<String> ingredients;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public OrderCreateRequest setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "OrderCreateRequest{" +
                "ingredients=" + ingredients +
                '}';
    }
}
