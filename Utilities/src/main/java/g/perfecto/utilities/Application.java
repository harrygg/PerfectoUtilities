package g.perfecto.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Application {

  private RemoteWebDriver driver = null;
  public String id = null;
  public String name = null;
  public String path = null;
  public String app = null;

  public Boolean instrument = false;
  public Boolean sensorInstrument = false;
  public Boolean fingerInstrument = false;
  public Boolean resign = false;
  public Boolean reset = false;
  public Boolean instrumentResetKeychain = false;
  public String adjustment = null;
  public String activity = null;
  public int timeout = 20;

  public static final String CONTEXT_NATIVE = "NATIVE_APP";
  public static final String CONTEXT_WEB = "WEBVIEW";
  public static final String CONTEXT_VISUAL = "VISUAL";

  public static final String AUTHENTICATION_TYPE_FAILED = "authFailed";
  public static final String AUTHENTICATION_TYPE_USER_CANCELED = "userCancel";
  public static final String AUTHENTICATION_TYPE_USER_FALLBACK = "userFallback";
  public static final String AUTHENTICATION_TYPE_SYSTEM_CANCEL = "systemCancel";
  public static final String AUTHENTICATION_TYPE_LOCKOUT = "lockOut";

  private final String COMMAND_INSTALL = "mobile:application:install";
  private final String COMMAND_UNINSTALL = "mobile:application:uninstall";
  private final String COMMAND_OPEN = "mobile:application:open";
  private final String COMMAND_CLOSE = "mobile:application:close";
  private final String COMMAND_ACTIVITYOPEN = "mobile:activity:open";
  private final String COMMAND_ACTIVITYSYNC = "mobile:activity:sync";
  private final String COMMAND_DEVICEINFO = "mobile:device:info";
  private final String COMMAND_IMAGEINJECTIONSTART = "mobile:image.injection:start";
  private final String COMMAND_IMAGEINJECTIONSTOP = "mobile:image.injection:stop";
  private final String COMMAND_SENSOR_AUTHENTICATE = "mobile:sensorAuthentication:set";
  private final String COMMAND_FINGERPRINT_AUTHENTICATE = "mobile:fingerprint:set";

  public Application(RemoteWebDriver driver) {
    Logger.LogDebug("Creating Application object");
    this.driver = driver;
  }

  public Application(RemoteWebDriver driver, String path) {
    this.driver = driver;
    this.path = path;
  }

  public Application(RemoteWebDriver driver, String path, String id) {
    this.driver = driver;
    this.path = path;
    this.id = id;
  }

  public Application(RemoteWebDriver driver, String path, String id, Boolean install) {
    this.driver = driver;
    this.path = path;
    this.id = id;

    if (install)
      install();
  }

  public Application(RemoteWebDriver driver, String path, String id, Boolean install, Boolean launch) {
    this.driver = driver;
    this.path = path;
    this.id = id;

    if (install)
      install();

    if (launch)
      start();
  }

  /**
   * Install application. Its path and id must be set prior to calling this
   * method.
   * 
   * @return true on successful execution of the function
   */
  public Boolean install() {

    Map<String, Object> params = new HashMap<>();

    if (path == null || path.trim().length() == 0) {
      Logger.LogError("App path not provided!");
      return false;
    }

    params.put("file", path);

    if (instrument)
      params.put("instrument", "instrument");
    if (fingerInstrument)
      params.put("fingerprintInstrument", "fingerprint");
    if (sensorInstrument)
      params.put("sensorInstrument", "sensor");
    if (resign)
      params.put("resign", "true");
    if (reset)
      params.put("dataReset", "reset");
    if (instrumentResetKeychain)
      params.put("instrumentResetKeychain", "true");

    return Helper.executeMethod(driver, COMMAND_INSTALL, params);
  }

  /**
   * Uninstalls an application. Its id or name must be set prior to calling this
   * method The function will automatically use the app id or app name (if id is
   * not found)
   * 
   * @return
   */
  public Boolean uninstall() {

    Map<String, Object> params = new HashMap<>();

    String identifier = id;
    String type = "identifier";

    // If we don't have app id, use name
    if (id == null || id.trim().length() == 0) {

      if (name != null && name.trim().length() > 0) {
        type = "name";
        identifier = name;
      } else {
        Logger.LogError("No app id or name provided! App not uninstalled!");
        return false;
      }
    }
    params.put(type, identifier);

    return Helper.executeMethod(driver, COMMAND_UNINSTALL, params);
  }

  /**
   * Open an app. App id or name must be set prior to calling this method. If id
   * is not set, name will be used for starting the app. If none of them are
   * provided, false will be returned
   * 
   * @return true on successful execution of the function
   */
  public Boolean open() {

    Map<String, Object> params = new HashMap<>();
    String identifier = id;
    String type = "identifier";

    // If we don't have app id, use name
    if (id == null || id.trim().length() == 0) {
      if (name != null && name.trim().length() > 0) {
        type = "name";
        identifier = name;
      } else {
        Logger.LogError("No app id or name provided! App not started!");
        return false;
      }
    }

    params.put(type, identifier);
    Logger.LogInfo("Starting app " + identifier + " identified by its " + type);

    switchToNativeContext();
    return Helper.executeMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Opens app by provided identifier. The identifier could be an the app id
   * (appPackage or bundleId) or its name. The function will try to open app by
   * id first and if it fails, it will try to open the app by name.
   * 
   * @param identifier
   * @return
   */
  public Boolean open(String identifier) {

    if (!openById(identifier))
      return openByName(identifier);
    else
      return true;
  }

  /**
   * Open app by id (appPackage or bundleId)
   * 
   * @param id
   * @return
   */
  public Boolean openById(String id) {
    Map<String, Object> params = new HashMap<>();
    params.put("identifier", id);
    return Helper.executeMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Open app by name
   * 
   * @param name
   * @return
   */
  public Boolean openByName(String name) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    return Helper.executeMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Start an application. Preferably use Open
   * 
   * @return
   */
  public Boolean start() {
    return open();
  }

  /**
   * Start an application by identifier (id or name). Preferably use Open
   * 
   * @return
   */
  public Boolean start(String identifier) {
    return open(identifier);
  }

  /**
   * Close a running app. App id or name must be provided prior to calling this
   * method.
   * 
   * @return
   */
  public Boolean close() {

    Map<String, Object> params = new HashMap<>();
    String identifier = id;
    String type = "identifier";

    // If we don't have app id, use name
    if (id == null || id.isEmpty()) {
      if (name != null && !name.isEmpty()) {
        type = "name";
        identifier = name;
      } else {
        Logger.LogError("No app id or name provided! App not closed!");
        return false;
      }
    }

    params.put(type, identifier);
    Logger.LogInfo("Closing app " + identifier + " identified by its " + type);

    return Helper.executeMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Close app by provided id or name. First try to close the app by id and then
   * by name.
   * 
   * @param identifier
   * @return
   */
  public Boolean close(String identifier) {
    if (!closeById(identifier))
      return closeByName(identifier);
    return true;
  }

  /**
   * Close app by id
   * 
   * @param id
   *          the bundleID or appPackage
   * @return
   */
  public Boolean closeById(String id) {
    Map<String, Object> params = new HashMap<>();
    params.put("identifier", id);
    return Helper.executeMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Close app by name
   * 
   * @param name
   * @return
   */
  public Boolean closeByName(String name) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    return Helper.executeMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Stops an app. Use Close instead.
   * 
   * @return
   */
  public Boolean stop() {
    return close();
  }

  /**
   * Stops an app by provided identifier (id or name). Use Close instead.
   * 
   * @param identifier
   * @return
   */
  public Boolean stop(String identifier) {
    return close(identifier);
  }

  /**
   * Closes and reopens an application
   * @return
   */
  public Boolean restart()
  {
    close();
    return open();
  }
  
  /**
   * Closes and reopens app by provided ID
   * @param id
   * @return
   */
  public Boolean restart(String id)
  {
    this.id = id;
    close();
    return open();
  }
  
  /**
   * Starts activity. App id must be provided prior to calling this method.
   * 
   * @param activityName
   * @return
   */
  public Boolean startActivity(String activityName) {

    if (id == null || id.trim().length() == 0) {
      Logger.LogError("App id is not provided!");
      return false;
    }

    if (activityName == null || activityName.trim().length() == 0) {
      Logger.LogError("App activityName is not provided!");
      return false;
    }

    Map<String, Object> params = new HashMap<>();
    params.put("package", id);
    params.put("activity", activityName);
    return Helper.executeMethod(driver, COMMAND_ACTIVITYOPEN, params);
  }

  /**
   * Gets current app activity.
   * 
   * @return
   */
  public String getCurrentActivity() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentActivity");
    return Helper.executeMethodString(driver, COMMAND_DEVICEINFO, params);
  }

  /**
   * Get current app package
   * 
   * @return
   */
  public String getCurrentPackage() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentPackage");
    return Helper.executeMethodString(driver, COMMAND_DEVICEINFO, params);
  }

  /**
   * Verifies the Android package or activity has started on the device.
   * 
   * @return
   */
  public Boolean syncActivity(String activityName) {
    Map<String, Object> params = new HashMap<>();

    if (id == null || id.trim().length() == 0) {
      Logger.LogError("App id is not provided!");
      return false;
    }

    if (activityName == null || activityName.trim().length() == 0) {
      Logger.LogError("App activity is not provided!");
      return false;
    }

    params.put("package", id);
    params.put("activity", activityName);

    return Helper.executeMethod(driver, COMMAND_ACTIVITYSYNC, params);
  }

  public Boolean syncActivity(String activityName, int timeout) {
    this.timeout = timeout;
    return syncActivity(activityName);
  }

  /**
   * Start image injection for the current app. App id or name must be provided
   * prior to calling this function. Uses ID or name.
   * 
   * @param repositoryFile
   *          - path to the image in the repository to be injected
   * @return
   */
  public Boolean startImageInjection(String repositoryFile) {
    Map<String, Object> params = new HashMap<>();

    if (repositoryFile.trim().length() == 0) {
      Logger.LogError("No repositoryFile specified!");
      return false;
    }

    params.put("repositoryFile", repositoryFile);

    if (adjustment != null)
      params.put("adjustment", adjustment);

    if (id == null || id.trim().length() == 0) {
      if (name == null || name.trim().length() == 0) {
        Logger.LogError("No app id or name specified!");
      } else {
        params.put("name", name);
      }
    } else {
      params.put("identifier", id);
    }

    return Helper.executeMethod(driver, COMMAND_IMAGEINJECTIONSTART, params);
  }

  /**
   * Start image injection to app identified by its ID
   * 
   * @param repositoryFile
   *          - path to the image in the repository to be injected
   * @param id
   *          - id of the application
   * @return
   */
  public Boolean startImageInjection(String repositoryFile, String id) {
    this.id = id;
    return startImageInjection(repositoryFile);
  }

  /**
   * Inject image to an app identified by its name.
   * 
   * @param repositoryFile
   * @param name
   * @return
   */
  public Boolean StartImageInjectionByName(String repositoryFile, String name) {
    this.id = null;
    this.name = name;
    return startImageInjection(repositoryFile, name);
  }

  /**
   * Stops the last started image injection
   * 
   * @return
   */
  public Boolean stopImageInjection() {
    return Helper.executeMethod(driver, COMMAND_IMAGEINJECTIONSTOP, new HashMap<String, Object>());
  }

  /**
   * Switches to different context
   * 
   * @param context
   * @return
   */
  public Boolean switchToContext(String context) {
    if (!context.equalsIgnoreCase(CONTEXT_WEB) && !context.equalsIgnoreCase(CONTEXT_NATIVE)
        && !context.equalsIgnoreCase(CONTEXT_VISUAL)) {
      Logger.LogError("Unsupported context value: " + context);
      return false;
    }

    RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
    Map<String, String> params = new HashMap<String, String>();
    params.put("name", context);

    try {
      Logger.LogInfo("Switching to '" + context + "' context.");
      executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public Boolean switchToWebviewContext() {
    return switchToContext(CONTEXT_WEB);
  }

  public Boolean switchToNativeContext() {
    return switchToContext(CONTEXT_NATIVE);
  }

  public Boolean switchToVisualContext() {
    return switchToContext(CONTEXT_VISUAL);
  }

  public String getCurrentContextHandle() {
    RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
    String context = (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
    Logger.LogInfo("Current context: " + context);
    return context;
  }

  public List<String> getContextHandles() {
    RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
    List<String> contexts = (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
    Logger.LogInfo("List of available contexts: " + String.join(", ", contexts));

    return contexts;
  }

  public Boolean startObjectOptimization(Integer maxChildNodes) {
    Map<String, Object> params = new HashMap<>();
    params.put("children", maxChildNodes);
    return Helper.executeMethod(driver, "mobile:objects.optimization:start", params);
  }

  public Boolean stopObjectOptimization(Integer maxChildNodes) {
    return Helper.executeMethod(driver, "mobile:objects.optimization:stop", new HashMap<String, Object>());
  }

  private Boolean authenticate(String authType, Boolean success, String errorType) {
    Map<String, Object> params = new HashMap<>();

    // params = setAppIdOrName(params);
    String identifier = id;
    String type = "identifier";

    // If we don't have app id, use name
    if (id == null || id.isEmpty()) {
      if (name != null && !name.isEmpty()) {
        type = "name";
        identifier = name;
      } else {
        Logger.LogError("No app id or name provided! SetAuthentication canceled!");
        return false;
      }
    }

    Logger.LogInfo("SetAuthentication on app '" + identifier + "' identified by its " + type);
    params.put(type, identifier);
    params.put("resultAuth", success ? "success" : "fail");
    if (errorType != null && !errorType.isEmpty())
      params.put("errorType", errorType);

    String method = authType == "finger" ? COMMAND_FINGERPRINT_AUTHENTICATE : COMMAND_SENSOR_AUTHENTICATE;
    return Helper.executeMethod(driver, method, params);
  }

  public Boolean sensorAuthenticate(Boolean success, String errorType) {
    return authenticate("sensor", success, errorType);
  }

  public Boolean sensorAuthenticate() {
    return authenticate("sensor", true, null);
  }

  public Boolean sensorAuthenticateFail(String errorType) {
    return authenticate("sensor", false, errorType);
  }

  public Boolean fingerAuthenticate(Boolean success, String errorType) {
    return authenticate("finger", success, errorType);
  }

  public Boolean fingerAuthenticate() {
    return authenticate("finger", true, null);
  }

  public Boolean fingerAuthenticateFail(String errorType) {
    return authenticate("finger", false, errorType);
  }
}
