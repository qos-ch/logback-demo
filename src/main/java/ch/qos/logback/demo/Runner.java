package ch.qos.logback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {

  Logger logger = LoggerFactory.getLogger(Runner.class);
  ScheduledExecutorService scheduledExecutorService;

  Runner(String applicationNam) {
    scheduledExecutorService = Executors.newScheduledThreadPool(2);
  }

  public void start() {
    addTask();
  }

  void addTask() {
    Runnable runnable = new LoggingTask("Howdydy-diddly-ho");
    scheduledExecutorService.scheduleAtFixedRate(runnable,
            0, 100, TimeUnit.MILLISECONDS);
  }

  public void stop() {
    scheduledExecutorService.shutdownNow();
    scheduledExecutorService = null;
  }
}
