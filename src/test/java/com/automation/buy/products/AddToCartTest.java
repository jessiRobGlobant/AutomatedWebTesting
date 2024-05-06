package com.automation.buy.products;

import static org.hamcrest.Matchers.is;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.cart.CartPage;
import org.testng.annotations.Test;

/**
 * Class that implements the test of adding products to the cart.
 */
public class AddToCartTest extends InventoryBaseTest {

  @Test
  public void addRandomProductsToCart() {
    checkThat.hardAssert("The inventory page is not visible",
        inventoryPage.isPageLoaded(), is(true));

    products = inventoryPage.getRandomProducts(3);
    products.keySet().forEach(name -> logInfo(String.format("Product to add: %s", name)));

    CartPage cartPage = inventoryPage.addRandomProductsToCart(products)
        .goToCart();

    checkThat.softAssert("The total number of product types added to the cart is not 3",
        cartPage.numberOfItems(), is(3));
    checkThat.softAssert("The total number of items added to the cart is not 3",
        cartPage.totalQuantityItems(), is(3));
    checkThat.softAssert("The products names are not the expected",
        cartPage.areProductsNames(products), is(true));
  }


}
