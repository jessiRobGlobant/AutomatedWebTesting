package com.automation.core.base;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.asserts.SoftAssert;

/** Class for handle assertions. */
public class Assert {

  protected static SoftAssert softAssert;
  protected static ThreadLocal<SoftAssert> threadLocalSoftAssert =
      ThreadLocal.withInitial(SoftAssert::new);

  public Assert() {
    softAssert = getSoftAssert();
  }

  protected static synchronized SoftAssert getSoftAssert() {
    return threadLocalSoftAssert.get();
  }

  /** Method for call a hard assertion. */
  public <T> void hardAssert(String description, T actual, Matcher<T> expected) {
    String step = String.format(description, expected.toString());

    try {
      MatcherAssert.assertThat(step, actual, expected);
    } catch (AssertionError var6) {
      throw new AssertionError(var6.getMessage());
    }
  }

  /** Method for call a soft assertion. */
  public synchronized <T> void softAssert(String description, T actual, Matcher<T> expected) {
    String step = String.format(description, expected.toString());

    try {
      MatcherAssert.assertThat(step, actual, expected);
    } catch (AssertionError var7) {
      softAssert.fail(var7.getMessage());
    }
  }
}
