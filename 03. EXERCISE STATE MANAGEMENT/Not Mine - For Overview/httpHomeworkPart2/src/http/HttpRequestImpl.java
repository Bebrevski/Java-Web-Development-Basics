package http;

import http.base.HttpCookie;
import http.base.HttpRequest;

import java.util.*;
import java.util.stream.Collectors;

public class HttpRequestImpl implements HttpRequest {
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private Map<String, HttpCookie> cookies;
    private String method;
    private String requestUrl;

    public HttpRequestImpl(List<String> requestLines) {
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new LinkedHashMap<>();
        parseRequestLines(requestLines);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return this.cookies;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.putIfAbsent(parameter, value);
    }

    @Override
    public boolean isResource() {
        return false;
    }

    private void parseRequestLines(List<String> lines) {
        String[] methodAndUrl = lines.get(0).split("\\s+");
        this.setMethod(methodAndUrl[0]);
        this.setRequestUrl(methodAndUrl[1]);

        this.parseHeaders(lines.stream().skip(1).collect(Collectors.toList()));
        this.parseBodyParameters(lines.get(lines.size() - 1));
    }

    private void parseHeaders(List<String> lines) {
        for (String line : lines) {
            if(line.equals("\r\n")){
                break;
            }

            String[] kvp = line.split(":\\s+");

            if(kvp[0].startsWith("Cookie")) {
                this.addCookies(kvp[1]);
                continue;
            }

            this.addHeader(kvp[0], kvp[1]);
        }
    }

    private void parseBodyParameters(String line) {
        if(line.equals("\r\n") || line.equals(" ")) {
            return;
        }

        Arrays.stream(line.split("&"))
                .map(x -> x.split("="))
                .forEach(kvp -> {
                    this.bodyParameters.putIfAbsent(kvp[0], kvp[1]);
                });
    }

    private void addCookies(String line) {
        Arrays.stream(line.split("; "))
                .forEach(x -> {
                    String[] kvp = x.split("=");
                    this.cookies.putIfAbsent(kvp[0], new HttpCookieImpl(kvp[0], kvp[1]));
                });
    }
}
