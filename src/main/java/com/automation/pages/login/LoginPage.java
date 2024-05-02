package com.automation.pages.login;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import com.automation.enums.User;
import com.automation.pages.base.BasePage;
import com.automation.pages.inventory.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
   * Constructor of the class, for initialize the driver.
   *
   * @param driver Object WebDriver
   * @param url navigate to URL
   */
  public LoginPage(WebDriver driver, String url) {
    super(driver);
    driver.get(url);
  }

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage setUsername(User user) {
    usernameInput.sendKeys(user.getUsername());
    return this;
  }

  public LoginPage setUsername(String username) {
    usernameInput.sendKeys(username);
    return this;
  }

  public LoginPage setPassword(User user) {
    passwordInput.sendKeys(user.getPassword());
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
    return usernameInput.getAttribute("class").contains("error")
        && passwordInput.getAttribute("class").contains("error");
  }

  public boolean emptyTextInputs() {
    return usernameInput.getAttribute("value").isEmpty() && passwordInput.getAttribute("value").isEmpty();
  }

  @Override
  public boolean isPageLoaded() {
    getWait().until(visibilityOf(usernameInput));
    return usernameInput.isDisplayed();
  }
}
