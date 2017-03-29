package ru.stqa.training.selenium.Tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.TestUtils;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewWindowTest {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;
    private String mainWindow;
    private Set<String> oldWindows;
    private String newWindow;


    private static Logger log = Logger.getLogger("MyLogger");

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
    public void CheckOpenWindows() {

        driver.manage().window().maximize();

        driver.get("http://localhost/litecart/admin/");
        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector(".button")).click();

        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));


        List<WebElement> ExternalLinks = driver.findElements(By.cssSelector(".fa-external-link"));
        for (int i = 0; i < ExternalLinks.size(); i++) {
            mainWindow = driver.getWindowHandle();
            System.out.println("ID: " + mainWindow);
            oldWindows = driver.getWindowHandles();
            ExternalLinks.get(i).click();
            newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            System.out.println("Открылось окно: " + driver.getTitle());
            driver.close();
            driver.switchTo().window(mainWindow);
        }

        // Выход
        TestUtils.logout(driver, wait);

    }

    private ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver input) {
                Set<String> handles = input.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
