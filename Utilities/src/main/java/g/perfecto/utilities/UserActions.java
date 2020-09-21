package g.perfecto.utilities;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.appium.java_client.MobileBy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserActions {

  private PerfectoDriver perfectoDriver;
  private Log log;
  
  private final String COMMAND_DRAG = "mobile:touch:drag";
  private final String COMMAND_SWIPE = "mobile:touch:swipe";
  private final String COMMAND_TAP = "mobile:touch:tap";
  private final String COMMAND_PRESS_KEY = "mobile:presskey";
  private final String COMMAND_SEND_KEY = "mobile:key:event";
  
  public Integer pollingRetryInterval = 1;
  public Integer waitForElementTimeout = 15;
  public Wait<WebDriver> fluentWait;
  public Integer waitAfterSwipe = 0;
  public Integer swipeDuration;
  
  public UserActions(PerfectoDriver driver)
  {
    perfectoDriver = driver;
    log = LogFactory.getLog(this.getClass());
    log.debug("Creating " + this.getClass() + " object");
    
    fluentWait = new FluentWait<WebDriver>(perfectoDriver.driver)
      .withTimeout(waitForElementTimeout, TimeUnit.SECONDS)
      .pollingEvery(pollingRetryInterval, TimeUnit.SECONDS)
      .ignoring(NoSuchElementException.class);
  }

  /**
   * Performs the drag gesture
   * @param location: either "x1,y1,x2,y2" or "x1%,y1%,x2%,y2%"
   * @param duration: 1-30
   * @param auxiliary: tap | notap | down | up
   * @return
   */
  public Boolean drag(String location, int duration, String auxiliary) 
  {

    Map<String, Object> params = new HashMap<>();

    if (location == null || location.isEmpty()){
      log.error("Drag location not provided!");
      return false;
    }

    params.put("location", location);

    if (duration > 0) 
      params.put("duration", duration);

    if (auxiliary != null && !auxiliary.isEmpty())
      params.put("auxiliary", auxiliary);

    return perfectoDriver.executor.executeMethod(COMMAND_DRAG, params);
  }


  public Boolean drag(String location, int duration)
  {
    return drag(location, duration, null);
  }


  public Boolean drag(String location)
  {
    return drag(location, 1, null);
  }



  /**
   * mobile:touch:swipe
   * Performs the swipe gesture.
   * @param start: "x,y" or "x%,y%"
   * @param end: "x,y" or "x%,y%"
   * @param duration: 1-30
   * @param repeat - how many times to repeat the swipe operation
   * @return
   */
  public void swipe(String start, String end, Integer duration, Integer repeat)
  {
    Map<String, Object> params = new HashMap<>();

    params.put("start", start);
    params.put("end", end);
    
    if (duration != null)
      swipeDuration = duration;
    
    if (swipeDuration != null)
      params.put("duration", duration);
    
    if (repeat == null)
      repeat = 1;
    else
      log.info("repeat: " + repeat);
    
    for (int i=0; i<repeat; i++)
    {
      perfectoDriver.executor.executeMethod(COMMAND_SWIPE, params);
      if (waitAfterSwipe != null)
        wait(waitAfterSwipe, "Waiting 3 seconds to finish swiping");   
    }
  }
  
  /**
   * mobile:touch:swipe
   * Performs the swipe gesture.
   * @param start: "x,y" or "x%,y%"
   * @param end: "x,y" or "x%,y%"
   * @param duration: 1-30
   * @return
   */
  public void swipe(String start, String end, Integer duration)
  {
    swipe(start, end, duration, null);
  }

  public void swipe(String start, String end)
  {
    swipe(start, end, null, null);
  }

  public void swipeUp()
  {
    log.info("Swiping up");
    swipe("50%,65%", "50%,45%", null, null);
  }

  public void swipeUp(int duration)
  {
    log.info("Swiping up");
    swipe("50%,65%", "50%,45%", duration, null);
  }

  public void swipeDown()
  {
    log.info("Swiping down");
    swipe("50%,45%", "50%,65%", null, null);
  }

  public void swipeDown(int duration)
  {
    log.info("Swiping down");
    swipe("50%,45%", "50%,65%", duration, null);
  }

  public void swipeLeft()
  {
    log.info("Swiping left");
    swipe("65%,50%", "45%,50%", null, null);
  }
  
  public void swipeLeft(int duration)
  {
    log.info("Swiping left");
    swipe("65%,50%", "45%,50%", duration, null);
  }

  public void swipeRight()
  {
    log.info("Swiping right");
    swipe("45%,50%", "65%,50%", null, null);
  }
  
  public void swipeRight(int duration)
  {
    log.info("Swiping right");
    swipe("45%,50%", "65%,50%", duration, null);
  }

  /**
   * mobile:touch:tap
   * Performs a touch operation on the specified coordinate location.
   * A long-press can be performed by using the duration parameter.
   * @param location either "x,y" or "x%,y%"
   * @param duration 1-30
   * @return
   */
  public Boolean tap(String location, int duration ) 
  {

    Map<String, Object> params = new HashMap<>();

    if (location == null || location.trim().length() == 0){
      log.error("Drag location not provided!");
      return false;
    }

    params.put("location", location);

    if (duration > 0)
      params.put("duration", duration);

    return perfectoDriver.executor.executeMethod(COMMAND_TAP, params);
  }

  public Boolean tap(String location) 
  {
    return tap(location, 0);
  }	

  public Boolean tap() 
  {
    log.info("Tapping in the center of screen");
    return tap("50%,50%");
  }

  public Boolean tapTopLeft() 
  {
    log.info("Tapping top left");
    return tap("1%,1%");
  }

  public Boolean tapTopRight() 
  {
    return tap("99%,1%");
  }

  public Boolean tapBottomLeft() 
  {
    return tap("1%,99%");
  }

  public Boolean tapBottomRight() 
  {
    return tap("99%,99%");
  }
  
  public Boolean tapBottom()
  {
    return tap("50%,95%");
  }

  /**
   * Clicks on a single or sequence of device special keys (either control or keyboard).
   * @param key The physical device keys to press. It is possible to select a sequence of comma separated keys.
   * @return
   */
  public Boolean pressKey(String key)
  {
    return pressKey(key, null);
  }

  /**
   * Clicks on a single or sequence of device special keys (either control or keyboard).
   * @param key The physical device keys to press. It is possible to select a sequence of comma separated keys.
   * @param keypad Specify the logical name of the virtual keypad to be used. 
   * @return
   */
  public Boolean pressKey(String key, String keypad)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("keySequence", key);

    if (keypad != null && !keypad.isEmpty())
      params.put("keypad", keypad);

    return perfectoDriver.executor.executeMethod(COMMAND_PRESS_KEY, params);
  }

  public Boolean pressBack()
  {
    return pressKey("BACK");
  }
  
  public Boolean pressHome()
  {
    return pressKey("HOME");
  }

  public Boolean pressOk()
  {
    return pressKey("OK");
  }
  
  public Boolean pressLeft()
  {
    return pressKey("LEFT");
  }

  public Boolean pressRight()
  {
    return pressKey("RIGHT");
  }

  public Boolean pressUp()
  {
    return pressKey("UP");
  }

  public Boolean pressDown()
  {
    return pressKey("DOWN");
  }
  
  public Boolean pressVolumeUp()
  {
    return pressKey("VOL_UP");
  }

  public Boolean pressVolumeDown()
  {
    return pressKey("VOL_DOWN");
  }

  public Boolean pressCamera()
  {
    return pressKey("CAMERA");
  }

  public Boolean pressClear()
  {
    return pressKey("CLEAR");
  }

  public Boolean pressAppSwitch()
  {
    return pressKey("APP_SWITCH");
  }

  public Boolean pressLongHome()
  {
    return pressKey("LONGHOME");
  }

  /**
   * Sends a key event to the device, using key codes and metastates.
   * @param keyEvent The key code key event to send to the device.
   * @param metastate The metastate key event to send to the device.
   * @return
   */
  public Boolean sendKeyEvent(Integer keyEvent, Integer metastate)
  {	
    Map<String, Object> params = new HashMap<>();
    params.put("key", keyEvent);

    if (metastate != null)
      params.put("metastate", metastate );

    return perfectoDriver.executor.executeMethod(COMMAND_SEND_KEY, params);
  }

  /**
   * Sends a key event to the device, using key codes and metastates.
   * @param keyEvent The key code key event to send to the device.
   * @return
   */
  public Boolean sendKeyEvent(Integer keyEvent)
  {
    return sendKeyEvent(keyEvent, null);
  }

  /**
   * Wrapper of WebElement.Click method with additional logging
   * @param element
   */
  public void clickOnElement(WebElement element)
  {
    log.info("------------------------------------------------");
    log.info("Clicking on element " + element.toString());
    element.click();
  }
  
  public void clickOn(By by)
  {
    clickOnElement(perfectoDriver.driver.findElement(by));
  }
  
  public void clickOn(String xpath)
  {
    clickOnElement(perfectoDriver.driver.findElement(By.xpath(xpath)));
  }

  public void clickOnXpath(String xpath) {
    clickOn(xpath);
  }
  
  public void clickOnId(String id)
  {
    clickOnElement(perfectoDriver.driver.findElement(By.id(id)));
  }

  public void clickOnAccessibilityId(String id)
  {
    clickOnElement(perfectoDriver.driver.findElementByAccessibilityId(id));
  }
  
  public void clickOnLabel(String label)
  {
    clickOnElement(perfectoDriver.driver.findElement(By.xpath("//*[@label=\"" + label + "\"]")));
  }

  public void clickOnValue(String value)
  {
    clickOnElement(perfectoDriver.driver.findElement(By.xpath("//*[@value=\"" + value + "\"]")));
  }

  public void clickOnText(String text)
  {
    clickOnElement(perfectoDriver.driver.findElement(By.xpath("(//*[@value=\"" + text + "\"] | //*[@label=\"" + text + "\"] | //*[@text=\"" + text + "\"] | //*[text()=\"" + text + "\"])[1]")));
  }

  public void clickOnName(String name) {
    clickOnElement(perfectoDriver.driver.findElement(By.xpath("//*[@name='" + name + "']")));
  }
 

  public void tryClickOnElement(WebElement element)
  {
    try {
      clickOnElement(element);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Boolean tryClickOn(By by)
  {
    try {
      log.info("------------------------------------------------");
      log.info("Clicking on element " + by);
      perfectoDriver.driver.findElement(by).click();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  
  public Boolean tryClickOn(String xpath)
  {
    return tryClickOn(By.xpath(xpath));
  }

  public Boolean tryClickOnId(String id)
  {
    return tryClickOn(By.id(id));
  }

  public Boolean tryClickOnLabel(String label)
  {
    return tryClickOn(By.xpath("//*[@label='" + label + "']"));
  }

  public Boolean tryClickOnValue(String value)
  {
    return tryClickOn(By.xpath("//*[@value='" + value + "']"));
  }

  public Boolean tryClickOnText(String text)
  {
    return tryClickOn(By.xpath("//*[@label='" + text + "'] | //*[@value='" + text + "'] | //*[@text='" + text + "'] | //*[@text()='" + text + "']"));
  }
  
  public void type(String text, WebElement el, Boolean clear)
  {
    if (clear == true)
    {
      log.info("Clearing text from element " + el);
      el.clear();
    }
    
    log.info("Typing text " + text + " in element " + el);
    el.sendKeys(text);
  }
  
  public void type(String text, WebElement el)
  {
    type(text, el, false);
  }

  public void type(String text, By by, Boolean clear)
  {
    type(text, perfectoDriver.driver.findElement(by), clear);
  }

  public void type(String text, By by)
  {
    type(text, perfectoDriver.driver.findElement(by));
  }
  
  public void type(String text, String xpath, Boolean clear)
  {
    type(text, perfectoDriver.driver.findElement(By.xpath(xpath)), clear);
  }  
  
  public void type(String text, String xpath)
  {
    type(text, perfectoDriver.driver.findElement(By.xpath(xpath)));
  }

  public void tryType(String text, String xpath, Boolean clear)
  {
    try {
      type(text, perfectoDriver.driver.findElement(By.xpath(xpath)), clear);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tryType(String text, String xpath)
  {
    try {
      type(text, perfectoDriver.driver.findElement(By.xpath(xpath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tryType(String text, WebElement el, Boolean clear)
  {
    try {
      type(text, el, clear);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void tryType(String text, WebElement el)
  {
    try {
      type(text, el, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void tryType(String text, By by, Boolean clear)
  {
    type(text, perfectoDriver.driver.findElement(by), clear);
  }

  public void tryType(String text, By by)
  {
    type(text, perfectoDriver.driver.findElement(by));
  }
  
  public String takeScreenshot()
  {
    log.info("Taking screenshot as base64");
    return perfectoDriver.driver.getScreenshotAs(OutputType.BASE64);
  }
  
  public String takeScreenshotAsBase64()
  {
    log.info("Taking screenshot as base64");
    return perfectoDriver.driver.getScreenshotAs(OutputType.BASE64);
  }
  
  public String tryTakeScreenshot()
  {
    try {
      return takeScreenshot();
    } catch (WebDriverException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public File takeScreenshotAsFile()
  {
    log.info("Taking screenshot as file");
    return perfectoDriver.driver.getScreenshotAs(OutputType.FILE);
  }

  public byte[] takeScreenshotAsBytes()
  {
    log.info("Taking screenshot as file");
    return perfectoDriver.driver.getScreenshotAs(OutputType.BYTES);
  }

  public void clickOnCoordinates(String string) {
    tap(string);    
  }
  
  public WebElement waitForElement(MobileBy by)
  {
    log.info("Waiting " + waitForElementTimeout + " seconds for element located by " + by);
    return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));    
  }
  
  public WebElement waitForElement(By by)
  {
    log.info("Waiting " + waitForElementTimeout + " seconds for element located by " + by);
    return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));    
  }
  
  public WebElement waitForElement(String xpath)
  {
    log.info("Waiting " + waitForElementTimeout + " seconds for element located by xpath " + xpath);
    return fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));    
  }
  
  public WebElement waitForElementByName(String name)
  {
    return waitForElement("//*[@name='" + name + "']");
  }
  
  
  public WebElement waitForElementByLabel(String label)
  {
    return waitForElement("//*[@label='" + label + "']");    
  }
  

  public WebElement waitForElementById(String id)
  {
    log.info("Waiting " + waitForElementTimeout + " seconds for element located by id " + id);
    return fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));    
  }
  

  public WebElement waitForElementByText(String text)
  {
    return waitForElement("(//*[@label='" + text + "'] | //*[@value='" + text + "'] | //*[@text='" + text + "'] | //*[text()='" + text + "'])[1]");    
  }

  public void waitAndClickOnElelent(By by)
  {
    log.info("Waiting for element to click on " + by);
    WebElement el = fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    clickOnElement(el);
  }
  
  public void waitAndClickOnElelent(String xpath)
  {
    log.info("Waiting " + waitForElementTimeout + " seconds for element to click on " + xpath);
    WebElement el = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    clickOnElement(el);
  }
  
  public void wait(int seconds)
  {
    wait(seconds, null);
  }
  
  public void wait(int seconds, String message)
  {
    if (message == null || message.isEmpty())
      log.info("Waiting for " + seconds + " seconds");
    else
      log.info(message);
    
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ignore) {
      log.error("Waiting canceled!");
    }
  }

  public void setWaitForElementTimeout(int timeout)
  {
    setFluentWait(timeout, pollingRetryInterval);
  }
  
  public void setFluentWait(int waitForElementTimeout, int pollingRetryInterval) {
    this.waitForElementTimeout = waitForElementTimeout;
    this.pollingRetryInterval = pollingRetryInterval;
    
    this.fluentWait = new FluentWait<WebDriver>(perfectoDriver.driver)
        .withTimeout(waitForElementTimeout, TimeUnit.SECONDS)
        .pollingEvery(pollingRetryInterval, TimeUnit.SECONDS)
        .ignoring(org.openqa.selenium.NoSuchElementException.class);
  }

  public void waitAndClickOnElelentByText(String text) {
    waitAndClickOnElelent("//*[@label='" + text + "'] | //*[@value='" + text + "'] | //*[@text='" + text + "'] | //*[text()='" + text + "']");
  }
  
  public WebElement findElementByAccessibilityId(String id)
  {
    log.info("Finding element by accesibility id " + id);
    return perfectoDriver.driver.findElementByAccessibilityId(id);
  }

  public WebElement waitForElementToBeClickable(By by) {
    return fluentWait.until(ExpectedConditions.elementToBeClickable(by));
  }

  public boolean elementExists(String xpath) 
  {  
    try {
      perfectoDriver.driver.findElement(By.xpath(xpath));
      return true;
    } catch (Exception e) {}
    return false;
  }


  public boolean elementExists(By by) {
    try {
      perfectoDriver.driver.findElement(by);
      return true;
    } catch (Exception e) {}
    return false;
  }
  
  public boolean labelExists(String label) 
  {  
    try {
      perfectoDriver.driver.findElement(By.xpath("//*[@label='" + label + "']"));
      return true;
    } catch (Exception e) {}
    return false;
  }

  
  public WebElement getElement(String xpath) {
    return perfectoDriver.driver.findElement(By.xpath(xpath));
  }

  public void changeFluentWaitTimeOut(int waitForElementTimeout) {
    fluentWait = new FluentWait<WebDriver>(perfectoDriver.driver)
        .withTimeout(waitForElementTimeout, TimeUnit.SECONDS)
        .pollingEvery(pollingRetryInterval, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);
  }
 
  public void changePollingRetryInterval(int pollingRetryInterval) {
    fluentWait = new FluentWait<WebDriver>(perfectoDriver.driver)
        .withTimeout(waitForElementTimeout, TimeUnit.SECONDS)
        .pollingEvery(pollingRetryInterval, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);
  }

  public void fingerAuthenticate()
  {
    perfectoDriver.application.fingerAuthenticate();
  }
  
  public void comment(String text)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("text", text);
    perfectoDriver.executor.executeScript("mobile:comment", params);
  }
}
