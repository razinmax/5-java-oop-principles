package com.example.task04;

import java.util.ArrayList;

public class MemoryHandler implements MessageHandler {

    private final ArrayList<String> buffer = new ArrayList<>();
    private final int bufferLimit;
    private final MessageHandler handler;

    public MemoryHandler(int bufferLimit, MessageHandler handler) {
        this.bufferLimit = bufferLimit;
        this.handler = handler;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);

        if (buffer.size() >= bufferLimit) {
            send();
        }
    }

    public void send() {
        for (String message : buffer) {
            handler.handle(message);
        }
        buffer.clear();
    }
}
