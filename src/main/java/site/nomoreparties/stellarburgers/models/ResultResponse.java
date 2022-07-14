package site.nomoreparties.stellarburgers.models;

public class ResultResponse {
    /**
     * POJO ошибочного ответа
     */
    private boolean success;
    private String message;

    public ResultResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public ResultResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public ResultResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
