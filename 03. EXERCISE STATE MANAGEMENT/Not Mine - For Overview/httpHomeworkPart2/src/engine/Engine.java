package engine;

import http.HttpRequestImpl;
import http.RequestHandlerImpl;
import http.base.HttpRequest;
import http.base.HttpResponse;
import http.base.RequestHandler;
import io.base.InputReader;
import io.base.Writer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Engine implements Runnable {
    private InputReader reader;
    private Writer writer;

    public Engine(InputReader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
//            List<String> validUrls = Arrays.asList(this.reader.read().split("\\s+"));
//            List<String> importantHeaders = List.of("Date", "Host", "Content-Type");
            List<String> requestLines = this.reader.readRequestLines();

            HttpRequest request = new HttpRequestImpl(requestLines);
//            RequestHandler requestHandler = new RequestHandlerImpl(validUrls, importantHeaders);
//
//            HttpResponse response = requestHandler.handleRequest(request);
//
//            this.writer.write(new String(response.getBytes()));
            request.getCookies()
                    .values()
                    .forEach(x -> {
                        this.writer.write(x.getCookieName() + " <-> " + x.getValue());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
