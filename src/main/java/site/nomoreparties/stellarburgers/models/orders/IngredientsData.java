package site.nomoreparties.stellarburgers.models.orders;

import java.util.ArrayList;
import java.util.List;

public class IngredientsData {
    /**
     * POJO данных об ингредиентах
     */

    private boolean success;
    private List<Ingredient> data;

    public IngredientsData(boolean success, List<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    public IngredientsData() {
    }

    public IngredientsData setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public IngredientsData setData(ArrayList<Ingredient> data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Ingredient> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "IngredientsData{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
