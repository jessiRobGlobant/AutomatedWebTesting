package com.automation.buy.products;

import static org.hamcrest.Matchers.is;

import com.automation.core.purchase.CheckOut1BaseTest;
import org.testng.annotations.Test;

/**
 * Class that implements the check out test.
 */
public class CheckOutTest extends CheckOut1BaseTest {

  @Test
  public void validateEmptyInputs() {
    checkThat.hardAssert("The check out step one page is not visible",
        checkout1Page.isPageLoaded(), is(true));

    checkout1Page.clickContinue();

    checkThat.softAssert("The data error is not displayed",
        checkout1Page.isValidationErrorDisplayed(), is(true)
    );
    checkThat.softAssert("Inputs are not highlighted",
        checkout1Page.textInputsAreHighlighted(), is(true));
    checkThat.softAssert("Error: First Name is required",
        checkout1Page.validationErrorText(), is(true));
  }

}
