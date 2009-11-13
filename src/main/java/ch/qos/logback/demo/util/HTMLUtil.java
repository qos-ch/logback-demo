package ch.qos.logback.demo.util;

import java.io.PrintWriter;

public class HTMLUtil {

  public static void printMenu(String localRef, PrintWriter output) {
    output.append("<table class=\"nav\">");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/index.jsp\" class=\"sexy\">Home</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/login.jsp\" class=\"sexy\">Login</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/statii/\" class=\"sexy\">View Statii</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/lastLog/\" class=\"sexy\">View logs</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/prime.html\" class=\"sexy\">Prime number</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/lotto.html\" class=\"sexy\">Play the loto</a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/reloadConfig/\" class=\"sexy\"><b>Reload config</b></a></td></tr>");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/logout/\" class=\"sexy\">logout</a></td></tr>");
    output.append("</table>");
  }

  public static void printHome(String localRef, PrintWriter output) {
    output.append("<table class=\"nav\"><tr><td class=\"sexy\"><a href=\""
        + localRef
        + "/index.jsp\" class=\"sexy\">Main page</a></td></tr></table>\r\n");
  }

  public static void printContentStart(PrintWriter output) {
    output.append("<div class=\"content\">");
  }

  public static void printContentEnd(PrintWriter output) {
    output.append("</div>");
  }

  public static void printCSSLink(String localRef, PrintWriter output) {
    output.append("<LINK REL=\"StyleSheet\" HREF=\"../css/pk.css\" />");
  }
}
