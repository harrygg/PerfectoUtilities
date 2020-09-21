package g.perfecto.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.Response;

import io.appium.java_client.AppiumDriver;

public class Executor
{
  private Log log = LogFactory.getLog(Executor.class);
  private AppiumDriver<WebElement> driver;
  public JavascriptExecutor js;
  
  public Executor(AppiumDriver<WebElement> driver)
  {
    this.driver = driver;
    js = (JavascriptExecutor) driver;
  }
  
  public Boolean executeMethod(String methodName, Map<String, Object> params)
  {
    if (params == null)
      params = new HashMap<String, Object>();
    
    String res = executeMethodString(methodName, params);
    if (res != null && (res.equalsIgnoreCase("false") || res.equalsIgnoreCase("failed")))
      return false;
    return true;
  }
  
  public Boolean executeMethod(String methodName) {
    return executeMethod(methodName, null);
  }
  
  public Boolean tryExecuteMethod(String methodName) {
    return tryExecuteMethod(methodName, null);
  }
  
  public Boolean tryExecuteMethod(String methodName, Map<String, Object> params)
  {
    if (params == null)
      params = new HashMap<String, Object>();
    
    String res = tryExecuteMethodString(methodName, params);
    if (res != null && (res.equalsIgnoreCase("false") || res.equalsIgnoreCase("failed")))
      return false;
    return true;
  }
  
  public String tryExecuteMethodString(String methodName, Map<String, Object> params)
  {
    try 
    {
      return executeMethodString(methodName, params);
    }
    catch (Exception ex)
    {
      log.error("Failed to execute method: " + methodName);
      ex.printStackTrace(); 
      return "false";
    }
  }
  
  public String executeMethodString(String methodName, Map<String, Object> params)
  {
    log.info("------------------------------------------------");
    log.info("Executing method: " + methodName);

    if (params == null)
      params = new HashMap<String, Object>();
    
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
  
  
  public String executeMethodString(String methodName)
  {
    return executeMethodString(methodName, null);
  }
  
  public String executeScript(String script, Object args)
  {
    log.info("------------------------------------------------");
    log.info("Executing script: " + script + " on " + args);
    String res = (String) js.executeScript(script, args);
    log.info("Result: " + res);
    return res;
  }
  
  public String executeScript(String script)
  {
    return executeScript(script, null);
  }
  
  public String executeAsyncScript(String script, Object args)
  {
    log.info("------------------------------------------------");
    log.info("Executing script: " + script + " on " + args);
    String res = (String) js.executeAsyncScript(script, args);
    log.info("Result: " + res);
    return res;
  }

  public String executeAsyncScript(String script)
  {
    return executeAsyncScript(script, null);
  }
  
  public Object remoteExecute(String methodName, Map<String, String> parameters)
  { 
    log.info("------------------------------------------------");
    log.info("Executing remote method: " + methodName);
    
    if (parameters != null && parameters.size() > 0)
    {
      log.info("Arguments:");
      for (Entry<String, String> entry : parameters.entrySet())  
        log.info("  " + entry.getKey() + ": " + entry.getValue());
    }
    RemoteExecuteMethod remoteExecuteMethod = new RemoteExecuteMethod(driver);
    Object res = remoteExecuteMethod.execute(methodName, parameters);
    log.info("Result: " + res);
    return res;
  }
  
  public Object execute(String methodName)
  {
    return driver.execute(methodName);
  }
}
