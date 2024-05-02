package com.automation.pages.purchase;

import com.automation.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Checkout1Page extends BasePage {

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "first-name")
    private WebElement fristNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = "h3[data-test = 'error']")
    private WebElement validationError;

    public Checkout1Page(WebDriver driver) {
        super(driver);
    }

    public Checkout1Page clickContinue() {
        waitAndClick(continueBtn);
        return this;
    }

    public boolean isValidationErrorDisplayed() {
        return validationError.isDisplayed();
    }

    public String validationErrorText() {
        return validationError.getText();
    }

    public boolean textInputsAreHighlighted() {
        return fristNameInput.getAttribute("class").contains("error") && lastNameInput.getAttribute("class")
                .contains("error") && postalCodeInput.getAttribute("class")
                .contains("error");
    }


    @Override
    public boolean isPageLoaded() {
        getWait().withTimeout(ofSeconds(2)).pollingEvery(ofMillis(500)).ignoring(NoSuchElementException.class)
                .until(visibilityOf(continueBtn));
        return continueBtn.isDisplayed();
    }
}
