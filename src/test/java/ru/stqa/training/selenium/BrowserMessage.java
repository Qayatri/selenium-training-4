package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by nrpo-sorokina on 27.03.17.
 */
public class BrowserMessage {

    private EventFiringWebDriver driver;
    private WebDriverWait wait;


    @Before
    public void beforeTest() {

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        System.out.println("Запуск браузера");
        driver = new EventFiringWebDriver(new ChromeDriver(cap));
        driver.register(new TestUtils.MyListener());
        wait = new WebDriverWait(driver, 10);

    }

    @After
    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        driver = null;
    }

    @Test
    public void browseMessage() {

        driver.manage().window().maximize();

        driver.get("http://localhost/litecart/admin/");
        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        ducksCatalog();


        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));

        // Выход
        TestUtils.logout(driver, wait);
    }

    public void ducksCatalog() {
        List<Object[]> list = new ArrayList<>();
        getProductLinks(0).forEach(link -> list.add(new Object[]{link}));
        driver.manage().logs().get("browser");
    }
}
