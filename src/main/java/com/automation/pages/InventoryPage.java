package com.automation.pages;

import com.automation.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class InventoryPage extends BasePage {

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenu;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartButton;

    /**
     * Constructor of the class, for initialize the driver
     *
     * @param driver Object WebDriver
     */
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitUntilPageLoad() {
        getWait().withTimeout(ofSeconds(2)).pollingEvery(ofMillis(500)).ignoring(NoSuchElementException.class)
                .until(visibilityOf(hamburgerMenu));
    }

    @Override
    public boolean isPageLoaded() {
        waitUntilPageLoad();
        return hamburgerMenu.isDisplayed();
    }
}
