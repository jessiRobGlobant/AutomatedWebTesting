package com.automation.base;

import com.automation.driver.Driver;
import com.automation.pages.LoginPage;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

@Slf4j
public abstract class BaseTest {

    private Driver driver;
    protected LoginPage loginPage;
    protected Faker faker = new Faker();

    @BeforeSuite
    public void beforeSuite() {
        log.info("Before suite");
    }

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void beforeMethod(String browser, String url) {
        log.info("Before method, loading page: {}", url);
        driver = new Driver(browser);
        loginPage = new LoginPage(driver.getDriver(), url);
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
