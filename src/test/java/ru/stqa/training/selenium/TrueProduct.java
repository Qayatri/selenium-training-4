package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
а) на главной странице и на странице товара совпадает текст названия товара
б) на главной странице и на странице товара совпадают цены (обычная и акционная)
в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
г) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
*/
public class TrueProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void beforeTest() {
        System.out.println("Запуск браузера");
        //System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        driver = null;
    }

    @Test
    public void countryTest() throws InterruptedException {
        System.out.println("Открыть старницу http://192.168.1.98/litecart/admin");
        driver.get("http://localhost/litecart/admin");

        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        System.out.println("Страны - проверка сортировки");
        driver.findElement(By.cssSelector("#app-:nth-child(3)")).click();


        System.out.println("Зоны - проверка сортировки, если есть в стране");


        System.out.println("Геозоны - проверка сортировки списка зон");
        driver.findElement(By.cssSelector("#app-:nth-child(6)")).click();


        // Выход
        TestUtils.logout(driver, wait);
    }

}
