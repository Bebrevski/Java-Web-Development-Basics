import engine.Engine;
import io.InputReaderImpl;
import io.WriterImpl;
import io.base.InputReader;
import io.base.Writer;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class App {
    public static void main(String[] args) {
        InputReader reader = new InputReaderImpl(new BufferedReader(new InputStreamReader(System.in)));
        Writer writer = new WriterImpl();

        Engine engine = new Engine(reader, writer);

        engine.run();
    }
}
