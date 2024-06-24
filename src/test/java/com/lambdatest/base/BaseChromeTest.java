package com.lambdatest.base;

import org.junit.jupiter.api.BeforeEach;

public class BaseChromeTest extends BaseTest {

    @BeforeEach
    public void setup() {
        startChromeDriver();
        this.testInfo = testInfo;
        driver.manage().window().maximize();
    }
}