package ru.stqa.training.selenium.Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.TestUtils;

import java.util.List;
import java.util.logging.Level;


public class BrowserMessage {

    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    public LogEntries log;


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

        driver.get("http://192.168.1.98/litecart/admin/");
        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        driver.get("http://192.168.1.98/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        ducksCatalog();

        // Выход
        TestUtils.logout(driver, wait);
    }

    public void ducksCatalog() {
        driver.findElement(By.linkText("Subcategory")).click();
        List<WebElement> duckList = driver.findElements(By.cssSelector("#content > form > table > tbody  td:nth-child(3) > a"));
        System.out.println("Товаров в подкатегории: " + duckList.size());
        for (int i= 0 ; i <duckList.size() ; i++ ){
            driver.findElements(By.cssSelector("#content > form > table > tbody  td:nth-child(3) > a")).get(i).click();
            System.out.println("Товар: "+(i+1));
            wait.until(driver1 -> driver1.findElement(By.linkText("General")).isDisplayed());
            log = driver.manage().logs().get("browser");

            Assert.assertTrue("Сообщения лога:\n" + printLog(log),
                    log.getAll().isEmpty());

            driver.navigate().back();
        }
    }

    private String printLog(LogEntries log) {
        String logStr = "";
        for(LogEntry entry : log) {
            logStr += entry.toString() + "\n";
        }
        return logStr;
    }

}
