package site.nomoreparties.stellarburgers.models.orders;

public class OrderResponse {
    /**
     * POJO ответа на запрос создания заказа
     */

    private boolean success;
    private String name;
    private Order order;

    public OrderResponse() {
    }

    public OrderResponse(boolean success, String name, Order order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public OrderResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public OrderResponse setName(String name) {
        this.name = name;
        return this;
    }

    public OrderResponse setOrder(Order order) {
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
