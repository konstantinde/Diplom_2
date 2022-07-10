package site.nomoreparties.stellarburgers.models.orders;

import java.util.List;

public class UserOrdersListResponse {
    /**
     * POJO списка заказов конкретного пользователя
     */

    private boolean success;
    private List<Orders> orders;
    private int total;
    private int totalToday;

    public UserOrdersListResponse() {
    }

    public UserOrdersListResponse(boolean success, List<Orders> orders, int total, int totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public UserOrdersListResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public UserOrdersListResponse setOrders(List<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public UserOrdersListResponse setTotal(int total) {
        this.total = total;
        return this;
    }

    public UserOrdersListResponse setTotalToday(int totalToday) {
        this.totalToday = totalToday;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    @Override
    public String toString() {
        return "UserOrdersListResponse{" +
                "success=" + success +
                ", orders=" + orders +
                ", total=" + total +
                ", totalToday=" + totalToday +
                '}';
    }
}
