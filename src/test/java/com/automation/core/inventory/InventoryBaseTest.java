package com.automation.core.inventory;

import com.automation.core.base.BaseTest;
import com.automation.enums.User;
import com.automation.pages.inventory.InventoryPage;
import java.util.Map;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Login base test class.
 */
public abstract class InventoryBaseTest extends BaseTest {

  protected InventoryPage inventoryPage;
  protected Map<String, Integer> products;

  /**
   * Login and arrive to the inventory Page.
   *
   * @param url home url of the page
   */
  @BeforeMethod
  @Parameters({"url"})
  public void navigateToLoginPage(String url) {
    URL = url;
    inventoryPage = login(User.STANDARD);
  }
}
