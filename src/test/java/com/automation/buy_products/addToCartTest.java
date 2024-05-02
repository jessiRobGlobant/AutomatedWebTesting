package com.automation.buy_products;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.cart.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class addToCartTest extends InventoryBaseTest {
    private Map<String, Integer> products;


    @Test
    public void addRandomProductsToCart() {
        Assert.assertTrue(inventoryPage.isPageLoaded(), "The inventory page is not visible");

        products = inventoryPage.getRandomProducts(3);
        products.keySet().forEach(name -> logInfo(String.format("Product to add: %s", name)));

        CartPage cartPage = inventoryPage.addRandomProductsToCart(products)
                .goToCart();

        Assert.assertEquals(cartPage.numberOfItems(), 3);
        Assert.assertEquals(cartPage.totalQuantityItems(), 3);
        Assert.assertTrue(cartPage.areProductsNames(products), "The product names are different");
    }


}
