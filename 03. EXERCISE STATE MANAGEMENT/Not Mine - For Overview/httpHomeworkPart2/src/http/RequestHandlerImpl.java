package http;

import constants.WebConstants;
import http.base.HttpRequest;
import http.base.HttpResponse;
import http.base.RequestHandler;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class RequestHandlerImpl implements RequestHandler {
    private List<String> validUrls;
    private List<String> importantHeadersNames;

    public RequestHandlerImpl(List<String> validUrls, List<String> importantHeadersNames) {
        this.validUrls = validUrls;
        this.importantHeadersNames = importantHeadersNames;
    }

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        HttpResponse response = new HttpResponseImpl();

        this.setHeaders(request, response);

        if(!this.validUrls.contains(request.getRequestUrl())) {
            response.setStatusCode(WebConstants.NOT_FOUND_STATUS_CODE);
            response.setContent(WebConstants.NOT_FOUND_EXPLANATION_MESSAGE.getBytes());

            return response;
        }

        if(request.getMethod().equals(WebConstants.HTTP_POST_METHOD) && request.getBodyParameters().isEmpty()) {
            response.setStatusCode(WebConstants.BAD_REQUEST_STATUS_CODE);
            response.setContent(WebConstants.BAD_REQUEST_EXPLANATION_MESSAGE.getBytes());

            return response;
        }

        if(!request.getHeaders().keySet().contains(WebConstants.AUTHORIZATION_HEADER)) {
            response.setStatusCode(WebConstants.NOT_AUTHORIZED_STATUS_CODE);
            response.setContent(WebConstants.NOT_AUTHORIZED_EXPLANATION_MESSAGE.getBytes());

            return response;
        }

        String[] authorizationHeaderContent =
                request.getHeaders().get(WebConstants.AUTHORIZATION_HEADER).split("\\s+");

        String username =
                new String(Base64.getDecoder().decode(authorizationHeaderContent[authorizationHeaderContent.length - 1]));

        if(request.getMethod().equals(WebConstants.HTTP_GET_METHOD)) {
            response.setStatusCode(WebConstants.OK_STATUS_CODE);
            response.setContent(String.format(WebConstants.GET_REQUEST_OK_MESSAGE, username).getBytes());

            return response;
        }

        response.setStatusCode(WebConstants.OK_STATUS_CODE);

        Map.Entry<String, String>[] bodyParameters = request.getBodyParameters().entrySet().toArray(new Map.Entry[request.getBodyParameters().size()]);

        String content = String.format(WebConstants.POST_REQUEST_OK_MESSAGE, username, bodyParameters[0].getValue(),
                bodyParameters[1].getKey(), bodyParameters[1].getValue(),
                bodyParameters[2].getKey(), bodyParameters[2].getValue());

        response.setContent(content.getBytes());

        return response;
    }


    private void setHeaders(HttpRequest request, HttpResponse response) {
        request.getHeaders()
                .entrySet()
                .stream()
                .filter(x -> importantHeadersNames.contains(x.getKey()))
                .forEach(x -> response.addHeader(x.getKey(), x.getValue()));

        if(!response.getHeaders().containsKey("Date")) {
            response.addHeader("Date", LocalDate.now().toString().replaceAll("-", "/"));
        }
    }
}
