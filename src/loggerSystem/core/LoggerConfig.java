package loggerSystem.core;

import java.util.List;

public class LoggerConfig {

    private List<SinkConfig> sinks;
    private LoggerType loggerType;

    public LoggerConfig(List<SinkConfig> sinks, LoggerType loggerType) {
        this.sinks = sinks;
        this.loggerType = loggerType;
    }

    public List<SinkConfig> getSinks() {
        return sinks;
    }

    public LoggerType getLoggerType() {
        return loggerType;
    }
}
