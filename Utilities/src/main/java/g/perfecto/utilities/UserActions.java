package g.perfecto.utilities;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.exec.ExecuteStreamHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserActions {
	
	private RemoteWebDriver driver = null;

	private final String COMMAND_DRAG = "mobile:touch:drag";
	private final String COMMAND_SWIPE = "mobile:touch:swipe";
	private final String COMMAND_TAP = "mobile:touch:tap";
	private final String COMMAND_PRESSKEY = "mobile:presskey";
	private final String COMMAND_SENDKEY = "mobile:key:event";
	
	
	public UserActions(RemoteWebDriver driver) {
	  Logger.LogDebug("Creating UserActions object");
		this.driver = driver;
	}	
	
	/**
	 * Performs the drag gesture
	 * @param location: either "x1,y1,x2,y2" or "x1%,y1%,x2%,y2%"
	 * @param duration: 1-30
	 * @param auxiliary: tap | notap | down | up
	 * @return
	 */
	public Boolean Drag(String location, int duration, String auxiliary) {
		
        Map<String, Object> params = new HashMap<>();
        
		if (location == null || location.trim().length() == 0){
			System.out.println("Drag location not provided!");
			return false;
		}
		
        params.put("location", location);
        
        if (duration > 0) 
        	params.put("duration", duration);
        
        if (auxiliary != null && auxiliary.trim().length() > 0)
        	params.put("auxiliary", auxiliary);
        
        return Helper.ExecuteMethod(driver, COMMAND_DRAG, params);
	}
	
	
	public Boolean Drag(String location, int duration){
		return Drag(location, duration, null);
	}
	
	
	public Boolean Drag(String location){
		return Drag(location, 1, null);
	}
	
	
	public Boolean Swipe(String start, String end)
	{
		return Swipe(start, end, 3);
	}
	
	/**
	 * mobile:touch:swipe
	 * Performs the swipe gesture.
	 * @param start: "x,y" or "x%,y%"
	 * @param end: "x,y" or "x%,y%"
	 * @param duration: 1-30
	 * @return
	 */
	public Boolean Swipe(String start, String end, int duration)
	{
		Map<String, Object> params = new HashMap<>();
		 
		if (start == null || start.trim().length() == 0){
			System.out.println("Start location not provided!");
			return false;
		} 
		if (end == null || end.trim().length() == 0){
			System.out.println("End location not provided!");
			return false;
		}
	
		params.put("start", start);
		params.put("end", end);
		params.put("duration", duration);
		
		return Helper.ExecuteMethod(driver, COMMAND_SWIPE, params);
	}
	
	public Boolean SwipeUp(){
		return Swipe("65%,50%", "45%,50%");
	}
	
	public Boolean SwipeUp(int duration){
		return Swipe("65%,50%", "45%,50%", duration);
	}
	
	public Boolean SwipeDown(){
		return Swipe("45%,50%", "65%,50%");
	}
	
	public Boolean SwipeDown(int duration){
		return Swipe("45%,50%", "65%,50%", duration);
	}
	

	public Boolean SwipeLeft(){
		return Swipe("50%,65%", "50%,45%");
	}
	public Boolean SwipeLeft(int duration){
		return Swipe("50%,65%", "50%,45%", duration);
	}
	
	
	public Boolean SwipeRight(){
		return Swipe("50%,45%", "50%,65%");
	}
	public Boolean SwipeRight(int duration){
		return Swipe("50%,45%", "50%,65%", duration);
	}
	
	/**
	 * mobile:touch:tap
	 * Performs a touch operation on the specified coordinate location.
	 * A long-press can be performed by using the duration parameter.
	 * @param location either "x,y" or "x%,y%"
	 * @param duration 1-30
	 * @return
	 */
	public Boolean Tap(String location, int duration ) {
		
		Map<String, Object> params = new HashMap<>();
        
		if (location == null || location.trim().length() == 0){
			System.out.println("ERROR! Drag location not provided!");
			return false;
		}
		
        params.put("location", location);
        
        if (duration > 0)
        	params.put("duration", duration);
        
		return Helper.ExecuteMethod(driver, COMMAND_TAP, params);
	}
	
	public Boolean Tap(String location) {
		return Tap(location, 0);
	}	
	
	public Boolean Tap() {
		return Tap("50%,50%");
	}
	
	public Boolean TapTopLeft() {
		return Tap("1%,1%");
	}
	
	public Boolean TapTopRight() {
		return Tap("99%,1%");
	}
	
	public Boolean TapBottomLeft() {
		return Tap("1%,99%");
	}
	
	public Boolean TapBottomRight() {
		return Tap("99%,99%");
	}
	
	/**
	 * Clicks on a single or sequence of device special keys (either control or keyboard).
	 * @param key The physical device keys to press. It is possible to select a sequence of comma separated keys.
	 * @return
	 */
	public Boolean PressKey(String key)
	{
		return PressKey(key, null);
	}
	
	/**
	 * Clicks on a single or sequence of device special keys (either control or keyboard).
	 * @param key The physical device keys to press. It is possible to select a sequence of comma separated keys.
	 * @param keypad Specify the logical name of the virtual keypad to be used. 
	 * @return
	 */
	public Boolean PressKey(String key, String keypad)
	{
		if (key == null || key.trim().length() == 0)
		{
			System.out.println("ERROR! key not provided");
			return false;
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("keySequence", key);
		
		if (keypad != null && keypad.trim().length() > 0)
			params.put("keypad", keypad);
		
		return Helper.ExecuteMethod(driver, COMMAND_PRESSKEY, params);
	}
	
	/**
	 * Sends a key event to the device, using key codes and metastates.
	 * @param keyEvent The key code key event to send to the device.
	 * @param metastate The metastate key event to send to the device.
	 * @return
	 */
	public Boolean SendKeyEvent(Integer keyEvent, Integer metastate)
	{
	
		Map<String, Object> params = new HashMap<>();
		params.put("key", keyEvent);
		
		if (metastate != null)
			params.put("metastate", metastate );
		
		return Helper.ExecuteMethod(driver, COMMAND_SENDKEY, params);
	}
	
	/**
	 * Sends a key event to the device, using key codes and metastates.
	 * @param keyEvent The key code key event to send to the device.
	 * @return
	 */
	public Boolean SendKeyEvent(Integer keyEvent)
	{
		return SendKeyEvent(keyEvent, null);
	}
	
	/**
	 * Wrapper of WebElement.Click method with additional logging
	 * @param element
	 */
  public void Click(WebElement element)
  {
    Logger.LogInfo("------------------------------------------------");
    Logger.LogInfo("Clicking on element " + element.toString());
    element.click();
  }
  
  public void Click(String xpath)
	{
		Click(By.xpath(xpath));
	}
	
	public void ClickOnId(String id)
	{
		Click(By.id(id));
	}
	
	public void ClickOnLabel(String label)
	{
	  Click(By.xpath("//*[@label='" + label + "']"));
	}
  
  public void ClickOnValue(String value)
  {
    Click(By.xpath("//*[@value='" + value + "']"));
  }
  
  public void ClickOnText(String text)
  {
    Click(By.xpath("//*[@value='" + text + "'] | //*[@label='" + text + "']"));
  }
	 
	public void Click(By by)
	{
		Click(driver.findElement(by));
	}

	public void TryClick(String xpath)
	{
		TryClick(By.xpath(xpath));
	}
	
	public void TryClickOnId(String id)
	{
		TryClick(By.id(id));
	}
	 
  public void TryClickOnLabel(String label)
  {
    TryClick(By.xpath("//*[@label='" + label + "']"));
  }
  
  public void TryClickOnValue(String value)
  {
    TryClick(By.xpath("//*[@value='" + value + "']"));
  }
 
  public void TryClickOnText(String text)
  {
    TryClick(By.xpath("//*[@label='" + text + "'] | //*[@value='" + text + "']"));
  }
	 
  public void TryClick(By by)
  {
    TryClick(driver.findElement(by));
  }

  public void TryClick(WebElement element)
  {
    try {
      Click(element);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  public void Type(String text, WebElement el)
  {
    Logger.LogInfo("Typing text " + text + " in element " + el);
    el.sendKeys(text);
  }
  
	public void Type(String text, By by)
	{
		Type(text, driver.findElement(by));
	}
	

	public void Type(String text, String xpath)
	{
		Type(text, driver.findElement(By.xpath(xpath)));
	}
	

	
	public void TryType(String text, String xpath)
	{
		try {
		  Type(text, driver.findElement(By.xpath(xpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TryType(String text, WebElement el)
	{
	  Type(text, el);
	}
	
	public void TryType(String text, By by)
	{
	  Type(text, driver.findElement(by));
	}
	
	public String TakeScreenshot()
	{
	  Logger.LogInfo("Taking screenshot as base64");
	  return driver.getScreenshotAs(OutputType.BASE64);
	}

  public File TakeScreenshotAsFile()
  {
    Logger.LogInfo("Taking screenshot as file");
    return driver.getScreenshotAs(OutputType.FILE);
  }
  
  public byte[] TakeScreenshotAsBytes()
  {
    Logger.LogInfo("Taking screenshot as file");
    return driver.getScreenshotAs(OutputType.BYTES);
  }
}
