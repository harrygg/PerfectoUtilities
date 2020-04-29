package g.perfecto.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class Logger
{

  int level = 4;

  private final static List VALID_LEVELS = Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR");

  public Logger(String logLevel)
  {
//    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//    ch.qos.logback.classic.Logger logger = loggerContext.getLogger("g.perfecto.utilities");
//    System.out.println("g.perfecto.utilities" + " current logger level: " + logger.getLevel());

//    logger.setLevel(Level.toLevel(logLevel));
  }
  
  public static void Log(Integer level, String data)
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");  
    LocalDateTime now = LocalDateTime.now();

    String type;
    switch(level) {
      case 1: type = "INFO"; break;
      case 2: type = "WARN"; break;
      case 3: type = "ERROR"; break;
      default: type = "DEBUG";
    }
    System.out.println(String.format("%s | %s | %s ", dtf.format(now), type, data));
  }

  public static void LogInfo(String data)
  {
    Log(1, data);
  }

  public static void LogWarning(String data)
  {
    Log(2, data);
  }

  public static void LogError(String data)
  {
    Log(3, data);
  }

  public static void LogDebug(String data)
  {
    Log(4, data);
  }
}

