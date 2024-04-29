package com.automation.pages;

import com.automation.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "h3[data-test = 'error']")
    private WebElement validationError;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    /**
     * Constructor of the class, for initialize the driver
     *
     * @param driver Object WebDriver
     * @param url    navigate to URL
     */
    public LoginPage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    public LoginPage setUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public <T> T clickOnLoginButton(boolean shouldLogin) {
        loginButton.click();
        return shouldLogin ? (T) new InventoryPage(getDriver()) : (T) this;
    }

    public boolean isValidationErrorDisplayed() {
        return validationError.isDisplayed();
    }

    public String validationErrorText() {
        return validationError.getText();
    }

    public boolean usernameAndPasswordAreHighlighted() {
        return usernameInput.getAttribute("class").contains("error") && passwordInput.getAttribute("class")
                .contains("error");
    }

    @Override
    protected void waitUntilPageLoad() {
        getWait().until(visibilityOf(usernameInput));
    }

    @Override
    public boolean isPageLoaded() {
        waitUntilPageLoad();
        return usernameInput.isDisplayed();
    }

}
