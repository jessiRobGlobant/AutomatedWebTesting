package com.automation.buy_products;

import com.automation.core.inventory.InventoryBaseTest;
import com.automation.pages.cart.CartPage;
import com.automation.pages.purchase.Checkout1Page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class checkOutTest extends InventoryBaseTest {
    private Checkout1Page checkout1Page;
    private Map<String, Integer> products;

    @BeforeMethod
    public void addProducts() {
        //Add products
        products = inventoryPage.getRandomProducts(3);
        products.keySet().forEach(name -> logInfo(String.format("Product to add: %s", name)));

        //Go to check out
        CartPage cartPage = inventoryPage.goToCart();
        checkout1Page = cartPage.goToCheckOut();
    }

    @Test
    public void validateEmptyInputs() {
        Assert.assertTrue(checkout1Page.isPageLoaded(), "The check out step one page is not visible");

        checkout1Page.clickContinue();

        Assert.assertTrue(checkout1Page.isValidationErrorDisplayed(), "The data error is not displayed");
        Assert.assertTrue(checkout1Page.textInputsAreHighlighted(), "Inputs are not highlighted");
        Assert.assertEquals(checkout1Page.validationErrorText(), "Error: First Name is required");
    }


}
