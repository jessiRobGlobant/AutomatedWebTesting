package com.automation.buy_products;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.cart.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class removeProducts extends InventoryBaseTest {
    private CartPage cartPage;
    private Map<String, Integer> products;

    @BeforeMethod
    public void addProducts() {
        //Add products
        products = inventoryPage.getRandomProducts(3);
        products.keySet().forEach(name -> logInfo(String.format("Product to add: %s", name)));
        cartPage = inventoryPage.addRandomProductsToCart(products)
                .goToCart();
    }

    @Test
    public void removeProductsFromCart() {
        Assert.assertTrue(cartPage.isPageLoaded(), "The cart page is not visible");

        cartPage.removeAllItems();

        Assert.assertEquals(cartPage.numberOfItems(), 0);
    }


}
