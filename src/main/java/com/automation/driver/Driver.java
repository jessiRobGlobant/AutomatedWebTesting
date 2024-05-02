package com.automation.driver;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/** Utility class to instantiate the driver. */
@Getter
public class Driver {

  private WebDriver driver;

  /** Constructor of the class. */
  public Driver(String browser) {
    switch (browser) {
      case "Chrome":
        driver = new ChromeDriver();
        break;
      case "Edge":
        driver = new EdgeDriver();
        break;
      case "Firefox":
        driver = new FirefoxDriver();
        break;
      case "Safari":
        driver = new SafariDriver();
        break;
      default:
        break;
    }
  }
}
