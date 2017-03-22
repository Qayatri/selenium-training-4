package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public final class TestUtils {

    private TestUtils() {
        throw new AssertionError("No instances allowed");
    }

    public static void loginAsAdmin(WebDriver webDriver, WebDriverWait wait) {
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

}