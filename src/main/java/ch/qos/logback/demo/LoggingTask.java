package ch.qos.logback.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggingTask implements Runnable {

  static Marker HOWDY_MARKER = MarkerFactory.getMarker("HOWDY");
  static Marker TOTO = MarkerFactory.getMarker("TOTO");

  static {
    TOTO.add(HOWDY_MARKER);
  }
  Logger logger = LoggerFactory.getLogger(LoggingTask.class);

  String msg;

  int i = 0;

  LoggingTask(String msg) {
    this.msg = msg;
  }

  public void run() {
    if(i % 100 == 99) {
      logger.info(HOWDY_MARKER, msg + " - " + (i++), new Exception("e"));
    } else {
      logger.trace("x {]", i++);
    }
  }

}
