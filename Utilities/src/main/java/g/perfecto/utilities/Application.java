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
      Install();
  }

  public Application(RemoteWebDriver driver, String path, String id, Boolean install, Boolean launch) {
    this.driver = driver;
    this.path = path;
    this.id = id;

    if (install)
      Install();

    if (launch)
      Start();
  }

  /**
   * Install application. Its path and id must be set prior to calling this
   * method.
   * 
   * @return true on successful execution of the function
   */
  public Boolean Install() {

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

    return Helper.ExecuteMethod(driver, COMMAND_INSTALL, params);
  }

  /**
   * Uninstalls an application. Its id or name must be set prior to calling this
   * method The function will automatically use the app id or app name (if id is
   * not found)
   * 
   * @return
   */
  public Boolean Uninstall() {

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

    return Helper.ExecuteMethod(driver, COMMAND_UNINSTALL, params);
  }

  /**
   * Open an app. App id or name must be set prior to calling this method. If id
   * is not set, name will be used for starting the app. If none of them are
   * provided, false will be returned
   * 
   * @return true on successful execution of the function
   */
  public Boolean Open() {

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

    return Helper.ExecuteMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Opens app by provided identifier. The identifier could be an the app id
   * (appPackage or bundleId) or its name. The function will try to open app by
   * id first and if it fails, it will try to open the app by name.
   * 
   * @param identifier
   * @return
   */
  public Boolean Open(String identifier) {

    if (!OpenById(identifier))
      return OpenByName(identifier);
    else
      return true;
  }

  /**
   * Open app by id (appPackage or bundleId)
   * 
   * @param id
   * @return
   */
  public Boolean OpenById(String id) {
    Map<String, Object> params = new HashMap<>();
    params.put("identifier", id);
    return Helper.ExecuteMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Open app by name
   * 
   * @param name
   * @return
   */
  public Boolean OpenByName(String name) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    return Helper.ExecuteMethod(driver, COMMAND_OPEN, params);
  }

  /**
   * Start an application. Preferably use Open
   * 
   * @return
   */
  public Boolean Start() {
    return Open();
  }

  /**
   * Start an application by identifier (id or name). Preferably use Open
   * 
   * @return
   */
  public Boolean Start(String identifier) {
    return Open(identifier);
  }

  /**
   * Close a running app. App id or name must be provided prior to calling this
   * method.
   * 
   * @return
   */
  public Boolean Close() {

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

    return Helper.ExecuteMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Close app by provided id or name. First try to close the app by id and then
   * by name.
   * 
   * @param identifier
   * @return
   */
  public Boolean Close(String identifier) {
    if (!CloseById(identifier))
      return CloseByName(identifier);
    return true;
  }

  /**
   * Close app by id
   * 
   * @param id
   *          the bundleID or appPackage
   * @return
   */
  public Boolean CloseById(String id) {
    Map<String, Object> params = new HashMap<>();
    params.put("identifier", id);
    return Helper.ExecuteMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Close app by name
   * 
   * @param name
   * @return
   */
  public Boolean CloseByName(String name) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    return Helper.ExecuteMethod(driver, COMMAND_CLOSE, params);
  }

  /**
   * Stops an app. Use Close instead.
   * 
   * @return
   */
  public Boolean Stop() {
    return Close();
  }

  /**
   * Stops an app by provided identifier (id or name). Use Close instead.
   * 
   * @param identifier
   * @return
   */
  public Boolean Stop(String identifier) {
    return Close(identifier);
  }

  /**
   * Starts activity. App id must be provided prior to calling this method.
   * 
   * @param activityName
   * @return
   */
  public Boolean StartActivity(String activityName) {

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
    return Helper.ExecuteMethod(driver, COMMAND_ACTIVITYOPEN, params);
  }

  /**
   * Gets current app activity.
   * 
   * @return
   */
  public String GetCurrentActivity() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentActivity");
    return Helper.ExecuteMethodString(driver, COMMAND_DEVICEINFO, params);
  }

  /**
   * Get current app package
   * 
   * @return
   */
  public String GetCurrentPackage() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentPackage");
    return Helper.ExecuteMethodString(driver, COMMAND_DEVICEINFO, params);
  }

  /**
   * Verifies the Android package or activity has started on the device.
   * 
   * @return
   */
  public Boolean SyncActivity(String activityName) {
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

    return Helper.ExecuteMethod(driver, COMMAND_ACTIVITYSYNC, params);
  }

  public Boolean SyncActivity(String activityName, int timeout) {
    this.timeout = timeout;
    return SyncActivity(activityName);
  }

  /**
   * Start image injection for the current app. App id or name must be provided
   * prior to calling this function. Uses ID or name.
   * 
   * @param repositoryFile
   *          - path to the image in the repository to be injected
   * @return
   */
  public Boolean StartImageInjection(String repositoryFile) {
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

    return Helper.ExecuteMethod(driver, COMMAND_IMAGEINJECTIONSTART, params);
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
  public Boolean StartImageInjection(String repositoryFile, String id) {
    this.id = id;
    return StartImageInjection(repositoryFile);
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
    return StartImageInjection(repositoryFile, name);
  }

  /**
   * Stops the last started image injection
   * 
   * @return
   */
  public Boolean StopImageInjection() {
    return Helper.ExecuteMethod(driver, COMMAND_IMAGEINJECTIONSTOP, new HashMap<String, Object>());
  }

  /**
   * Switches to different context
   * 
   * @param context
   * @return
   */
  public Boolean SwitchToContext(String context) {
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

  public Boolean SwitchToWebviewContext() {
    return SwitchToContext(CONTEXT_WEB);
  }

  public Boolean SwitchToNativeContext() {
    return SwitchToContext(CONTEXT_NATIVE);
  }

  public Boolean SwitchToVisualContext() {
    return SwitchToContext(CONTEXT_VISUAL);
  }

  public String GetCurrentContextHandle() {
    RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
    String context = (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
    Logger.LogError("Current context: " + context);
    return context;
  }

  public List<String> GetContextHandles() {
    RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
    List<String> contexts = (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
    Logger.LogInfo("List of available contexts: " + String.join(", ", contexts));

    return contexts;
  }

  public Boolean StartObjectOptimization(Integer maxChildNodes) {
    Map<String, Object> params = new HashMap<>();
    params.put("children", maxChildNodes);
    return Helper.ExecuteMethod(driver, "mobile:objects.optimization:start", params);
  }

  public Boolean StopObjectOptimization(Integer maxChildNodes) {
    return Helper.ExecuteMethod(driver, "mobile:objects.optimization:stop", new HashMap<String, Object>());
  }

  public Boolean Authenticate(String authType, Boolean success, String errorType) {
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
    return Helper.ExecuteMethod(driver, method, params);
  }

  public Boolean SensorAuthenticate(Boolean success, String errorType) {
    return Authenticate("sensor", success, errorType);
  }

  public Boolean SensorAuthenticate() {
    return Authenticate("sensor", true, null);
  }

  public Boolean SensorAuthenticateFail(String errorType) {
    return Authenticate("sensor", false, errorType);
  }

  public Boolean FingerAuthenticate(Boolean success, String errorType) {
    return Authenticate("finger", success, errorType);
  }

  public Boolean FingerAuthenticate() {
    return Authenticate("finger", true, null);
  }

  public Boolean FingerAuthenticateFail(String errorType) {
    return Authenticate("finger", false, errorType);
  }
}
