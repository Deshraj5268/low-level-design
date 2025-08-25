package loggerSystem.core;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {

    private LoggerConfig configs;
    private int bufferSize;
    private BlockingDeque<LogEntry> queue;
    private Thread workerThread;
    private boolean running = true;

    // sync call
    private ReentrantLock lock = new ReentrantLock();



    public Logger(LoggerConfig loggerConfig, int bufferSize){
        this.configs = loggerConfig;
        if(configs.getLoggerType() == LoggerType.ASYNC){
            this.bufferSize = bufferSize;
            this.queue = new LinkedBlockingDeque<>(bufferSize);
            this.workerThread = new Thread(this :: processQueue);
            this.workerThread.start();
        }
    }

    public Logger(LoggerConfig loggerConfig){
        this.configs = loggerConfig;
        if(configs.getLoggerType() == LoggerType.SYNC) {
            this.queue = null;
            this.workerThread = null;
        }
    }

    public void log(LogLevel logLevel, String msg){
        // async
        if(configs.getLoggerType() == LoggerType.ASYNC){
            try {
                queue.put(new LogEntry(logLevel, msg));
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }else{
            lock.lock();
            try {
                writeToSink(new LogEntry(logLevel, msg));
            }finally {
                lock.unlock();
            }
        }
    }

    private void writeToSink(LogEntry logEntry){
        // msg to all sinks
        for (SinkConfig sinkConfig : configs.getSinks()){
            if(logEntry.getLogLevel().ordinal() >= sinkConfig.getLogLevel().ordinal()){ // debug > INFO
                String formatted = String.format(" [%s] [%s] %s",
                        logEntry.getLocalDateTime(), logEntry.getLogLevel(), logEntry.getLog());
               try {
                   sinkConfig.getSink().write(formatted);
               }catch (IOException e){
                   System.out.println(e.getMessage());
               }

            }else {
                System.out.println("log level is not present");
            }
        }
    }
    public void info(String msg){
        log(LogLevel.INFO, msg);
    }
    public void debug(String msg){
        log(LogLevel.DEBUG, msg);
    }
    public void warn(String msg){
        log(LogLevel.WARN, msg);
    }
    public void error(String msg){
        log(LogLevel.ERROR, msg);
    }

    public void fatal(String msg){
        log(LogLevel.FATAL, msg);
    }


    // async
    private void processQueue(){
        while (running || !queue.isEmpty()){
            try {
                //System.out.println("size "+queue.size());
                LogEntry logEntry = queue.take();
                writeToSink(logEntry);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void close(){
        if(configs.getLoggerType() == LoggerType.ASYNC){
            running = false;
            workerThread.interrupt();
            try {
                workerThread.join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("closing log");
        }
    }
}
