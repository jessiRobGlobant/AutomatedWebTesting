package com.automation.core.purchase;

import static java.lang.String.format;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.cart.CartPage;

/**
 * Class that implements the common methods for the test that involve the purchase of products.
 */
public class PurchaseBaseTest extends InventoryBaseTest {

  /**
   * Add random products to the cart.
   */
  public CartPage addProducts() {
    //Add products
    products = inventoryPage.getRandomProducts(3);
    products.keySet().forEach(name -> logInfo(format("Product to add: %s", name)));
    return inventoryPage.addRandomProductsToCart(products)
        .goToCart();
  }
}
