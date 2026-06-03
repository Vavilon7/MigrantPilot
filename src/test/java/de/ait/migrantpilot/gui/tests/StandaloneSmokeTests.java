package de.ait.migrantpilot.gui.tests;

import de.ait.migrantpilot.gui.core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class StandaloneSmokeTests extends TestBase {

    @Test(description = "Check MigrantPilot standalone prototype opens without bundler errors")
    public void standalonePageOpensSuccessfullyTest() {
        WebDriverWait wait = new WebDriverWait(app.driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Unpacking...")
        ));

        Assert.assertTrue(app.driver.getTitle().contains("MigrantPilot"), "Page title should contain MigrantPilot");
        Assert.assertTrue(app.driver.findElements(By.id("__bundler_err")).isEmpty(), "Bundler error should not be visible");

        String lang = (String) ((JavascriptExecutor) app.driver)
                .executeScript("return document.documentElement.lang");
        Assert.assertEquals(lang, "ru", "Prototype language should be Russian");
    }
}
