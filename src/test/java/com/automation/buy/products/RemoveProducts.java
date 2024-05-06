package com.automation.buy.products;

import com.automation.core.purchase.RemoveProductsBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class that implements the tests to remove products from the cart.
 */
public class RemoveProducts extends RemoveProductsBaseTest {

  @Test
  public void removeProductsFromCart() {
    Assert.assertTrue(cartPage.isPageLoaded(), "The cart page is not visible");

    cartPage.removeAllItems();

    Assert.assertEquals(cartPage.numberOfItems(), 0);
  }

}
