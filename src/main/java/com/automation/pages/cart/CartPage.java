package com.automation.pages.cart;

import com.automation.pages.base.BasePage;
import com.automation.pages.purchase.Checkout1Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CartPage extends BasePage {

    @FindBy(className = "checkout_button")
    private WebElement checkOutBtn;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productsNames;

    @FindBy(className = "cart_quantity")
    private List<WebElement> productsQuantity;

    @FindBy(className = "cart_button")
    private List<WebElement> removeBtns;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage removeAllItems() {
        for (WebElement removeBtn : removeBtns) {
            waitAndClick(removeBtn);
        }
        return this;
    }

    public Checkout1Page goToCheckOut() {
        waitAndClick(checkOutBtn);
        return new Checkout1Page(getDriver());
    }

    public int numberOfItems() {
        return productsNames.size();
    }

    public int totalQuantityItems() {
        int total = 0;
        for (WebElement product : productsQuantity) {
            total += Integer.parseInt(product.getText());
        }
        return total;
    }

    public boolean areProductsNames(Map<String, Integer> products) {
        for (WebElement product : productsNames) {
            if (!products.containsKey(product.getText())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isPageLoaded() {
        getWait().withTimeout(ofSeconds(2)).pollingEvery(ofMillis(500)).ignoring(NoSuchElementException.class)
                .until(visibilityOf(checkOutBtn));
        return checkOutBtn.isDisplayed();
    }
}
