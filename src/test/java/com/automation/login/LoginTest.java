package com.automation.login;

import static com.automation.enums.ErrorMsg.DATA_MATCH;
import static com.automation.enums.ErrorMsg.REQUIRED_PASSWORD;
import static com.automation.enums.ErrorMsg.REQUIRED_USERNAME;
import static com.automation.enums.User.LOCKED;
import static com.automation.enums.User.STANDARD;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;

import com.automation.core.login.LoginBaseTest;
import com.automation.pages.inventory.InventoryPage;
import org.testng.annotations.Test;


/**
 * Login tests.
 */
public class LoginTest extends LoginBaseTest {

  @Test
  public void validateLoginWithWrongCredentials() {
    checkThat.hardAssert("The login page is visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(LOCKED).setPassword(LOCKED).clickOnLoginButton(false);

    checkThat.softAssert(
        "The login error is displayed", loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert(
        "Inputs are highlighted", loginPage.usernameAndPasswordAreHighlighted(), is(true));
  }

  @Test
  public void validateLoginSuccessful() {
    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(STANDARD)
        .setPassword(STANDARD);
    InventoryPage inventoryPage = loginPage.clickOnLoginButton(true);

    checkThat.softAssert("The inventory page is not displayed",
        inventoryPage.isPageLoaded(), is(true));
    checkThat.softAssert(format("The url is not %s/inventory.html", URL),
        inventoryPage.getPageUrl().equals(format(URL + "/inventory.html")),
        is(true));
  }

  @Test
  public void validateInvalidUsername() {
    String invalidUsername = getInvalidUsername();
    logInfo(format("Invalid username: %s", invalidUsername));

    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(invalidUsername)
        .setPassword(getFaker().internet().password(8, 14))
        .clickOnLoginButton(false);


    checkThat.softAssert("The login error is not displayed",
        loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted",
        loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(format("The error message is not '%s'",
        DATA_MATCH.getMsg()), loginPage.validationErrorText(), is(DATA_MATCH.getMsg()));
  }

  @Test
  public void validateInvalidPassword() {
    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(STANDARD)
        .setPassword("NotAPassword")
        .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed",
        loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted",
        loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(format("The error message is not '%s'",
        DATA_MATCH.getMsg()), loginPage.validationErrorText(), is(DATA_MATCH.getMsg()));
  }

  @Test
  public void validateNotUsername() {
    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setPassword(getFaker().internet().password(8, 14))
        .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed",
        loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted",
        loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(format("The error message is not '%s'",
            REQUIRED_USERNAME.getMsg()), loginPage.validationErrorText(),
        is(REQUIRED_USERNAME.getMsg()));
  }

  @Test
  public void validateNotPassword() {

    checkThat.hardAssert("The login page is not visible",
        loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(STANDARD)
        .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed",
        loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted",
        loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(format("The error message is not '%s'",
            REQUIRED_PASSWORD.getMsg()), loginPage.validationErrorText(),
        is(REQUIRED_PASSWORD.getMsg()));
  }

}
