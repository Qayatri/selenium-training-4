package ru.stqa.training.selenium.Tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void beforeClass() {
        System.out.println("Запуск браузера");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task1Test() throws InterruptedException {
        System.out.println("Открыть старницу https://www.google.ru");
       // driver.get("https://www.google.ru");

        driver.get("http://localhost/litecart/admin");
        System.out.println("OK");
        Thread.sleep(3000);
        //wait.until()
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        /*
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Catalog")).click();

        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.linkText("DYMO Print")).click();*/
    }

    @After
    public void afterClass() {
        System.out.print("Завершение работы браузера");
        driver.quit();
    }
}
