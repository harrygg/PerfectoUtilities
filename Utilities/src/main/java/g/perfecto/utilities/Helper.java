package g.perfecto.utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Helper {

  public static int logLevel = 2;

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
      if (logLevel > 0)
      {
        Logger.LogInfo("------------------------------------------------");
        Logger.LogInfo("Executing method: " + methodName);
      }
      if (logLevel > 1) 
      {
        if (params.size() > 0)
        {
          Logger.LogInfo("Arguments:");
          for (Map.Entry<String, Object> entry : params.entrySet())  
            Logger.LogInfo("  " + entry.getKey() + ": " + entry.getValue());
        }
      }
      String res = (String) driver.executeScript(methodName, params);
      Logger.LogInfo("RESULT: " + res);
      return res;
    }
    catch (Exception ex)
    {
      Logger.LogError("Failed to execute method: " + methodName);
      ex.printStackTrace(); 
      return null;
    }
  }

}
