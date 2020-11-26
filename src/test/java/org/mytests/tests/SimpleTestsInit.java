package org.mytests.tests;

import com.epam.jdi.light.driver.get.DriverData;
import org.mytests.uiobjects.example.site.JdiTestSite;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.logger.LogLevels.STEP;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.light.elements.init.PageFactory.initElements;

public interface SimpleTestsInit {
    @BeforeSuite(alwaysRun = true)
    default void setUp() {
        logger.setLogLevel(STEP);
        // Use JDK 8, some problems mentioned with JDK 12 or higher
        // Put your custom browser options here or use default
        DriverData.defaultChromeOptions(customChromeOptions());
        // Put your custom/remote driver settings here or use default
        // WebSettings.useDriver(this::customDriver);
        initElements(JdiTestSite.class);
        logger.info("Run Tests");
    }
    @AfterSuite(alwaysRun = true)
    default void teardown() {
        killAllSeleniumDrivers();
    }

    /*default WebDriver customDriver() {
        return new ChromeDriver();
    }*/
    default ChromeOptions customChromeOptions() {
        try {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            ChromeOptions cap = new ChromeOptions();
            cap.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            cap.addArguments("--start-maximized");
            cap.setExperimentalOption("prefs", chromePrefs);
            return cap;
        } catch (Throwable ex) {
            throw exception("Failed Init Chrome Driver settings: " + ex.getMessage());
        }
    }
}

