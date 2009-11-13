package ch.qos.logback.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;

public class EnhancedStatusPrinter {
  
  private static final int ABBR_LENGTH = 17;
  private static TargetLengthBasedClassNameAbbreviator abbreviator = new TargetLengthBasedClassNameAbbreviator(ABBR_LENGTH);
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd'T'HH:mm:ss");

  public static String print(Status s) {
    StringBuffer buf = new StringBuffer();
    buf.append(sdf.format(s.getDate()) + " ");
    switch (s.getEffectiveLevel()) {
    case Status.INFO:
      buf.append("INFO");
      break;
    case Status.WARN:
      buf.append("<span class=\"orange\">WARN</span>");
      break;
    case Status.ERROR:
      buf.append("<span class=\"red\">ERROR</span>");
      break;
    }
    if (s.getOrigin() != null) {
      buf.append(" in ");
      buf.append(abbreviator.abbreviate(s.getOrigin().getClass().getName()));
      buf.append(" -");
    }

    buf.append(" ");
    buf.append(s.getMessage());

    if (s.getThrowable() != null) {
      buf.append(" ");
      buf.append(s.getThrowable());
    }

    return buf.toString();
  }
  
  public static void print(StringBuffer buf, StatusManager sm) {

    List<Status> statusList = sm.getCopyOfStatusList();
    for (Status s: statusList) {
      print(buf, "", s);
    }
  }
  
  public static void print(StringBuffer buf, String indentation, Status s) {
    String prefix;
    if(s.hasChildren()) {
       prefix = indentation + "+ ";
    } else {
      prefix = indentation + "|-";
    }
    buf.append(prefix);
    buf.append(EnhancedStatusPrinter.print(s));
    buf.append("\r\n");
    
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    if (s.getThrowable() != null) {
      s.getThrowable().printStackTrace(pw);
      buf.append(sw.getBuffer());
    }
    if(s.hasChildren()) {
      Iterator<Status> ite = s.iterator();
      while(ite.hasNext()) {
        Status child = ite.next();
        print(buf, indentation+"  ", child);
      }
    }
  }
  
}
