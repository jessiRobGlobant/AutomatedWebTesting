package com.automation.login;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.login.LoginPage;
import org.testng.annotations.Test;

/**
 * Class that implements the logout test.
 */
public class LogoutTest extends InventoryBaseTest {

  @Test
  public void validateLogout() {
    checkThat.hardAssert("The inventory page is not visible",
        inventoryPage.isPageLoaded(), is(true));

    LoginPage loginPage = inventoryPage.clickHamburgerMenu()
        .clickLogoutBtn(true);

    checkThat.softAssert("The login page is not displayed",
        loginPage.isPageLoaded(), is(true));
    checkThat.softAssert(format("The url is different than %s", URL),
        loginPage.getPageUrl(), is(format("%s/", URL)));
    checkThat.softAssert("The login labels are not empty",
        loginPage.emptyTextInputs(), is(true));
  }
}
