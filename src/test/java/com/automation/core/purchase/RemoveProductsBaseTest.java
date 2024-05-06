package com.automation.core.purchase;

import com.automation.pages.cart.CartPage;
import org.testng.annotations.BeforeMethod;

/**
 * Class that implements the Base from Remove Products Base Test.
 */
public class RemoveProductsBaseTest extends PurchaseBaseTest {
  protected CartPage cartPage;

  /**
   * Add random products to the cart.
   */
  @BeforeMethod
  public void addProductsToCart() {
    //Add products
    cartPage = addProducts();
  }
}
