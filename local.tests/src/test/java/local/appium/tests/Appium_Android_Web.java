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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Appium_Android_Web {

  
  @Test
  public void visitGoogleAndSearch() throws InterruptedException {
    
    driver.get("http://www.google.com");
    
    try {
      driver.findElement(By.id("cnskx")).click();
    } catch(Exception ex){}
    
    WebElement el = driver.findElement(By.name("q"));
    el.sendKeys("Perfecto Mobile");
    el.sendKeys(Keys.ENTER);
    
    
    
    Thread.sleep(5000);
    //driver.findElement(By.name("btnK")).click();
    
  }
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
    
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("deviceName", "Pixel 2 XL");
    caps.setCapability("udid", "emulator-5554"); 
    caps.setCapability("platformName", "Android");
    caps.setCapability("platformVersion", "10");
    caps.setCapability("automationName", "UIAutomator2");
    caps.setCapability("browserName", "Chrome");
    caps.setCapability("chromedriverExecutable", "C:\\ChromeDriver\\74\\chromedriver.exe");
    
    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
   
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
