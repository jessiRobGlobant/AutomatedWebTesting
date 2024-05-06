package com.automation.core.login;

import com.automation.core.base.BaseTest;
import com.automation.enums.User;
import com.automation.pages.login.LoginPage;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Login base test class.
 */
public abstract class LoginBaseTest extends BaseTest {

  protected LoginPage loginPage;

  @BeforeMethod
  @Parameters({"url"})
  public void navigateToLoginPage(String url) {
    loginPage = new LoginPage(driver.getDriver(), url);
    URL = url;
  }

  protected String getInvalidUsername() {
    Set<String> acceptedUsernames = acceptedUsernames();
    String username = getFaker().internet().username();

    while (acceptedUsernames.contains(username)) {
      username = getFaker().internet().username();
    }

    return username;
  }

  protected static Set<String> acceptedUsernames() {
    return Arrays.stream(User.values())
        .map(User::getUsername)
        .collect(Collectors.toSet());
  }
}
