package site.nomoreparties.stellarburgers.models.orders;

public class OrderCreateResponse {
    /**
     * POJO ответа на запрос создания заказа
     */

    private boolean success;
    private String name;
    private Order order;

    public OrderCreateResponse() {
    }

    public OrderCreateResponse(boolean success, String name, Order order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public OrderCreateResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public OrderCreateResponse setName(String name) {
        this.name = name;
        return this;
    }

    public OrderCreateResponse setOrder(Order order) {
        this.order = order;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "success=" + success +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
