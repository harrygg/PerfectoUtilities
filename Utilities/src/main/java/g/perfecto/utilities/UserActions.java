package g.perfecto.utilities;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.exec.ExecuteStreamHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserActions {

  private AppiumDriver driver;

  private final String COMMAND_DRAG = "mobile:touch:drag";
  private final String COMMAND_SWIPE = "mobile:touch:swipe";
  private final String COMMAND_TAP = "mobile:touch:tap";
  private final String COMMAND_PRESSKEY = "mobile:presskey";
  private final String COMMAND_SENDKEY = "mobile:key:event";
  
  private Log log = LogFactory.getLog(UserActions.class);
  
  public UserActions(AppiumDriver driver) 
  {
    log.debug("Creating UserActions object");
    this.driver = driver;
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

    return Helper.executeMethod(driver, COMMAND_DRAG, params);
  }


  public Boolean drag(String location, int duration)
  {
    return drag(location, duration, null);
  }


  public Boolean drag(String location)
  {
    return drag(location, 1, null);
  }


  public Boolean swipe(String start, String end)
  {
    return swipe(start, end, 3);
  }

  /**
   * mobile:touch:swipe
   * Performs the swipe gesture.
   * @param start: "x,y" or "x%,y%"
   * @param end: "x,y" or "x%,y%"
   * @param duration: 1-30
   * @return
   */
  public Boolean swipe(String start, String end, int duration)
  {
    Map<String, Object> params = new HashMap<>();

    if (start == null || start.trim().length() == 0){
      log.error("Start location not provided!");
      return false;
    } 
    if (end == null || end.trim().length() == 0){
      log.error("End location not provided!");
      return false;
    }

    params.put("start", start);
    params.put("end", end);
    params.put("duration", duration);

    return Helper.executeMethod(driver, COMMAND_SWIPE, params);
  }

  public Boolean SwipeUp(){
    return swipe("65%,50%", "45%,50%");
  }

  public Boolean SwipeUp(int duration){
    return swipe("65%,50%", "45%,50%", duration);
  }

  public Boolean SwipeDown(){
    return swipe("45%,50%", "65%,50%");
  }

  public Boolean SwipeDown(int duration){
    return swipe("45%,50%", "65%,50%", duration);
  }

  public Boolean SwipeLeft(){
    return swipe("50%,65%", "50%,45%");
  }
  public Boolean SwipeLeft(int duration){
    return swipe("50%,65%", "50%,45%", duration);
  }

  public Boolean SwipeRight(){
    return swipe("50%,45%", "50%,65%");
  }
  public Boolean SwipeRight(int duration){
    return swipe("50%,45%", "50%,65%", duration);
  }

  /**
   * mobile:touch:tap
   * Performs a touch operation on the specified coordinate location.
   * A long-press can be performed by using the duration parameter.
   * @param location either "x,y" or "x%,y%"
   * @param duration 1-30
   * @return
   */
  public Boolean tap(String location, int duration ) {

    Map<String, Object> params = new HashMap<>();

    if (location == null || location.trim().length() == 0){
      log.error("Drag location not provided!");
      return false;
    }

    params.put("location", location);

    if (duration > 0)
      params.put("duration", duration);

    return Helper.executeMethod(driver, COMMAND_TAP, params);
  }

  public Boolean tap(String location) 
  {
    return tap(location, 0);
  }	

  public Boolean tap() 
  {
    return tap("50%,50%");
  }

  public Boolean tapTopLeft() 
  {
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
    if (key == null || key.trim().length() == 0)
    {
      log.error("key not provided");
      return false;
    }

    Map<String, Object> params = new HashMap<>();
    params.put("keySequence", key);

    if (keypad != null && keypad.trim().length() > 0)
      params.put("keypad", keypad);

    return Helper.executeMethod(driver, COMMAND_PRESSKEY, params);
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

    return Helper.executeMethod(driver, COMMAND_SENDKEY, params);
  }

  /**
   * Sends a key event to the device, using key codes and metastates.
   * @param keyEvent The key code key event to send to the device.
   * @return
   */
  public Boolean SendKeyEvent(Integer keyEvent)
  {
    return sendKeyEvent(keyEvent, null);
  }

  /**
   * Wrapper of WebElement.Click method with additional logging
   * @param element
   */
  public void clickOn(WebElement element)
  {
    log.info("------------------------------------------------");
    log.info("Clicking on element " + element.toString());
    element.click();
  }
  
  public void clickOn(By by)
  {
    clickOn(driver.findElement(by));
  }
  
  public void clickOn(String xpath)
  {
    clickOn(driver.findElement(By.xpath(xpath)));
  }

  public void ClickOnXpath(String xpath) {
    clickOn(xpath);
  }
  
  public void clickOnId(String id)
  {
    clickOn(driver.findElement(By.id(id)));
  }

  public void clickOnLabel(String label)
  {
    clickOn(driver.findElement(By.xpath("//*[@label=\"" + label + "\"]")));
  }

  public void clickOnValue(String value)
  {
    clickOn(driver.findElement(By.xpath("//*[@value=\"" + value + "\"]")));
  }

  public void clickOnText(String text)
  {
    clickOn(driver.findElement(By.xpath("(//*[@value=\"" + text + "\"] | //*[@label=\"" + text + "\"] | //*[text()=\"" + text + "\"])[1]")));
  }

  public void clickOnName(String name) {
    clickOn(driver.findElement(By.xpath("//*[@name='" + name + "']")));
  }
 

  public void tryClick(WebElement element)
  {
    try {
      clickOn(element);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tryClick(By by)
  {
    try {
      log.info("------------------------------------------------");
      log.info("Clicking on element " + by);
      driver.findElement(by).click();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  public void tryClick(String xpath)
  {
    tryClick(By.xpath(xpath));
  }

  public void tryClickOnId(String id)
  {
    tryClick(By.id(id));
  }

  public void tryClickOnLabel(String label)
  {
    tryClick(By.xpath("//*[@label='" + label + "']"));
  }

  public void tryClickOnValue(String value)
  {
    tryClick(By.xpath("//*[@value='" + value + "']"));
  }

  public void tryClickOnText(String text)
  {
    tryClick(By.xpath("//*[@label='" + text + "'] | //*[@value='" + text + "']"));
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
    type(text, driver.findElement(by), clear);
  }

  public void type(String text, By by)
  {
    type(text, driver.findElement(by));
  }
  
  public void type(String text, String xpath, Boolean clear)
  {
    type(text, driver.findElement(By.xpath(xpath)), clear);
  }  
  
  public void type(String text, String xpath)
  {
    type(text, driver.findElement(By.xpath(xpath)));
  }

  public void tryType(String text, String xpath, Boolean clear)
  {
    try {
      type(text, driver.findElement(By.xpath(xpath)), clear);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tryType(String text, String xpath)
  {
    try {
      type(text, driver.findElement(By.xpath(xpath)));
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
    type(text, driver.findElement(by), clear);
  }

  public void tryType(String text, By by)
  {
    type(text, driver.findElement(by));
  }
  
  public String takeScreenshot()
  {
    log.info("Taking screenshot as base64");
    return driver.getScreenshotAs(OutputType.BASE64);
  }
  
  public String takeScreenshotAsBase64()
  {
    log.info("Taking screenshot as base64");
    return driver.getScreenshotAs(OutputType.BASE64);
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
    return driver.getScreenshotAs(OutputType.FILE);
  }

  public byte[] takeScreenshotAsBytes()
  {
    log.info("Taking screenshot as file");
    return driver.getScreenshotAs(OutputType.BYTES);
  }

  public void clickOnCoordinates(String string) {
    tap(string);    
  }
}
