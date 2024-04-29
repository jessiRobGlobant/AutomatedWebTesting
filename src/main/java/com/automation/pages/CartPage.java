package com.automation.pages;

import com.automation.pages.base.BasePage;
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
    private WebElement continueBtn;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productsNames;

    @FindBy(className = "cart_quantity")
    private List<WebElement> productsQuantity;

    public CartPage(WebDriver driver) {
        super(driver);
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
    protected void waitUntilPageLoad() {
        getWait().withTimeout(ofSeconds(2)).pollingEvery(ofMillis(500)).ignoring(NoSuchElementException.class)
                .until(visibilityOf(continueBtn));
    }

    @Override
    public boolean isPageLoaded() {
        waitUntilPageLoad();
        return continueBtn.isDisplayed();
    }
}
