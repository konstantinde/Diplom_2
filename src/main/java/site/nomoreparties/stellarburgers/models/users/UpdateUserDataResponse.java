package site.nomoreparties.stellarburgers.models.users;

public class UpdateUserDataResponse {
    /**
     * POJO для успешного ответа на обновление пользователя
     */

    private boolean success;
    private User user;

    public UpdateUserDataResponse() {
    }

    public UpdateUserDataResponse(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public UpdateUserDataResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public UpdateUserDataResponse setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UpdateResponse{" +
                "success=" + success +
                ", user=" + user +
                '}';
    }
}
