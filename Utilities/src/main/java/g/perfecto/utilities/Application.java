package g.perfecto.utilities;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.DriverCommand;

public class Application {

  private PerfectoDriver perfectoDriver;
  public String id;
  public String name;
  public String path;
  public String localPath;
  public String app;

  public Boolean instrument = false;
  public Boolean sensorInstrument = false;
  public Boolean fingerInstrument = false;
  public Boolean resign = false;
  public Boolean reset = false;
  public Boolean instrumentResetKeychain = false;
  public String adjustment;
  public String activity;
  public int timeout = 20;
  public String injectedImagePath;
  
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
  private final String COMMAND_ACTIVITY_OPEN = "mobile:activity:open";
  private final String COMMAND_ACTIVITY_SYNC = "mobile:activity:sync";
  private final String COMMAND_DEVICE_INFO = "mobile:device:info";
  private final String COMMAND_IMAGE_INJECTION_START = "mobile:image.injection:start";
  private final String COMMAND_IMAGE_INJECTION_STOP = "mobile:image.injection:stop";
  private final String COMMAND_SENSOR_AUTHENTICATE = "mobile:sensorAuthentication:set";
  private final String COMMAND_FINGERPRINT_AUTHENTICATE = "mobile:fingerprint:set";
  private final String COMMAND_CHECK_ACCESSIBILITY_AUDIT = "mobile:checkAccessibility:audit";
  
  private Log log;
  
  public Application(PerfectoDriver parent)
  {
    this.perfectoDriver = parent;
    if (parent.capabilities.bundleId != null)
      id = parent.capabilities.bundleId;
    else if (parent.capabilities.appPackage != null)
      id = parent.capabilities.appPackage;
    
    log = LogFactory.getLog(this.getClass());
    log.debug("Creating Application object");
  }

  
  public Application(PerfectoDriver parent, String id)
  {
    this(parent);
    this.id = id;
  }
  
  /**
   * Install application. Its path must be set prior to calling this
   * method.
   * 
   * @return true on successful execution of the function
   */
  public Boolean install() 
  {
    return install(null);
  }

  /**
   * Install and start application
   * @param start - start app
   */
  
  public void install(boolean start) 
  {
    install(null);
    start();
  }
  
  /**
   * Install application.
   * method.
   * @param installPath
   * @return
   */
  public Boolean install(String installPath) {

    Map<String, Object> params = new HashMap<>();
    
    if (installPath != null && !installPath.isEmpty())
      path = installPath;
    
    if (path == null || path.isEmpty()) 
    {
      log.error("App path not provided!");
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

    String res = perfectoDriver.executor.executeMethodString(COMMAND_INSTALL, params);
    if (res != null)
    {
      // Fill out the id if it is missing
      if (id == null || id.isEmpty())
        id = res;
      else
        if (id != res)
          log.warn("Returned app identifier is different than the specified one. " + id + " vs " + res);
      return true;
    }
    return false;
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
    if (id == null || id.isEmpty()) {

      if (name != null && !name.isEmpty()) {
        type = "name";
        identifier = name;
      } else {
        log.error("No app id or name provided! App not uninstalled!");
        return false;
      }
    }
    params.put(type, identifier);

    return perfectoDriver.executor.executeMethod(COMMAND_UNINSTALL, params);
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
    if (id == null || id.isEmpty()) {
      if (name != null && !name.isEmpty()) {
        type = "name";
        identifier = name;
      } else {
        log.error("No app id or name provided! App not started!");
        return false;
      }
    }

    params.put(type, identifier);
    log.info("Starting app " + identifier + " identified by its " + type);

//    switchToNativeContext();
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
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
    this.id = id;
    params.put("identifier", id);
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
  }

  /**
   * Open app by name
   * 
   * @param name
   * @return
   */
  public Boolean openByName(String name) {
    Map<String, Object> params = new HashMap<>();
    this.name = name;
    params.put("name", name);
    return perfectoDriver.executor.executeMethod(COMMAND_OPEN, params);
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
        log.error("No app id or name provided! App not closed!");
        return false;
      }
    }

    params.put(type, identifier);
    log.info("Closing app " + identifier + " identified by its " + type);

    return perfectoDriver.executor.tryExecuteMethod(COMMAND_CLOSE, params);
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
    return perfectoDriver.executor.executeMethod( COMMAND_CLOSE, params);
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
    return perfectoDriver.executor.executeMethod(COMMAND_CLOSE, params);
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

    if (id == null || id.isEmpty()) {
      log.error("App id is not provided!");
      return false;
    }

    if (activityName == null || activityName.isEmpty()) {
      log.error("App activityName is not provided!");
      return false;
    }

    Map<String, Object> params = new HashMap<>();
    params.put("package", id);
    params.put("activity", activityName);
    return perfectoDriver.executor.executeMethod(COMMAND_ACTIVITY_OPEN, params);
  }

  /**
   * Gets current app activity.
   * 
   * @return
   */
  public String getCurrentActivity() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentActivity");
    return perfectoDriver.executor.executeMethodString(COMMAND_DEVICE_INFO, params);
  }

  /**
   * Get current app package
   * 
   * @return
   */
  public String getCurrentPackage() {
    Map<String, Object> params = new HashMap<>();
    params.put("property", "currentPackage");
    return perfectoDriver.executor.executeMethodString(COMMAND_DEVICE_INFO, params);
  }

  /**
   * Verifies the Android package or activity has started on the device.
   * 
   * @return
   */
  public Boolean syncActivity(String activityName) {
    Map<String, Object> params = new HashMap<>();

    if (id == null || id.isEmpty()) {
      log.error("App id is not provided!");
      return false;
    }

    if (activityName == null || activityName.isEmpty()) {
      log.error("App activity is not provided!");
      return false;
    }

    params.put("package", id);
    params.put("activity", activityName);

    return perfectoDriver.executor.executeMethod(COMMAND_ACTIVITY_SYNC, params);
  }

  public Boolean syncActivity(String activityName, int timeout) {
    this.timeout = timeout;
    return syncActivity(activityName);
  }


  public Boolean startImageInjection()
  {
    Map<String, Object> params = new HashMap<>();

    if (injectedImagePath == null || injectedImagePath.isEmpty()) {
      log.error("No repositoryFile specified!");
      return false;
    }

    params.put("repositoryFile", injectedImagePath);

    if (adjustment != null)
      params.put("adjustment", adjustment);

    if (id == null || id.isEmpty()) {
      if (name == null || name.isEmpty()) {
        log.error("No app id or name specified!");
      } else {
        params.put("name", name);
      }
    } else {
      params.put("identifier", id);
    }

    return perfectoDriver.executor.executeMethod(COMMAND_IMAGE_INJECTION_START, params);
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
    injectedImagePath = repositoryFile;
    return startImageInjection();
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
    injectedImagePath = repositoryFile;
    return startImageInjection();
  }


  
  /**
   * Inject image to an app identified by its name.
   * 
   * @param repositoryFile
   * @param name
   * @return
   */
  public Boolean startImageInjectionByName(String repositoryFile, String name) {
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
    return perfectoDriver.executor.executeMethod(COMMAND_IMAGE_INJECTION_STOP, new HashMap<String, Object>());
  }
  
  /**
   * Switches to different context
   * 
   * @param context
   * @return
   */
  public Boolean switchToContext(String context) 
  {
    Map<String, String> params = new HashMap<String, String>();
    params.put("name", context);

    try {
      log.info("Switching to '" + context + "' context.");
      perfectoDriver.executor.remoteExecute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }
    catch (Exception e) {
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

  public String getCurrentContextHandle() 
  {
    String context = (String) perfectoDriver.driver.getContext();//.executor.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE);
    log.info("Current context: " + context);
    return context;
  }

  public List<String> getContextHandles() 
  {
    @SuppressWarnings("unchecked")
    List<String> contexts = (List<String>) perfectoDriver.executor.remoteExecute(DriverCommand.GET_CONTEXT_HANDLES, null);
    log.info("List of available contexts: " + String.join(", ", contexts));

    return contexts;
  }

  public Boolean startObjectOptimization(Integer maxChildNodes) 
  {
    Map<String, Object> params = new HashMap<>();
    params.put("children", maxChildNodes);
    return perfectoDriver.executor.executeMethod("mobile:objects.optimization:start", params);
  }

  public Boolean stopObjectOptimization(Integer maxChildNodes) 
  {
    return perfectoDriver.executor.executeMethod("mobile:objects.optimization:stop", new HashMap<String, Object>());
  }

  private Boolean authenticate(String authType, Boolean success, String errorType) 
  {
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
        log.error("No app id or name provided! SetAuthentication canceled!");
        return false;
      }
    }

    log.info("SetAuthentication on app '" + identifier + "' identified by its " + type);
    params.put(type, identifier);
    params.put("resultAuth", success ? "success" : "fail");
    if (errorType != null && !errorType.isEmpty())
      params.put("errorType", errorType);

    String method = authType == "finger" ? COMMAND_FINGERPRINT_AUTHENTICATE : COMMAND_SENSOR_AUTHENTICATE;
    return perfectoDriver.executor.executeMethod(method, params);
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

  /**
   * Uploads a local APK/IPA file to Perfecto repository.
   * If repository path is not specified, uploads to PRIVATE folder
   * @return
   */
  public Boolean upload()
  {
    if (localPath == null || localPath.isEmpty())
    {
      log.error("Local app path must be specified to upload a media");
      return false;
    }
    
    if (path == null || path.isEmpty())
    {
      Path filePath = Paths.get(localPath);
      path = "PRIVATE:" + filePath.getFileName().toString();
      log.error("Path argument was not set. Using: " + path);
    }
    return perfectoDriver.uploadMedia(localPath, path);
  }
  
  /**
   * Uploads a local APK/IPA file to Perfecto repository.
   * @param localPath
   * @return
   */
  public Boolean upload(String localPath)
  {
    this.localPath = localPath;
    return upload();
  }
  
  public Boolean upload(URL mediaURL) 
  {
    if (path == null || path.isEmpty())
    {
      Path filePath = Paths.get(mediaURL.toString());
      path = "PRIVATE:" + filePath.getFileName().toString();
      log.error("Path argument was not set. Using: " + path);
    }
    
    return perfectoDriver.uploadMedia(mediaURL, path);
  }
  
  public Boolean upload(String localPath, String repositoryKey)
  {
    return perfectoDriver.uploadMedia(localPath, repositoryKey);
  }
  
  public Boolean upload(URL mediaURL, String repositoryKey) 
  {
    return perfectoDriver.uploadMedia(mediaURL, repositoryKey);
  }
  
  /**
   * 
   * @param tag The tag that is appended to the name of the audit report to help match it to the application screen.
   * @return
   */
  public void checkAccessibilityAudit(String tag)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("tag", tag);
    perfectoDriver.executor.executeMethod(COMMAND_CHECK_ACCESSIBILITY_AUDIT, params);
  }
  
}