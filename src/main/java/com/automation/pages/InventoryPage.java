package com.automation.pages;

import com.automation.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class InventoryPage extends BasePage {

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenu;

    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productsNames;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addProductsBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartBtn;

    /**
     * Constructor of the class, for initialize the driver
     *
     * @param driver Object WebDriver
     */
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage clickHamburgerMenu() {
        waitAndClick(hamburgerMenu);
        return this;
    }

    public Map<String, Integer> getRandomProducts(int numOfProducts) {
        Random random = new Random();
        Map<String, Integer> products = new HashMap<>();
        Set<Integer> indexes = new HashSet<>();

        int randomIndex;

        while (products.size() < numOfProducts) {
            randomIndex = random.nextInt(productsNames.size());
            if (!indexes.contains(randomIndex)) {
                products.put(getProductNameByIndex(randomIndex), randomIndex);
                indexes.add(randomIndex);
            }
        }
        return products;
    }

    public InventoryPage addRandomProductsToCart(Map<String, Integer> products) {
        products.forEach((name, index) -> waitAndClick(addProductsBtn.get(index)));
        return this;
    }

    private String getProductNameByIndex(int index) {
        return productsNames.get(index).getText();
    }

    public <T> T clickLogoutBtn(boolean shouldLogout) {
        logoutBtn.click();
        return shouldLogout ? (T) new LoginPage(getDriver()) : (T) this;
    }

    public CartPage goToCart() {
        waitAndClick(cartBtn);
        return new CartPage(getDriver());
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
