package com.automation.core.login;

import com.automation.core.base.BaseTest;
import com.automation.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Login base test class.
 */
public abstract class LoginBaseTest extends BaseTest {

    protected LoginPage loginPage;
    protected String URL;

    @BeforeMethod
    @Parameters({"url"})
    public void navigateToLoginPage(String url) {
        loginPage = new LoginPage(driver.getDriver(), url);
        URL = url;
    }
}
