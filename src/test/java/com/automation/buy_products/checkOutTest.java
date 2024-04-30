package com.automation.buy_products;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.Checkout1Page;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

public class checkOutTest extends BaseTest {
    private Checkout1Page checkout1Page;
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
