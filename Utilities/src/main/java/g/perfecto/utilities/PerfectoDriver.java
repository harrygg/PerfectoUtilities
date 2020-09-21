package g.perfecto.utilities;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class PerfectoDriver
{
  public AppiumDriver<WebElement> driver;
  public RemoteWebDriver remoteDriver;
  public Executor executor;
  public URL driverUrl;
  public String host;
  public Integer implicitWait = 15;
  public String testName = "";
  public String projectName = "My Project";
  public String projectVersion = "1.0";
  public String jobName = "My Job";
  public Integer jobNumber = 0;
  public String[] tags = new String[] {"tag1", "tag2"};
  public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
  public Capabilities capabilities = new Capabilities();
  
  public PerfectoExecutionContext perfectoExecutionContext;
  public ReportiumClient reportiumClient;
  public JavascriptExecutor jsExecutor;
  
  public String reportFileName = "report";
  
  public Application application;
  public Audio audio;
  public Cloud cloud;
  public Device device;
  public Network network;
  public VisualAnalysis visualAnalysis;
  public UserActions userActions;
  public Browser browser;
  
  private static Log log = LogFactory.getLog(PerfectoDriver.class);
  public static String logLevel = "";
  public PerfectoDriver()
  {    
  } 
  
  public PerfectoDriver(String cloudName)
  {
    this();
    host = cloudName;
  } 

  public PerfectoDriver(String cloudName, Capabilities capabilities)
  {
    this();
    host = cloudName;
    this.capabilities = capabilities;
    init();
  }
  
  public void init(String cloudName, Capabilities capabilities)
  {
    host = cloudName;
    this.capabilities = capabilities;
    
    init();
  }
  
  public void init(Capabilities capabilities)
  {
    this.capabilities = capabilities;
    init();
  }
  
  public void init(String cloudName)
  {
    host = cloudName;
    init();
  }
  
  public void init()
  {
    if (host == null)
    {
      log.error("No host provided for driver URL. Exiting.");
      return;
    }
    
    try {
      driverUrl = new URL("https://" + host + ".perfectomobile.com/nexperience/perfectomobile/wd/hub/fast");
      log.info("Driver URL: " + driverUrl);
    } catch (Exception e) {
      e.printStackTrace();
    }  
      
    if (capabilities.securityToken == null)
      capabilities.securityToken = TokenStorage.getTokenForCloud(host);
    
    capabilities.takesScreenshot = true;
    capabilities.openDeviceTimeout = 2;
    
    if (capabilities.reportJobName != null)
      jobName = capabilities.reportJobName;
    if (capabilities.reportJobNumber != null)
      jobNumber = capabilities.reportJobNumber;
    
    desiredCapabilities.merge(capabilities.toSeleniumCapabilities());
    Map <String, Object> caps = (Map<String, Object>) desiredCapabilities.asMap();
    log.info("-----------------------------------------------");
    log.info("Using capabilities:");
    for (Map.Entry<String, Object> entry : caps.entrySet())
      log.info(entry.getKey() + ": " + entry.getValue()); 
    
    if (desiredCapabilities.getPlatform() == Platform.ANDROID)
      driver = new AndroidDriver<WebElement>(driverUrl, desiredCapabilities);
    else if(desiredCapabilities.getPlatform() == Platform.IOS)
      driver = new IOSDriver<WebElement>(driverUrl, desiredCapabilities);
    else
      driver = new AppiumDriver<WebElement>(driverUrl, desiredCapabilities);
    log.debug("Created " + driver + " type object");
    
//    Logger.LogInfo(driver.getClass().getPackage().getSpecificationTitle().toString());
//    Logger.LogInfo(driver.getClass().getPackage().getSpecificationVersion().toString());
//    
    executor = new Executor(driver);
    userActions = new UserActions(this);
    visualAnalysis = new VisualAnalysis(this);
    application = new Application(this);
    device = new Device(this);
    audio = new Audio(this);
    network = new Network(this);
    cloud = new Cloud(this);
    browser = new Browser(this);
    
    driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    
    perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
      .withProject(new Project(projectName, projectVersion))
      .withJob(new Job(jobName, jobNumber))
      .withContextTags(tags)
      .withWebDriver(driver)
      .build();
    
    log.debug("Creating new ReportiumClient");
    reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
    log.debug("Created " + reportiumClient.getClass().getPackage().getSpecificationTitle() + "  " + reportiumClient.getClass().getPackage().getSpecificationVersion());
    
  }

  
  public void executeJS(String script, Object args)
  {
    log.info("Executing JavaScript code");
    jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript(script, args);
  }
  
  
  
  public void downloadPdfReport(String fileName)
  {
    downloadReport("pdf", fileName); 
  }
  
  public void downloadPdfReport()
  {
    downloadReport("pdf", this.reportFileName); 
  }
  


  /**
   * Download the report. 
   * type - pdf, html, csv, xml
   * Example: downloadReport(driver, "pdf", "C:\\test\\report");
     *
   * Note that this method is relevant only for local hosted device lab (AKA "On Premise") and not for DigitalZoom (AKA ReportiumClient) users
   */
  public void downloadReport(String type, String fileName)
  {
    if (fileName == null || fileName.isEmpty())
      fileName = reportFileName;
    
    try {
      Map<String, Object> params = new HashMap<>(); 
      params.put("type", type); 
      String report = executor.executeMethodString("mobile:report:download", params);
      
      File reportFile = new File(fileName + "." + type); 
      BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile)); 
      byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report); 
      output.write(reportBytes);
      output.close();
    } catch (Exception ex) { 
      ex.printStackTrace();
    }
  }

  /**
   * Download all the report attachments with a certain type.
   * type - video, image, vital, network
   * Examples:
   * downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
   * downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");
     *
     * Note that this method is relevant only for local hosted device lab (AKA "On Premise") and not for DigitalZoom (AKA ReportiumClient) users
   */
  public void downloadAttachment(String type, String fileName, String suffix) throws IOException {
    try {
      String command = "mobile:report:attachment";
      boolean done = false;
      int index = 0;

      while (!done) {
        Map<String, Object> params = new HashMap<>(); 

        params.put("type", type);
        params.put("index", Integer.toString(index));

        String attachment = executor.executeMethodString(command, params);

        if (attachment == null) { 
          done = true; 
        }
        else 
        { 
          File file = new File(fileName + index + "." + suffix); 
          BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file)); 
          byte[] bytes = OutputType.BYTES.convertFromBase64Png(attachment); 
          output.write(bytes); 
          output.close(); 
          index++; 
        }
      }
    } catch (Exception ex) { 
      System.out.println("Got exception " + ex); 
    }
  }


  /**
   * Uploads a file to a media. Example:
   * uploadMedia("C:\\test\\ApiDemos.apk", "PRIVATE:apps/ApiDemos.apk");
   * @param path
   * @param repositoryKey
   * @throws IOException
   */
  public Boolean uploadMedia(String path, String repositoryKey)
  {
    log.info("Uploading local media file '" + path + "' to repository " + repositoryKey + " on " + host + " cloud");
    File file = new File(path);
    byte[] content = readFile(file);
    return uploadMedia(content, repositoryKey);
  }

/**
 * Example: uploadMedia(url, "PRIVATE:apps/ApiDemos.apk");
 * @param mediaURL
 * @param repositoryKey
 * @throws IOException
 */
  public Boolean uploadMedia(URL mediaURL, String repositoryKey) 
  {
    log.info("Uploading media file from URL '" + mediaURL + "' to repository " + repositoryKey + " on " + host + " cloud");
    byte[] content = readURL(mediaURL);
    return uploadMedia(content, repositoryKey);
  }

/**
 * Example: uploadMedia("demo.perfectomobile.com", content, "PRIVATE:apps/ApiDemos.apk");
 * @param content
 * @param repositoryKey
 * @throws UnsupportedEncodingException
 * @throws MalformedURLException
 * @throws IOException
 */
  public Boolean uploadMedia(byte[] content, String repositoryKey)
  {
    log.info("Uploading content to repository " + repositoryKey + " on " + host + " cloud");
    String urlStr = "https://" + host + ".perfectomobile.com/services/repositories/media/" + repositoryKey + "?" + "operation=upload&overwrite=true&securityToken=" + capabilities.securityToken;
    URL url;
    try 
    {
      url = new URL(urlStr);
      return sendRequest(content, url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return false;
    }
  }

  private Boolean sendRequest(byte[] content, URL url) 
  {
    try {
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/octet-stream");
      connection.connect();
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      outStream.write(content);
      outStream.writeTo(connection.getOutputStream());
      outStream.close();
      int code = connection.getResponseCode();
      if (code == HttpURLConnection.HTTP_OK) 
      {
        log.info("Media uploaded successfully!");
        return true;
      }
      else
      {
        log.error("Status code: " + code);
        handleError(connection);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  private void handleError(HttpURLConnection connection) throws IOException {
    String msg = "Failed to upload media.";
    InputStream errorStream = connection.getErrorStream();
    if (errorStream != null) {
      InputStreamReader inputStreamReader = new InputStreamReader(errorStream, "UTF-8");
      BufferedReader bufferReader = new BufferedReader(inputStreamReader);
      try {
        StringBuilder builder = new StringBuilder();
        String outputString;
        while ((outputString = bufferReader.readLine()) != null) {
          if (builder.length() != 0) {
            builder.append("\n");
          }
          builder.append(outputString);
        }
        String response = builder.toString();
        msg += "Response: " + response;
      }
      finally {
        bufferReader.close();
      }
    }
    throw new RuntimeException(msg);
  }

  private byte[] readFile(File path)
  {
    try {
      int length = (int)path.length();
      byte[] content = new byte[length];
      InputStream inStream = new FileInputStream(path);
      try {
        inStream.read(content);
      }
      finally {
        inStream.close();
      }
      return content;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String httpGet(String url)
  {
    try {
      return new String(readURL(new URL(url)));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private byte[] readURL(URL url) 
  {
    try 
    {
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setDoOutput(true);
      connection.setRequestProperty("User-Agent", "Chrome");

      int code = connection.getResponseCode();
      if (code > HttpURLConnection.HTTP_OK) 
        handleError(connection);
      
      InputStream stream = connection.getInputStream();
      if (stream == null) 
        throw new RuntimeException("Failed to get content from url " + url + " - no response stream");
      
      byte[] content = read(stream);
    
      return content;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private byte[] read(InputStream input) 
  {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      byte[] buffer = new byte[1024];
      int nBytes = 0;
      try {
        while ((nBytes = input.read(buffer)) > 0) {
          output.write(buffer, 0, nBytes);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } finally {
      try {
        input.close();
      } catch (IOException e){
      }
    }
    return output.toByteArray();
  }

  public void goToUrl(String url) 
  {
    log.debug("step started");
    log.info("Going to URL: " + url);
    driver.get(url);
    log.debug("step ended");
  }

  public void savePageSource(String filePath)
  {
    File file;    

    try {
      
      if (filePath != null && !filePath.isEmpty())
        file = new File(filePath);
      else
        file = new File(System.getenv("TEMP"), "pageSource.txt");
      
      log.info("Saving source code to " + file.toString());
      PrintWriter writer = new PrintWriter(file);
      writer.print(driver.getPageSource());
      writer.close();
      log.info("Source code saved!");
      
    } catch (Exception e) {
      log.error("Failed to save page source!");
      e.printStackTrace();
    }
  }
  
  public void savePageSource() 
  {
    savePageSource(null);
  }


  public void testStart(String name) 
  {
    if (reportiumClient != null)
    {
      log.info("##########################");
      log.info("#      STARTING TEST     #");
      log.info("##########################");
      log.info("Test name: " + name);
      reportiumClient.testStart(name, new TestContext());      
    }
  }
  

  public void testStart(String name, String[] tags) 
  {
    if (reportiumClient != null)
    {
      log.info("Starting test: " + name);
      reportiumClient.testStart(name, new TestContext(tags));
    }
  }
  
  public void testStop() 
  {
    userActions.wait(3, "Waiting 3 seconds for any pending actions before ending the test");
    if (reportiumClient != null)
    {
      log.info("Stopping test");
      reportiumClient.testStop(TestResultFactory.createSuccess());      
    }
  }
  
  public void testStop(String message) 
  {
    userActions.wait(3, "Waiting 3 seconds for any pending actions before ending the test");
    if (reportiumClient != null)
    {
      log.info("Stopping test");
      reportiumClient.testStop(TestResultFactory.createFailure(message)); 
    }
  }
  
  public void testStop(Exception e) 
  {
    userActions.wait(3, "Waiting 3 seconds for any pending actions before ending the test");
    if (reportiumClient != null)
    {
      log.info("Stopping test due to exception");
      reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e)); 
    }
  }
  
  public void testStop(String message, Exception e, String failureReasonCode) 
  {
    userActions.wait(3, "Waiting 3 seconds for any pending actions before ending the test with failure reason code " + failureReasonCode);
    if (reportiumClient != null)
    {
      log.info("Stopping test due to exception");
      reportiumClient.testStop(TestResultFactory.createFailure(message, e, failureReasonCode)); 
    }
  }
  public void driverQuit()
  {
    driverQuit(true);  
  }
  
  public void driverQuit(Boolean waitForReport) 
  {
    try {
      log.info("Executing driver.quit();");
      if (driver != null)
        driver.quit();
      log.info("Driver has quit!");
      
      if (waitForReport && reportiumClient != null)
      {
        log.info("Getting report URL");
        String reportURL = reportiumClient.getReportUrl();
        log.info("##########################");
        log.info("#       REPORT URL       #");
        log.info("##########################");
        log.info(reportURL);
        java.awt.Desktop.getDesktop().browse(new URI(reportURL));
      }
    } catch (Exception e) {
        e.printStackTrace();
    }

    
  }

  private String currentStep = null;
  
  public void stepStart(String stepName) 
  {
    currentStep = stepName;
    log.info("Starting step: " + stepName);
    reportiumClient.stepStart(stepName);
  }

  public void stepEnd()
  {
    log.info("Ending step: " + currentStep);
    reportiumClient.stepEnd();
  }
  
  
  public void comment(String text)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("text", text);
    executor.executeScript("mobile:comment", params);
  }
}