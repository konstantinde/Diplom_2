package site.nomoreparties.stellarburgers.models.users;

public class RegisterLoginResponse {
    /**
     * POJO успешной регистрации, логина пользователя в системе
     */

    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;

    public RegisterLoginResponse() {
    }

    public RegisterLoginResponse(boolean success, User user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public RegisterLoginResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public RegisterLoginResponse setUser(User user) {
        this.user = user;
        return this;
    }

    public RegisterLoginResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public RegisterLoginResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return "SuccessRegisterLoginResponse{" +
                "success=" + success +
                ", user=" + user +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
