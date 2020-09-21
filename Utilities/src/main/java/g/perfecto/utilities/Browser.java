package g.perfecto.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Browser
{
  public final String COMMAND_EXECUTE = "mobile:browser:execute";
  public final String COMMAND_NAVIGATE = "mobile:browser:navigate";
  public final String COMMAND_CLEAN = "mobile:browser:clean";
  public final String COMMAND_OPEN = "mobile:browser:open";
  public final String COMMAND_SYNC = "mobile:browser:sync";
  
  PerfectoDriver perfectoDriver;
  private Log log;
  
  public Browser(PerfectoDriver driver)
  {
    
    log = LogFactory.getLog(this.getClass());
    log.debug("Creating " + this.getClass() + " object");
    perfectoDriver = driver;
  }
  
  /**
   * Supported from Android 4.1. Cleans all data and opens a Chrome Terms and Services page that needs a manual click to accept. 
   * @return
   */
  public Boolean clean()
  {
    return perfectoDriver.executor.executeMethod(COMMAND_CLEAN);
  }

  /**
   * Opens the browser set as default on the device
   * @return
   */
  public Boolean openDefault()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("automation", "simulated");
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
  }

  /**
   * Opens the DOM object supported browser Safari for iOS, Chrome for Android. 
   * This is done with a direct native command to the device OS, and not with navigation. Supported for - Chrome, Safari, Instrumented hybrid apps.
   * @return
   */
  public Boolean open()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("automation", "os");
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
  }
  
  /**
   * Opens the Chrome browser. This is done with a direct native command to the device OS, and not with navigation.
   * @return
   */
  public Boolean openChrome()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("automation", "chrome");
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
  }
  
  /**
   * Opens the Safari browser. This is done with a direct native command to the device OS, and not with navigation.
   * @return
   */
  public Boolean openSafari()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("automation", "safari");
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
  }
  
  /**
   * Directs the browser page back. Can also be used to refresh the current webpage.
   * @return
   */
  public Boolean navigateBack()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("target", "back");
    return perfectoDriver.executor.executeMethod(COMMAND_NAVIGATE, params);
  }
  
  /**
   * Directs the browser page forward. Can also be used to refresh the current webpage.
   * @return
   */
  public Boolean navigateForward()
  {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("target", "forward");
    return perfectoDriver.executor.executeMethod(COMMAND_NAVIGATE, params);
  }
  

  /**
   * Verifies the browser application is running and page is loaded.
   * @param timeout The time, in seconds, to wait for the page to be loaded. (Default: 60 seconds)
   * @return
   */
  public Boolean sync(Integer timeout)
  {
    Map<String, Object> params = new HashMap<String, Object>();
    if (timeout != null && timeout > 0)
      params.put("timeout", timeout);
    return perfectoDriver.executor.executeMethod(COMMAND_SYNC, params);
  }
  
  /**
   * Verifies the browser application is running and page is loaded.
   * @return
   */
  public Boolean sync()
  {
    return sync(null);
  }

  /**
   * Executes a JavaScript script on the device browser application. Supported for - Chrome, Safari, Instrumented hybrid apps.
   * @param script The JavaScript code, encoded as a String, to execute or the full repository path
   * @param timeout The time, in seconds, to wait for the page to be loaded in order to execute the JavaScript code.
   * @param webView For hybrid applications - The index number of the web view element.
   * @return
   */
  public Boolean execute(String script, Integer timeout, Integer webView)
  {
    Map<String, Object> params = new HashMap<String, Object>();
    if (script.startsWith("PUBLIC") || script.startsWith("PRIVATE") || script.startsWith("GROUP"))
      params.put("repositoryFile", script);
    else
      params.put("script", script);
    
    if (timeout != null && timeout > 0)
      params.put("timeout", timeout);
    
    if (webView != null)
      params.put("webview", webView);
    
    return perfectoDriver.executor.executeMethod(COMMAND_EXECUTE, params);
  }
  
  public Boolean execute(String script, Integer timeout)
  {
    return execute(script, timeout, null);
  }
  

  public Boolean execute(String script)
  {
    return execute(script, null, null);
  }
}