package com.automation.autentication;

import com.automation.base.BaseTest;
import com.automation.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginTest extends BaseTest {

    public final String DATA_MATCH_MSG_ERROR = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void validateLoginWithWrongCredentials() {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername("standard_user")
                .setPassword(faker.lorem().characters(5))
                .clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
    }

    @Test
    @Parameters({"url", "baseUsername", "password"})
    public void validateLoginSuccessful(String url, String baseUsername, String password) {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername(baseUsername)
                .setPassword(password);
        InventoryPage inventoryPage = loginPage.clickOnLoginButton(true);

        Assert.assertTrue(inventoryPage.isPageLoaded(), "The inventory page is not displayed");
        Assert.assertEquals(inventoryPage.getPageURL(), String.format(url + "/inventory.html"));
    }

    @Test
    public void validateInvalidUsername() {
        String invalidUsername = getInvalidUsername();
        logInfo(String.format("Invalid username: %s", invalidUsername));

        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername(invalidUsername)
                .setPassword(faker.internet().password(8, 14))
                .clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
        Assert.assertEquals(loginPage.validationErrorText(), DATA_MATCH_MSG_ERROR);
    }

    @Test
    @Parameters({"baseUsername"})
    public void validateInvalidPassword(String baseUsername) {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername(baseUsername)
                .setPassword("NotAPassword")
                .clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
        Assert.assertEquals(loginPage.validationErrorText(), DATA_MATCH_MSG_ERROR);
    }

    @Test
    public void validateNotUsername() {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setPassword(faker.internet().password(8, 14))
                .clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
        Assert.assertEquals(loginPage.validationErrorText(), "Epic sadface: Username is required");
    }

    @Test
    @Parameters({"baseUsername"})
    public void validateNotPassword(String baseUsername) {
        Assert.assertTrue(loginPage.isPageLoaded(), "The login page is not visible");

        loginPage.setUsername(baseUsername)
                .clickOnLoginButton(false);

        Assert.assertTrue(loginPage.isValidationErrorDisplayed(), "The login error is not displayed");
        Assert.assertTrue(loginPage.usernameAndPasswordAreHighlighted(), "Inputs are not highlighted");
        Assert.assertEquals(loginPage.validationErrorText(), "Epic sadface: Password is required");
    }

    private String getInvalidUsername() {
        Set<String> acceptedUsernames = acceptedUsernames();
        String username = faker.name().username();

        while (acceptedUsernames.contains(username)) {
            username = faker.name().username();
        }

        return username;
    }

    private static Set<String> acceptedUsernames() {
        Object[][] users = acceptedUsers();
        return Arrays.stream(users)
                .map(user -> user[0].toString())
                .collect(Collectors.toSet());
    }

    private static Object[][] acceptedUsers() {
        String password = "secret_sauce";
        return new Object[][]{
                {"standard_user", password},
                {"locked_out_user", password},
                {"problem_user", password},
                {"performance_glitch_user", password},
                {"error_user", password},
                {"visual_user", password},
        };
    }
}
