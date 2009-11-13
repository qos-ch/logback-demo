package ch.qos.logback.demo.prime;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * NumberCruncher factors positive integers.
 */
public interface NumberCruncher extends Remote {
  /**
   * Factor a positive integer <code>number</code> and return its
   * <em>distinct</em> factor's as an integer array.
   * */
  Long[] factor(long number) throws RemoteException;
}
