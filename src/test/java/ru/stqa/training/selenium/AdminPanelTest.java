package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AdminPanelTest {
    private WebDriver driver;

    @Before
    public void beforeClass() {
        System.out.println("Запуск браузера");
        driver = new ChromeDriver();
    }

    @Test
    public void task1Test() {
        System.out.println("Открыть старницу https://www.google.ru");
        driver.get("https://www.google.ru");
    }

    @After
    public void afterClass() {
        System.out.print("Завершение работы браузера");
        driver.quit();
    }

}
