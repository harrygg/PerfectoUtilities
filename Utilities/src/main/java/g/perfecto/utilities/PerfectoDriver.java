package g.perfecto.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

import g.perfecto.utilities.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class PerfectoDriver
{
  public AppiumDriver driver;
  public String host;
  public Integer implicitWait = 15;
  public String projectName = "My Project";
  public String projectVersion = "1.0";
  public String jobName = "My Job";
  public String[] tags = new String[] {"tag1", "tag2"};
  public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
  public Capabilities capabilities = new Capabilities();
  
  public PerfectoExecutionContext perfectoExecutionContext;
  public ReportiumClient reportiumClient;
  
  
  public Application application;
  public Audio audio;
  public Cloud cloud;
  public Device device;
  public Network network;
  public VisualAnalysis visualAnalysis;
  public UserActions userActions;

  public Integer pollingRetryInterval = 1;
  public Integer waitForElementTimeout = 15;
  public Wait<WebDriver> wait;
  
  public PerfectoDriver()
  {
    
  } 
  
  public void init()
  {
    if (host == null)
    {
      Logger.LogError("No host provided for driver URL. Exiting.");
      return;
    }
    
    try {
      URL driverUrl = new URL("https://" + host + ".perfectomobile.com/nexperience/perfectomobile/wd/hub/fast");
      Logger.LogInfo("Driver URL: " + driverUrl);
      
      if (capabilities.securityToken == null)
        capabilities.securityToken = Authenticator.getTokenForCloud(host);
      
      desiredCapabilities.merge(capabilities.toSeleniumCapabilities());
      
      Map <String, Object> caps = (Map<String, Object>) desiredCapabilities.asMap();
      Logger.LogInfo("-----------------------------------------------");
      Logger.LogInfo("Using capabilities:");
      for (Map.Entry<String, Object> entry : caps.entrySet())
        Logger.LogInfo(entry.getKey() + ": " + entry.getValue()); 
      
      if (desiredCapabilities.getPlatform() == Platform.ANDROID)
        driver = new AndroidDriver(driverUrl, desiredCapabilities);
      else if(desiredCapabilities.getPlatform() == Platform.IOS)
        driver = new IOSDriver(driverUrl, desiredCapabilities);
      else
        driver = new AppiumDriver(driverUrl, desiredCapabilities);
      Logger.LogDebug("Created " + driver + " type object");
      
      userActions = new UserActions(driver);
      visualAnalysis = new VisualAnalysis(driver);
      application = new Application(driver);
      device = new Device(driver);
      audio = new Audio(driver);
      network = new Network(driver);
      cloud = new Cloud(driver);
      
      driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
      
      perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
        .withProject(new Project(projectName, projectVersion))
        .withJob(new Job(jobName, 45))
        .withContextTags(tags)
        .withWebDriver(driver)
        .build();
      
      reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
      
      wait = new FluentWait<WebDriver>(driver)
        .withTimeout(waitForElementTimeout, TimeUnit.SECONDS)
        .pollingEvery(pollingRetryInterval, TimeUnit.SECONDS)
        .ignoring(org.openqa.selenium.NoSuchElementException.class);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void init(Capabilities capabilities)
  {
    this.capabilities = capabilities;
    init();
  }
  
  public void init(String host, Capabilities capabilities)
  {
    this.host = host;
    this.capabilities = capabilities;
    
    init();
  }
  
  public void sleep(Integer seconds) throws InterruptedException
  {
    Logger.LogInfo("Waiting for " + seconds + " seconds");
    Thread.sleep(seconds * 1000);
  }
}