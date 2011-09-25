package ch.qos.logback.demo;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextListener implements ServletContextListener {

  static public final String TEST_GROUP = "TEST_GROUP";

  Logger logger = LoggerFactory.getLogger(ContextListener.class);

  Runner runner;
  String servletContextName;

  public ContextListener() {
  }

  public void contextInitialized(ServletContextEvent sce) {
    ClassLoader ctcc = Thread.currentThread().getContextClassLoader();
    logger.debug("Classload hashcode is " + ctcc.hashCode());

    ServletContext servletContext = sce.getServletContext();
    servletContextName = servletContext.getServletContextName();
    logger
        .debug("Initializing for ServletContext [" + servletContextName + "]");
    runner = new Runner(servletContextName);

    try {
      runner.start();
    } catch (Exception e) {
      logger.error("Failed to configure Logback Demo", e);
    }

  }

  public void contextDestroyed(ServletContextEvent arg0) {
    logger.debug("Shutting down ServletContext [" + servletContextName + "]");

    ClassLoader ctcc = Thread.currentThread().getContextClassLoader();
    logger.debug("Classload hashcode is " + ctcc.hashCode());

    if (runner != null) {
      runner.stop();
    }
  }

}
