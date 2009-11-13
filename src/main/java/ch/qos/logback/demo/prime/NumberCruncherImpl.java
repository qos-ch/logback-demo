package ch.qos.logback.demo.prime;

import java.rmi.RemoteException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A simple NumberCruncher implementation that logs its progress when
 * factoring numbers. The purpose of the whole exercise is to show the
 * use of mapped diagnostic contexts in order to distinguish the log
 * output from different client requests.
 * */
public class NumberCruncherImpl implements NumberCruncher {
  static Logger logger = LoggerFactory.getLogger(NumberCruncherImpl.class);
  
  private static int MAX_COUNT_BEFORE_WARN = 1000000;

  public NumberCruncherImpl() throws RemoteException {
  }

  public Long[] factor(long number) throws RemoteException {

    // The information contained within the request is another source
    // of distinctive information. It might reveal the users name,
    // date of request, request ID etc. In servlet type environments,
    // useful information is contained in the HttpRequest or in the  
    // HttpSession.
    //MDC.put("number", new Integer(number));

    logger.info("Beginning to factor.");

    if (number <= 0) {
    	IllegalArgumentException e = new IllegalArgumentException(number +
        " is not a positive integer.");
      logger.error("Bad argument", e);
      //throw e;
      return new Long[] { 0L };
    } else if (number == 1) {
      return new Long[] { 1L };
    }

    Vector<Long> factors = new Vector<Long>();
    long n = number;
    int count = 0;
    for (long i = 2; (i <= n) && ((i * i) <= number); i++) {
      // It is bad practice to place log requests within tight loops.
      // It is done here to show interleaved log output from
      // different requests. 
      
      if (count++ >= MAX_COUNT_BEFORE_WARN) {
        logger.warn("Already tried " + MAX_COUNT_BEFORE_WARN + " factors.");
        count = 0;
      }
      //logger.debug("Trying "+i+" as a factor.");
      logger.debug("Trying {} as a factor.", i);

      if ((n % i) == 0) {
        logger.info("Found factor " + i);
        factors.addElement(i);

        do {
          n /= i;
        } while ((n % i) == 0);
      }

      // Placing artificial delays in tight-loops will also lead to
      // sub-optimal resuts. :-)
      // delay(100);
    }

    if (n != 1) {
      logger.info("Found factor " + n);
      factors.addElement(n);
    }

    int len = factors.size();
    
    Long[] result = new Long[len];
    
    for (int i = 0; i < len; i++) {
      result[i] = factors.elementAt(i).longValue();
    }

    return result;
    
  }                             


  public static void delay(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
  }
}
