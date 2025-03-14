package com.example.task04;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Logger {
    private static final ArrayList<Logger> loggers = new ArrayList<>();
    private final ArrayList<MessageHandler> handlers = new ArrayList<>();
    private final String name;
    private LogLevel level;

    public enum LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public Logger(String name) {
        this.name = name;
        this.level = LogLevel.DEBUG;
        loggers.add(this);
    }

    public static Logger getLogger(String name) {
        for (Logger logger : loggers) {
            if (logger.getName().equals(name)) {
                return logger;
            }
        }
        Logger newLogger = new Logger(name);
        loggers.add(newLogger);
        return newLogger;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public String getName(){
        return name;
    }

    public LogLevel getLevel(){
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(LogLevel.DEBUG, format, args);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void info(String format, Object... args) {
        log(LogLevel.INFO, format, args);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(LogLevel.WARNING, format, args);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(LogLevel.ERROR, format, args);
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            String formattedMessage = formatMessage(level, message);
            for (MessageHandler handler : handlers) {
                handler.handle(formattedMessage);
            }
        }
    }

    public void log(LogLevel level, String format, Object... args) {
        if (level.ordinal() >= this.level.ordinal()) {
            String formattedMessage = formatMessage(level, String.format(format, args));
            for (MessageHandler handler : handlers) {
                handler.handle(formattedMessage);
            }
        }
    }

    private String formatMessage(LogLevel level, String message) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return String.format("[%s] %s %s %s - %s", level, date, time, name, message);
    }
}
