package loggerSystem.core;

import java.time.LocalDateTime;

public class LogEntry {

    private LogLevel logLevel;
    private String log;
    private LocalDateTime localDateTime;

    public LogEntry(LogLevel logLevel, String log) {
        this.logLevel = logLevel;
        this.log = log;
        this.localDateTime = LocalDateTime.now();
    }

    public String getLog() {
        return log;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LocalDateTime getLocalDateTime(){
        return localDateTime;
    }
}
