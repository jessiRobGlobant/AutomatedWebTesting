package com.automation.core.base;

import lombok.Getter;
import net.datafaker.Faker;

/**
 * Base test class.
 */
@Getter
public abstract class BaseTest extends BaseHooks {

    private final Faker faker = new Faker();
    protected Assert checkThat = new Assert();
    protected String URL;
}
