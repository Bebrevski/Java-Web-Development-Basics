package http;

import http.base.HttpCookie;

public class HttpCookieImpl implements HttpCookie {
    private String cookieName;
    private String cookieValue;

    public HttpCookieImpl(String cookieName, String cookieValue) {
        this.cookieName = cookieName;
        this.cookieValue = cookieValue;
    }

    @Override
    public String getCookieName() {
        return this.cookieName;
    }

    @Override
    public String getValue() {
        return this.cookieValue;
    }
}
