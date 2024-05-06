package com.automation.core.purchase;

import com.automation.pages.cart.CartPage;
import com.automation.pages.purchase.Checkout1Page;
import org.testng.annotations.BeforeMethod;

/**
 * Class that implements the base to the CheckOut Test.
 */
public class CheckOut1BaseTest extends PurchaseBaseTest {

  protected Checkout1Page checkout1Page;

  /**
   * Add products and go to checkout page.
   */
  @BeforeMethod
  public void goToCheckOut() {
    //Add products
    CartPage cartPage = addProducts();
    checkout1Page = cartPage.goToCheckOut();
  }

}
