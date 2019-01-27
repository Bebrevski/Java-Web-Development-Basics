package app.http;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private Map<String, String> bodyParams;
    private boolean isResource;

    public HttpRequestImpl(String request) {
        this.init(request);
    }

    private void init(String request) {
        this.setMethod(request.split("\\s+")[0]);
        this.setRequestUrl(request.split("\\s+")[1]);
        this.headers = new LinkedHashMap<>();
        this.bodyParams = new LinkedHashMap<>();

        Arrays.stream(request.split(System.lineSeparator()))
                .skip(1)
                .forEach(headerKvp -> {
                    String[] header = headerKvp.split(": ");

                    this.addHeader(header[0], header[1]);
                });
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParams() {
        return Collections.unmodifiableMap(this.bodyParams);
    }

    @Override
    public boolean isResource() {
        return this.isResource;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    @Override
    public void addBodyParams(String key, String value) {
        this.bodyParams.put(key, value);
    }
}
