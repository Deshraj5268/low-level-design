package loggerSystem.sinkstrategy;

import java.io.FileWriter;
import java.io.IOException;

public class FileSink implements Sink {

    private String filePath;
    public FileSink(String filePath){
        this.filePath = filePath;
    }
    @Override
    public void write(String logMessage) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {// true for append data
            fileWriter.write(logMessage);
        } catch (IOException exception) {
            System.out.println("fileSink exception" + exception);
        }
    }
}
