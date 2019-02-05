package http.base;

public interface RequestHandler {
    HttpResponse handleRequest(HttpRequest request);
}
