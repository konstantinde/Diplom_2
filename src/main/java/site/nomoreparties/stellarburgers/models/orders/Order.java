package site.nomoreparties.stellarburgers.models.orders;

import java.util.List;

public class Order {
    /**
     * POJO заказа
     */

    private List<Ingredient> ingredients;
    private String _id;
    private Owner owner;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;
    private float price;

    public Order() {
    }

    public Order(List<Ingredient> ingredients, String _id, Owner owner, String status, String name, String createdAt,
                 String updatedAt, int number, float price) {
        this.ingredients = ingredients;
        this._id = _id;
        this.owner = owner;
        this.status = status;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
        this.price = price;
    }

    public Order setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Order set_id(String _id) {
        this._id = _id;
        return this;
    }

    public Order setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public Order setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Order setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Order setNumber(int number) {
        this.number = number;
        return this;
    }

    public Order setPrice(float price) {
        this.price = price;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String get_id() {
        return _id;
    }

    public Owner getOwner() {
        return owner;
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

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ingredients=" + ingredients +
                ", _id='" + _id + '\'' +
                ", owner=" + owner +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", number=" + number +
                ", price=" + price +
                '}';
    }
}
