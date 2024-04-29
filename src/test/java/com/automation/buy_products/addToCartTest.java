package com.automation.buy_products;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class addToCartTest extends BaseTest {
    private InventoryPage inventoryPage;
    private Map<String, Integer> products;

    @BeforeMethod
    @Parameters({"baseUsername", "password"})
    public void login(String baseUsername, String password) {
        loginPage.isPageLoaded();
        loginPage.setUsername(baseUsername)
                .setPassword(password);
        inventoryPage = loginPage.clickOnLoginButton(true);
    }

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
