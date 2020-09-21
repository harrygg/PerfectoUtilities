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
  public static final String WINDOWS_7 = "7";
  public static final String WINDOWS_10 = "10";
  
  public String deviceSessionId;
  public String browserName;
  public static final String BROWSER_NAME_SAFARI = "Safari";
  public static final String BROWSER_NAME_MOBILE_SAFARI = "mobileSafari";
  public static final String BROWSER_NAME_CHROME = "Chrome";
  public static final String BROWSER_NAME_MOBILE_CHROME = "mobileChrome";
  public static final String BROWSER_NAME_MOBILE_OS = "mobileOS";
  public static final String BROWSER_NAME_MOBILE_DEFAULT = "mobileDefault";
  public static final String BROWSER_NAME_FIREFOX = "Firefox";
  public static final String BROWSER_NAME_EDGE = "Edge";
  public static final String BROWSER_NAME_INTERNET_EXPLORER = "Internet Explorer";
  
  public String browserVersion;
  public static final String BROWSER_VERSION_LATEST = "latest";
  public static final String BROWSER_VERSION_LATEST_1 = "latest-1";
  public static final String BROWSER_VERSION_LATEST_2 = "latest-2";
  public static final String BROWSER_VERSION_BETA = "beta";

  public String safariInitialUrl;
  public Boolean takesScreenshot;
  public String screenshotFormat;
  public Boolean screenshotOnError;
  
  public static final String AUTOMATION_NAME_PERFECTO = "Perfecto";
  public static final String AUTOMATION_NAME_APPIUM = "Appium";
  public static final String AUTOMATION_NAME_XCUITEST = "XCUITest";
  public String automationName = AUTOMATION_NAME_APPIUM;
  
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
  public Integer openDeviceTimeout;
  
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
  public Boolean useAppiumForHybrid;
  public Boolean iOSResign;
  
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
  public Boolean nativeWebTap;
  public String tunnelId;

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
  public boolean fullContextList;

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
  public Integer reportJobNumber;
  public String reportJobBrunch;
  public String reportTags;
  public String reportCustomFields;
  
  public Boolean outputVideo;
  public String outputVisibility;
  public static final String OUTPUT_VISIBILITY_PUBLIC = "public";
  public static final String OUTPUT_VISIBILITY_PRIVATE = "private";
  public static final String OUTPUT_VISIBILITY_GROUP = "group";
  
  public Boolean perfectoFingerPrintSupport;
  public Boolean perfectoSetLocationSupport;
  public Boolean perfectoMotionInjectionSupport;
  public Boolean perfectoFaceIDSupport;
  public Boolean perfectoImageInjectionSupport;
  public Boolean perfectoVoiceAssistantInjectionSupport;
  public Boolean perfectoForceTouch;
  public Boolean perfectoRebootSupport;
  public String appiumVersion;
  
  private DesiredCapabilities dc = new DesiredCapabilities();
  
  private Log log = LogFactory.getLog(Capabilities.class);


  
  public Capabilities()
  {
  }
  
  public DesiredCapabilities toSeleniumCapabilities()
  {
    Class<? extends Capabilities> cls = this.getClass();
    java.lang.reflect.Field[] fieldlist = cls.getDeclaredFields();
    
    log.debug("Converting Capabilities object to DesiredCapabilities");

    for (int i  = 0; i < fieldlist.length; i++) {
      String name = fieldlist[i].getName();
      // If it's not const and not a DesiredCapbilities object
      if ((fieldlist[i].getModifiers() & Modifier.FINAL) != Modifier.FINAL 
          && !fieldlist[i].getType().equals(DesiredCapabilities.class) 
          && !fieldlist[i].getType().equals(Log.class))
      {
        try {
          Object value = fieldlist[i].get(this);
          if (value != null)
          {
            if (name.startsWith("perfecto"))
              name = "perfecto:" + name.substring(8, 9).toLowerCase() + name.substring(9);
            else if (name.startsWith("report"))
              name = "report." + name.substring(6,7).toLowerCase() + name.substring(7);
            
            dc.setCapability(name, value);
            log.debug(name + ": " + value);     
          }
        } catch (Exception e) {
          e.printStackTrace();
        }      
      }
    }
    return dc;
  }
  
  public void setCapability(String capabilityName, Object capabilityValue)
  {
    dc.setCapability(capabilityName, capabilityValue);
  }
  
  public void setCapability(String capabilityName, boolean capabilityValue)
  {
    dc.setCapability(capabilityName, capabilityValue);
  }
  
  
  public void setCapability(String capabilityName, int capabilityValue)
  {
    dc.setCapability(capabilityName, capabilityValue);
  }
}