package com.automation.core.base;

import com.automation.driver.Driver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/** Base hooks class. */
@Slf4j
public class BaseHooks {

  protected Driver driver;

  @BeforeSuite
  public void beforeSuite() {
    log.info("Before suite");
  }

  /** Before method hook for initiate the driver. */
  @BeforeMethod
  @Parameters({"browser"})
  public void beforeMethod(String browser) {
    log.info("Before method, initiating the driver");
    driver = new Driver(browser);
  }

  @AfterMethod
  public void afterMethod() {
    log.info("After method, disposing driver");
    driver.getDriver().quit();
  }

  protected void logInfo(String msg) {
    log.info(msg);
  }
}
