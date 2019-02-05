package io;

import io.base.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReaderImpl implements InputReader {
    private BufferedReader bufferedReader;

    public InputReaderImpl(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String read() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public List<String> readRequestLines() throws IOException {
        List<String> lines = new ArrayList<>();

        String line;

        while ((line = this.bufferedReader.readLine()) != null && line.length() > 0) {
            lines.add(line);
        }

        lines.add(System.lineSeparator());

        while ((line = this.bufferedReader.readLine()) != null && line.length() > 0) {
            lines.add(line);
        }

        return lines;
    }
}
