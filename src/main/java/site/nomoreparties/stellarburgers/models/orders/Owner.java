package site.nomoreparties.stellarburgers.models.orders;

public class Owner {
    /**
     * POJO создателя заказа
     */

    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;

    public Owner() {
    }

    public Owner(String name, String email, String createdAt, String updatedAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Owner setName(String name) {
        this.name = name;
        return this;
    }

    public Owner setEmail(String email) {
        this.email = email;
        return this;
    }

    public Owner setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Owner setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
