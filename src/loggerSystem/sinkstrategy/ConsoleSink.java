package loggerSystem.sinkstrategy;

public class ConsoleSink implements Sink {
    @Override
    public void write(String logMessage) {
        System.out.println(logMessage);
    }
}
