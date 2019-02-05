import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> request = parseRequest();

        request.stream()
                .filter(x -> x.startsWith("Cookie: "))
                .forEach(x -> {
                    String[] tokens = x.split("[;:\\s]+");
                    Arrays.stream(tokens)
                            .skip(1)
                            .forEach(cookie -> {
                                String[] kvp = cookie.split("=");
                                System.out.println(kvp[0] + " < - > " + kvp[1]);
                            });
                });
    }

    private static List<String> parseRequest() throws IOException {
        List<String> requestLines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            requestLines.add(line);
        }

        requestLines.add(System.lineSeparator());

        while ((line = reader.readLine()) != null && line.length() > 0) {
            requestLines.add(line);
        }

        return requestLines;
    }
}
