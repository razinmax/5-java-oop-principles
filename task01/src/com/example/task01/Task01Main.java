package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("TestLogger");
        Logger logger2 = Logger.getLogger("TestLogger");

        System.out.println("logger1 == logger2: " + (logger1 == logger2));

        logger1.debug("Debug message");
        logger1.info("Info message");
        logger1.warning("Warning message");
        logger1.error("Error message");

        logger1.setLevel(Logger.LogLevel.WARNING);

        logger1.debug("This debug message should not be printed");
        logger1.info("This info message should not be printed");
        logger1.warning("This warning message should be printed");
        logger1.error("This error message should be printed");

        logger1.error("Formatted error: %d + %d = %d", 2, 3, 5);
    }
}