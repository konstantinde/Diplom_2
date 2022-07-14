package site.nomoreparties.stellarburgers.models.orders;

import java.util.List;

public class Orders {
    /**
     * POJO заказа из списка заказа пользователя
     */

    private String _id;
    private List<String> ingredients;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;

    public Orders() {
    }

    public Orders(String _id, List<String> ingredients, String status, String name, String createdAt, String updatedAt, int number) {
        this._id = _id;
        this.ingredients = ingredients;
        this.status = status;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
    }

    public Orders set_id(String _id) {
        this._id = _id;
        return this;
    }

    public Orders setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Orders setStatus(String status) {
        this.status = status;
        return this;
    }

    public Orders setName(String name) {
        this.name = name;
        return this;
    }

    public Orders setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Orders setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Orders setNumber(int number) {
        this.number = number;
        return this;
    }

    public String get_id() {
        return _id;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "_id='" + _id + '\'' +
                ", ingredients=" + ingredients +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", number=" + number +
                '}';
    }
}
