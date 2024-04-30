package com.automation.buy_products;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class removeProducts extends BaseTest {
    private CartPage cartPage;
    private Map<String, Integer> products;

    @BeforeMethod
    @Parameters({"baseUsername", "password"})
    public void login(String baseUsername, String password) {
        // Login
        loginPage.isPageLoaded();
        loginPage.setUsername(baseUsername)
                .setPassword(password);
        InventoryPage inventoryPage = loginPage.clickOnLoginButton(true);

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
