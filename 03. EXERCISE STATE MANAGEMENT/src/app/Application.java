package app;

import app.http.HttpRequest;
import app.http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        String request = getRequest();

        System.out.println(request);
        HttpRequest httpRequest = new HttpRequestImpl(request);
    }

    private static List<String> getValidUrls() throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split("\\s+")));
    }

    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            request.append(line).append(System.lineSeparator());
        }

        request.append(System.lineSeparator());

        if ((line = reader.readLine()) != null && !line.isEmpty()) {
            request.append(line);
        }

        return request.toString();
    }
}
