package de.ait.migrantpilot.gui.core;

import de.ait.migrantpilot.config.AppConfigApi;
import de.ait.migrantpilot.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class ApplicationManager {

    public static Logger logger = LoggerFactory.getLogger(AppConfigApi.class);

    public static SoftAssert softly = new SoftAssert();

    public WebDriver driver;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver startTest() {
        switch (browser) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }

        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get(resolveAppUrl(AppConfigApi.getProperty("app.url")));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public void stopTest() {
        driver.quit();
    }

    private String resolveAppUrl(String appUrl) {
        if (appUrl.startsWith("http://") || appUrl.startsWith("https://") || appUrl.startsWith("file:/")) {
            return appUrl;
        }

        Path path = Paths.get(appUrl);
        if (!path.isAbsolute()) {
            path = Paths.get(System.getProperty("user.dir")).resolve(path);
        }
        return path.normalize().toUri().toString();
    }
}
