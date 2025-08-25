package loggerSystem.sinkstrategy;

import java.io.IOException;

public interface Sink {

    void write(String logMessage) throws IOException;
}
