package com.automation.core.base;

import com.automation.enums.User;
import com.automation.pages.inventory.InventoryPage;
import com.automation.pages.login.LoginPage;
import lombok.Getter;
import net.datafaker.Faker;

/**
 * Base test class.
 */
@Getter
public abstract class BaseTest extends BaseHooks {

  private final Faker faker = new Faker();
  protected Assert checkThat = new Assert();
  protected static String URL;

  protected InventoryPage login(User user) {
    LoginPage loginPage = new LoginPage(driver.getDriver(), URL);
    loginPage.isPageLoaded();
    loginPage.setUsername(user)
        .setPassword(user);
    return loginPage.clickOnLoginButton(true);
  }
}
