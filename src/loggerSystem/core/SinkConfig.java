package loggerSystem.core;

import loggerSystem.sinkstrategy.Sink;

import java.time.format.DateTimeFormatter;

public class SinkConfig {

    private Sink sink;
    private LogLevel logLevel;
    private DateTimeFormatter formatter;


    public SinkConfig(Sink sink, LogLevel logLevel, String formattedMessage){
        this.sink = sink;
        this.logLevel = logLevel;
        this.formatter = DateTimeFormatter.ofPattern(formattedMessage);
    }

    public Sink getSink() {
        return sink;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
