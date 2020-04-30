package g.perfecto.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Helper {

  public static int logLevel = 2;
  private static Log log = LogFactory.getLog(Helper.class);
  
  public static Boolean executeMethod(RemoteWebDriver driver, String methodName, Map<String, Object> params)
  {
    String res = executeMethodString(driver, methodName, params);
    if (res == null || res.equalsIgnoreCase("false") || res.equalsIgnoreCase("failed"))
      return false;
    return true;
  }

  public static String executeMethodString(RemoteWebDriver driver, String methodName, Map<String, Object> params)
  {
    try {
      log.info("------------------------------------------------");
      log.info("Executing method: " + methodName);

      if (params.size() > 0)
      {
        log.info("Arguments:");
        for (Map.Entry<String, Object> entry : params.entrySet())  
          log.info("  " + entry.getKey() + ": " + entry.getValue());
      }
      
      String res = (String) driver.executeScript(methodName, params);
      log.info("Result: " + res);
      return res;
    }
    catch (Exception ex)
    {
      log.error("Failed to execute method: " + methodName);
      ex.printStackTrace(); 
      return null;
    }
  }

}
