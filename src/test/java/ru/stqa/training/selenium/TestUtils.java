package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public final class TestUtils {
    private WebDriver driver;

   // public EventFiringWebDriver driver;

    private TestUtils() {
        throw new AssertionError("No instances allowed");
    }

    public static void loginAsAdmin(WebDriver webDriver, WebDriverWait wait) {
        System.out.println("Авторизация админа");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();

        System.out.println("Проверка загрузки");
        String logotype = webDriver.findElement(By.cssSelector(".logotype >a>img")).getAttribute("title");
        wait.until(titleIs("My Store"));
    }

    public static void logout(WebDriver webDriver, WebDriverWait wait) {
        System.out.println("Выход");
        webDriver.findElement(By.className("fa-sign-out")).click();
        wait.until(presenceOfElementLocated(By.id("box-login")));
    }

    public static Optional<WebElement> findElement(WebElement element, By by) {
        try {
            return Optional.of(element.findElement(by));
        } catch (NoSuchElementException nsee) {
            return Optional.empty();
        }
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static boolean isElementPresent(WebDriver webdriver, By by){
        return webdriver.findElements(by).size() > 0;
    }



    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }


}