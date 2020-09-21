package local.appium.tests;

import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Appium_Android_Native {
  
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
    
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("deviceName", "Pixel 3 XL");
    caps.setCapability("udid", "emulator-5554"); 
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "11");
    caps.setCapability("automationName", "UIAutomator2");
    caps.setCapability("app", "C:\\Users\\hristo\\Downloads\\SApp-226.030automation_22.apk");
    caps.setCapability("appPackage", "com.salesforce.chatter");
    caps.setCapability("appActivity", "com.salesforce.chatter.Chatter");
//    caps.setCapability("autoLaunch", false);
    
    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
   
  }
  
  @Test
  public void openInstalledApp() throws InterruptedException {
    
    
    try {
      WebDriverWait wait = new WebDriverWait(driver, 15000);
      WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.salesforce.chatter:id/mi_accept")));
      element.click();
//    driver.findElement(By.id("com.salesforce.chatter:id/mi_accept")).click();
//      Thread.sleep(3000);

//      element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
//      element.sendKeys("hgenev@perforce.com");
//      driver.findElement(By.id("password")).sendKeys("123456");
//      driver.findElement(By.id("Login")).click();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println(driver.getPageSource());
    }
    
    Thread.sleep(3000);
  }


  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeText() {
    System.out.println("Starting test!");
  }
  

  @AfterTest
  public void afterText() {
    System.out.println("Finished test!");
  }
  
  AndroidDriver<MobileElement> driver;
}
