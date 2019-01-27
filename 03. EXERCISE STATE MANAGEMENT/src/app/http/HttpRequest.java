package app.http;

import java.util.Map;

public interface HttpRequest {
    String getMethod();

    String getRequestUrl();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParams();

    boolean isResource();

    void setMethod(String method);

    void setRequestUrl(String requestUrl);

    void addHeader(String key, String value);

    void addBodyParams(String key, String value);
}
