package com.automation.core.inventory;

import com.automation.core.base.BaseTest;
import com.automation.enums.User;
import com.automation.pages.inventory.InventoryPage;
import com.automation.pages.login.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Login base test class.
 */
public abstract class InventoryBaseTest extends BaseTest {

    protected InventoryPage inventoryPage;

    @BeforeMethod
    @Parameters({"url"})
    public void navigateToLoginPage(String url) {
        LoginPage loginPage = new LoginPage(driver.getDriver(), url);
        URL = url;
        loginPage.isPageLoaded();
        loginPage.setUsername(User.STANDARD)
                .setPassword(User.STANDARD);
        inventoryPage = loginPage.clickOnLoginButton(true);
    }
}
