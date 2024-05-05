package com.automation.login;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class LogoutTest extends InventoryBaseTest {

    @Test
    public void validateLogout() {
        checkThat.hardAssert("The inventory page is not visible", inventoryPage.isPageLoaded(), is(true));

        LoginPage loginPage = inventoryPage.clickHamburgerMenu()
                .clickLogoutBtn(true);

        checkThat.softAssert("The login page is not displayed", loginPage.isPageLoaded(), is(true));
        Assert.assertEquals("The ", loginPage.getPageURL(), String.format("%s/", URL));
        Assert.assertTrue(loginPage.emptyTextInputs(), "The login labels are not empty");
    }
}
