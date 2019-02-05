package io.base;

import java.io.IOException;
import java.util.List;

public interface InputReader {
    String read() throws IOException;

    List<String> readRequestLines() throws IOException;
}
