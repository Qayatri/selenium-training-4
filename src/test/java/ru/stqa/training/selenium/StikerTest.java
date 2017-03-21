package ru.stqa.training.selenium;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StikerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void beforeTest() {
        System.out.println("Запуск браузера");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
    }

    @Test
    public void stickerTest() {
        System.out.println("Открыть главную старницу ");
        driver.get("http://localhost/litecart/en/");

        System.out.println("Проверка наличия стикеров");
        stickerCheck();
    }

    private void stickerCheck() {
        List<WebElement> ducks = driver.findElements(By.className("product"));
        System.out.println("Всего товаров : " + ducks.size());

        for (WebElement duck : ducks) {
            //количество стикеров у товаров
            int stickerSum = duck.findElements(By.className("sticker")).size();
            //System.out.println(stickerSum);
            Assert.assertTrue(stickerSum == 1);
        }

    }
}

