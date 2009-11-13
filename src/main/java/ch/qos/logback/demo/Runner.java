package ch.qos.logback.demo;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.loading.MLet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

  Logger logger = LoggerFactory.getLogger(Runner.class);


  ScheduledExecutorService scheduledExecutorService;
  List<ObjectName> objectNameList = new ArrayList<ObjectName>();
  MBeanServer mbs;

  Random random = new Random(897);
  ObjectName mletON = null;

  public Runner(String applicationName) {
    this(applicationName, ManagementFactory.getPlatformMBeanServer());
  }

  Runner(String groupName, MBeanServer mbs) {

    this.mbs = mbs;
    scheduledExecutorService = Executors.newScheduledThreadPool(2);
    try {
      mletON = new ObjectName(Constants.MGMNT_DOMAIN + ":Group=" + groupName
          + ",Name=Mlet");
    } catch (MalformedObjectNameException e) {
      logger.debug("Failed to create object name for MLet", e);
    }

  }

  void registerMlet() {
    try {
      MLet mlet = new MLet(new URL[] {}, this.getClass().getClassLoader(), true);
      objectNameList.add(mletON);
      mbs.registerMBean(mlet, mletON);
    } catch (Exception e) {
      logger.warn("Failed to register mlet " + mletON, e);
    }
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

    try {
      mbs.unregisterMBean(mletON);
    } catch (InstanceNotFoundException e1) {
      logger.error("Failed to degister mlet", e1);
    } catch (MBeanRegistrationException e1) {
      logger.error("Failed to degister mlet", e1);
    }

    for (ObjectName objectName : objectNameList) {
      try {
        mbs.unregisterMBean(objectName);
      } catch (Exception e) {
        logger.error("Failed to deregister mbean " + objectName);
      }
    }
  }


}
