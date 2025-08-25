package loggerSystem;

import loggerSystem.core.LogLevel;
import loggerSystem.core.LoggerConfig;
import loggerSystem.core.LoggerType;
import loggerSystem.core.SinkConfig;
import loggerSystem.sinkstrategy.ConsoleSink;
import loggerSystem.sinkstrategy.FileSink;
import loggerSystem.core.Logger;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;

public class LoggerDriver {

    public static void main(String[] args) {
        SinkConfig consoleConfig = new SinkConfig(new ConsoleSink(), LogLevel.DEBUG, "yyyy-MM-dd HH:mm:ss");

        SinkConfig fileConfig = new SinkConfig(new FileSink("C:\\Users\\deshr\\OneDrive\\Desktop\\Newfolder/file.log"), LogLevel.INFO, "yyyy-MM-dd HH:mm:ss");

        List<SinkConfig> sinks = Arrays.asList(consoleConfig, fileConfig);
        LoggerConfig asyncLoggerConfig = new LoggerConfig(sinks,
                LoggerType.ASYNC);

        Logger logger = new Logger(asyncLoggerConfig, 2);
       for(int i=0;i<10;i++) {
           logger.info("async program "+i+Thread.currentThread().getName());
       }
        logger.close();

        LoggerConfig syncLoggerConfig = new LoggerConfig(sinks,
                LoggerType.SYNC);
        Logger syncLog = new Logger(syncLoggerConfig);
        syncLog.info( "sync program");
    }
}
