package g.perfecto.remote.tests;

import org.testng.annotations.Test;

import g.perfecto.utilities.Application;
import g.perfecto.utilities.Capabilities;
import g.perfecto.utilities.Device;
import g.perfecto.utilities.Logger;
import g.perfecto.utilities.PerfectoDriver;
import g.perfecto.utilities.UserActions;
import g.perfecto.utilities.VisualAnalysis;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.openqa.selenium.WebElement;

public class _Appium_Sceleton {

  PerfectoDriver pd = new PerfectoDriver();
  Application app;
  UserActions ua;
  VisualAnalysis va;
  Device device;
  Exception ex;
  WebElement element;
  
  @BeforeTest
  public void beforeTest() {
    String host = getHost("");
    Capabilities caps = new Capabilities();
    caps.deviceName = "";
//    caps.deviceSessionId = "";
//    caps.enableAppiumBehavior = true;
    
    pd.init(host, caps);
    app = pd.application;
    ua = pd.userActions;
    va = pd.visualAnalysis;
    device = pd.device;
    
    pd.testStart("hristo.genev test");
  }
  
  @AfterTest
  public void afterTest() 
  {
    if (ex != null)
    {
      Logger.LogInfo("Exception timestamp:");
      ex.printStackTrace();
      pd.testStop(ex);
    }
    else
    {
      pd.testStop();      
    }
    pd.driverQuit();
  }
  
  @Test
  public void test1() {
    try
    {

      
      
      app.switchToNativeContext();
      ua.swipeUp();
    }
    catch (Exception _ex)
    {
      ex = _ex;
    }
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }
  

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

  private String getHost(String host) {
    if (host != null && host.isEmpty())
      host = this.getClass().getSimpleName().toLowerCase();
    return host;
  }
  
}