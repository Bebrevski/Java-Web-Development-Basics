package http;

import constants.WebConstants;
import http.base.HttpRequest;
import http.base.HttpResponse;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private Map<String, String> headers;
    private int statusCode;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();

    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        StringBuilder result = new StringBuilder();

        result.append(WebConstants.PROTOCOL)
                .append(" ")
                .append(this.getStatusCode())
                .append(" ")
                .append(WebConstants.getStatusCodeMessage(this.getStatusCode()))
                .append(System.lineSeparator());

        for (Map.Entry<String, String> header : headers.entrySet()) {
            result.append(header.getKey()).append(": ").append(header.getValue()).append(System.lineSeparator());
        }

        result.append(System.lineSeparator());
        result.append(new String(this.getContent()));

        return result.toString().getBytes();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }
}
