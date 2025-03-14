package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private String fileName;
    private final ChronoUnit rotation;
    private LocalDateTime lastRotation;

    public RotationFileHandler(ChronoUnit rotation) {
        this.rotation = rotation;
        this.lastRotation = LocalDateTime.now();
        rotateFile();
    }

    private void rotateFile() {
        String time = lastRotation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH"));
        this.fileName = time + ".txt";
    }

    @Override
    public void handle(String message) {
        if (LocalDateTime.now().isAfter(lastRotation.plus(1, rotation))) {
            lastRotation = LocalDateTime.now();
            rotateFile();
        }

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + '\n');
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}