package g.perfecto.utilities;

import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities
{
  public String deviceName;
  public String securityToken;

  public String platformName;
  public String platformVersion;
  public String deviceSessionId;
  public String browserName;
  public static final String BROWSER_NAME_SAFARI = "Safari";
  public static final String BROWSER_NAME_CHROME = "Chrome";
  public static final String BROWSER_NAME_MOBILEOS = "MobileOS";
  public static final String BROWSER_NAME_MOBILEDEFAULT = "MobileDefault";
  public static final String BROWSER_NAME_PERFECTOMOBILE = "PerfectoMobile";
  public static final String BROWSER_NAME_FIREFOX = "Firefox";
  public static final String BROWSER_NAME_EDGE = "Edge";
  public static final String BROWSER_NAME_INTERNET_EXPLORER = "Internet Explorer";
  
  public String browserVersion;
  public static final String BROWSER_VERSION_LATEST = "latest";
  public static final String BROWSER_VERSION_LATEST_1 = "latest-1";
  public static final String BROWSER_VERSION_LATEST_2 = "latest-2";
  public static final String BROWSER_VERSION_BETA = "beta";

  public Boolean takesScreenshot;
  public String screenshotFormat;
  public Boolean screenshotOnError;
  
  public String automationName;
  public static final String AUTOMATION_NAME_PERFECTO = "Perfecto";
  public static final String AUTOMATION_NAME_APPIUM = "Appium";
  public static final String AUTOMATION_NAME_XCUITEST = "XCUITest";
  
  public String scriptName;
  
  public Boolean useVirtualDevice;  
  public String deviceType;
  public String description;
  
  public String location;
  public static final String LOCATION_US_EAST = "US East";
  public static final String LOCATION_EU_FRANKFURT = "EU Frankfurt";
  public static final String LOCATION_AP_SYDNEY = "AP Sydney";
  public static final String LOCATION_NA_CA_YYZ = "NA-CA-YYZ";
  public static final String LOCATION_NA_US_BOS = "NA-US-BOS";
  public static final String LOCATION_NA_US_PHX = "NA-US-PHX";
  public static final String LOCATION_EU_DE_FRA = "EU-DE-FRA";
  public static final String LOCATION_EU_UK_LON = "EU-UK-LON";
  public static final String LOCATION_APAC_AUS_SYD = "APAC-AUS-SYD";
  
  public String manifacturer;
  public String model;
  public String network;
  public String openDeviceTimeout;
  
  public String resolution;
  public static final String RESOLUTION_800x600 = "800x600";
  public static final String RESOLUTION_1024x768 = "1024x768";
  public static final String RESOLUTION_1280x1024 = "1280x1024";
  public static final String RESOLUTION_1366x768 = "1366x768";
  public static final String RESOLUTION_1440x900 = "1440x900";
  public static final String RESOLUTION_1600x1200 = "1600x1200";
  public static final String RESOLUTION_1680x1050 = "1680x1050";
  public static final String RESOLUTION_1920x1080 = "1920x1080";
  public static final String RESOLUTION_2560x1440 = "2560x1440";
  public static final String RESOLUTION_3840x2160 = "3840x2160";
  
  public Boolean baseAppiumBehavior;
  public Boolean enableAppiumBehavior;
  public Boolean useAppiumForWeb;
  
  public String automationInfrastructure;
  public Boolean audioPlayback;

  public String app;
  public String bundleId;
  public String udid;
  public String appPackage;
  public String appActivity;
  public String appWaitActivity;
  public String appWaitPackage;
  public String appWaitDuration;
  public String intentAction;
  public String intentCategory;
  public String optionalIntentArguments;
  public Boolean autoLaunch;
  public String orientation;
  public static final String ORIENTATION_LANDSCAPE = "Landscape";
  public static final String ORIENTATION_PORTRAIT = "Portrait";
  public String processArguments;
  public Boolean noReset;
  public Boolean fullReset;
  public Boolean dataReset;
  public Boolean autoInstrument;
  public Boolean sensorInstrument;
  public Boolean waitForPageLoad;
  public String language;
  public String locale;

  public Integer newCommandTimeout;
  public Boolean enablePerformanceLogging;
  public Boolean printPageSourceOnFindFailure;

  public String chromedriverArgs;

  public String chromedriverVersion;
  public String chromedriverExecutableDir;
  public String chromedriverChromeMappingFile;
  public String chromedriverUseSystemExecutable;
  public Integer chromedriverPort;
  public String chromeOptions;

  public Boolean autoWebview;
  public Integer autoWebviewTimeout;
  public Boolean ensureWebviewsHavePages;

  public Boolean nativeWebScreenshot;
  public Boolean autoGrantPermissions;

  public Boolean gpsEnabled;
  public Integer adbExecTimeout;

  public String unlockType;
  public String unlockKey;
  public String uninstallOtherPackages;

  public Boolean appWaitForLaunch;
  public Boolean skipServerInstallation;

  public String addHostsRecord;
  public String seleniumVersion;
  public String geckodriverVersion;
  public String iedriverVersion;

  public String reportProjectName;
  public String reportProjectVersion;
  public String reportJobName;
  public String reportJobNumber;
  public String reportJobBrunch;
  public String reportTags;
  public String reportCustomFields;
  
  public Boolean outputVideo;
  public String outputVisibility;
  public static final String OUTPUT_VISIBILITY_PUBLIC = "public";
  public static final String OUTPUT_VISIBILITY_PRIVATE = "private";
  public static final String OUTPUT_VISIBILITY_GROUP = "group";

  public DesiredCapabilities dc = new DesiredCapabilities();
  
  private Log log = LogFactory.getLog(Capabilities.class);
  
  public Capabilities()
  {
  }
  
  public DesiredCapabilities toSeleniumCapabilities() throws IllegalArgumentException, IllegalAccessException
  {
    Class cls = this.getClass();
    java.lang.reflect.Field[] fieldlist = cls.getDeclaredFields();
    
    log.debug("Converting Capabilities object to DesiredCapabilities");

    for (int i  = 0; i < fieldlist.length; i++) {
      String name = fieldlist[i].getName();
      // If it's not a constant and not a DesiredCapbilities object
      if ((fieldlist[i].getModifiers() & Modifier.FINAL) != Modifier.FINAL 
          && !fieldlist[i].getType().equals(DesiredCapabilities.class) 
          && !fieldlist[i].getType().equals(Log.class))
      {
        String value = String.valueOf(fieldlist[i].get(this)); 
        if (value != "null")
        {
          log.debug(name + ": " + value);
          dc.setCapability(name, value);
        }        
      }
    }

    return dc;
  }
}