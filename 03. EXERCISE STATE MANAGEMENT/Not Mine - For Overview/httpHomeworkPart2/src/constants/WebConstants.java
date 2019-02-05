package constants;

public final class WebConstants {
    public static final int NOT_FOUND_STATUS_CODE = 404;
    public static final String NOT_FOUND_EXPLANATION_MESSAGE = "The requested functionality was not found.";
    public static final int BAD_REQUEST_STATUS_CODE = 400;
    public static final String BAD_REQUEST_EXPLANATION_MESSAGE = "There was an error with the requested functionality due to malformed request.";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int NOT_AUTHORIZED_STATUS_CODE = 401;
    public static final String NOT_AUTHORIZED_EXPLANATION_MESSAGE = "You are not authorized to access the requested functionality.";
    public static final int OK_STATUS_CODE = 200;
    public static final String GET_REQUEST_OK_MESSAGE = "Greetings %s!";
    public static final String POST_REQUEST_OK_MESSAGE = "Greetings %s! You have successfully created %s with %s – %s, %s – %s.";
    public static final String OK_STATUS_CODE_MESSAGE = "OK";
    public static final String BAD_REQUEST_STATUS_CODE_MESSAGE = "Bad Request";
    public static final String UNAUTHORIZED_STATUS_CODE_MESSAGE = "Unauthorized";
    public static final String NOT_FOUND_STATUS_CODE_MESSAGE = "Not Found";
    public static final String PROTOCOL = "HTTP/1.1";
    public static final String HTTP_GET_METHOD = "GET";
    public static final String HTTP_POST_METHOD = "POST";

    private WebConstants() { }


    public static String getStatusCodeMessage(int statusCode) {

        switch (statusCode) {
            case 200:
                return OK_STATUS_CODE_MESSAGE;
            case 400:
                return BAD_REQUEST_STATUS_CODE_MESSAGE;
            case 401:
                return UNAUTHORIZED_STATUS_CODE_MESSAGE;
            case 404:
                return NOT_FOUND_STATUS_CODE_MESSAGE;
            default: return "";
        }

    }
}
