package com.automation.Login;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validateLoginWithWrongCredentials() {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername("standard_user").setPassword(faker.lorem().characters(5)).clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
    }
}
