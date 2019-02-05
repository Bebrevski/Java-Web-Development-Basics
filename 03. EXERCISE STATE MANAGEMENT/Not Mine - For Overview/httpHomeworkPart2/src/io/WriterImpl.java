package io;

import io.base.Writer;

public class WriterImpl implements Writer {
    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
