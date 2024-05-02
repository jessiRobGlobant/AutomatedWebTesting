package com.automation.login;

import static com.automation.enums.User.LOCKED;
import static com.automation.enums.User.STANDARD;
import static org.hamcrest.Matchers.is;

import com.automation.core.login.LoginBaseTest;
import com.automation.pages.inventory.InventoryPage;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/** Login tests. */
public class LoginTest extends LoginBaseTest {

  public final String DATA_MATCH_MSG_ERROR = "Epic sadface: Username and password do not match any user in this service";

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

    checkThat.softAssert("The inventory page is not displayed", inventoryPage.isPageLoaded(), is(true));
    checkThat.softAssert( String.format("The url is not %s/inventory.html", URL),
            inventoryPage.getPageURL().equals(String.format(URL + "/inventory.html")),
            is(true));
  }

  @Test
  public void validateInvalidUsername() {
    String invalidUsername = getInvalidUsername();
    logInfo(String.format("Invalid username: %s", invalidUsername));

    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(invalidUsername)
            .setPassword(getFaker().internet().password(8, 14))
            .clickOnLoginButton(false);


    checkThat.softAssert("The login error is not displayed", loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted", loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(String.format("The error message is not '%s'", DATA_MATCH_MSG_ERROR) , loginPage.validationErrorText().equals(DATA_MATCH_MSG_ERROR), is(true));
  }

  @Test
  public void validateInvalidPassword() {
    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(STANDARD)
            .setPassword("NotAPassword")
            .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed", loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert( "Inputs are not highlighted", loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(String.format("The error message is not '%s'", DATA_MATCH_MSG_ERROR) ,loginPage.validationErrorText().equals(DATA_MATCH_MSG_ERROR),is(true));
  }

  @Test
  public void validateNotUsername() {
    String expectedErrorMsg = "Epic sadface: Username is required";
    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setPassword(getFaker().internet().password(8, 14))
            .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed", loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted", loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(String.format("The error message is not '%s'", expectedErrorMsg) , loginPage.validationErrorText(), is(true));
  }

  @Test
  public void validateNotPassword() {
    String expectedErrorMsg = "Epic sadface: Password is required";

    checkThat.hardAssert("The login page is not visible", loginPage.isPageLoaded(), is(true));

    loginPage.setUsername(STANDARD)
            .clickOnLoginButton(false);

    checkThat.softAssert("The login error is not displayed", loginPage.isValidationErrorDisplayed(), is(true));
    checkThat.softAssert("Inputs are not highlighted", loginPage.usernameAndPasswordAreHighlighted(), is(true));
    checkThat.softAssert(String.format("The error message is not '%s'", expectedErrorMsg), loginPage.validationErrorText().equals(expectedErrorMsg), is(true));
  }

  private String getInvalidUsername() {
    Set<String> acceptedUsernames = acceptedUsernames();
    String username = getFaker().internet().username();

    while (acceptedUsernames.contains(username)) {
      username = getFaker().internet().username();
    }

    return username;
  }

  private static Set<String> acceptedUsernames() {
    Object[][] users = acceptedUsers();
    return Arrays.stream(users)
            .map(user -> user[0].toString())
            .collect(Collectors.toSet());
  }

  private static Object[][] acceptedUsers() {
    String password = "secret_sauce";
    return new Object[][]{
            {"standard_user", password},
            {"locked_out_user", password},
            {"problem_user", password},
            {"performance_glitch_user", password},
            {"error_user", password},
            {"visual_user", password},
    };
  }
}
