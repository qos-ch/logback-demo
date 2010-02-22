package ch.qos.logback.demo;

import static ch.qos.logback.demo.Constants.CYCLIC_BUFFER_APPENDER_NAME;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.html.UrlCssBuilder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.read.CyclicBufferAppender;

public class ViewLastLog extends HttpServlet {

  private static final long serialVersionUID = -3551928133801157219L;

  Logger logger = LoggerFactory.getLogger(ViewLastLog.class);

  CyclicBufferAppender<ILoggingEvent> cyclicBufferAppender;
  HTMLLayout layout;
  static String PATTERN = "%d%thread%level%logger{25}%mdc{"
      + Constants.USERID_MDC_KEY + "}%msg";

  @Override
  public void init() throws ServletException {
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    initialize(lc);
    super.init();
  }

  void reacquireCBA() {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    cyclicBufferAppender = (CyclicBufferAppender<ILoggingEvent>) context.getLogger(
        Logger.ROOT_LOGGER_NAME).getAppender(CYCLIC_BUFFER_APPENDER_NAME);
  }

  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    reacquireCBA();

    resp.setContentType("text/html");
    PrintWriter output = resp.getWriter();

    output.append(layout.getFileHeader());
    String localRef = req.getContextPath();
    output.append("<h2>Last logging events</h2>");
    output.append("<table class=\"nav\">");
    output.append("<tr><td class=\"sexy\"><a href=\"" + localRef
        + "/index.jsp\" class=\"sexy\">Main Page</a></td></tr>");
    output
        .append("<tr><td class=\"sexy\"><a href=\"#bottom\" class=\"sexy\">Jump to bottom</a></td></tr>");
    output.append("</table>");

    output.append("<div class=\"content_full\">");
    output.append(layout.getPresentationHeader());

    printLogs(output);

    output.append(layout.getPresentationFooter());

    output.append("<a name=\"bottom\" />");

    output.append("</div>");

    output.append(layout.getFileFooter());

    output.flush();
    output.close();
  }

  private void printLogs(PrintWriter output) {
    int count = -1;
    if (cyclicBufferAppender != null) {
      count = cyclicBufferAppender.getLength();
    }

    if (count == -1) {
      output.append("<tr><td>Failed to locate CyclicBuffer</td></tr>\r\n");
    } else if (count == 0) {
      output.append("<tr><td>No logging events to display</td></tr>\r\n");
    } else {
      LoggingEvent le;
      for (int i = 0; i < count; i++) {
        le = (LoggingEvent) cyclicBufferAppender.get(i);
        output.append(layout.doLayout(le) + "\r\n");
      }
    }
  }

  private void initialize(LoggerContext context) {
    logger.debug("Initializing ViewLastLog Servlet");
    cyclicBufferAppender = (CyclicBufferAppender<ILoggingEvent>) context.getLogger(
        Logger.ROOT_LOGGER_NAME).getAppender(CYCLIC_BUFFER_APPENDER_NAME);

    layout = new HTMLLayout();
    layout.setContext(context);
    UrlCssBuilder cssBuilder = new UrlCssBuilder();
    cssBuilder.setUrl("../css/pk.css");
    layout.setCssBuilder(cssBuilder);
    layout.setPattern(PATTERN);
    layout.setTitle("Last Logging Events");
    layout.start();
  }

  public boolean isResetResistant() {
    return false;
  }

  public void onStop(LoggerContext arg0) {
  }
}
