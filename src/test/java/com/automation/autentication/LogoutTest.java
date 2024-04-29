package com.automation.autentication;

import com.automation.base.BaseTest;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    private InventoryPage inventoryPage;

    @BeforeMethod
    @Parameters({"baseUsername", "password"})
    public void login(String baseUsername, String password) {
        loginPage.isPageLoaded();
        loginPage.setUsername(baseUsername)
                .setPassword(password);
        inventoryPage = loginPage.clickOnLoginButton(true);
    }

    @Test
    @Parameters({"url"})
    public void validateLogout(String url) {
        Assert.assertTrue(inventoryPage.isPageLoaded(), "The inventory page is not visible");

        loginPage = inventoryPage.clickHamburgerMenu()
                .clickLogoutBtn(true);

        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not displayed");
        Assert.assertEquals(loginPage.getPageURL(), String.format("%s/", url));
        Assert.assertTrue(loginPage.emptyTextInputs(), "The login labels are not empty");
    }
}
