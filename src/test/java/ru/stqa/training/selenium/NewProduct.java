package ru.stqa.training.selenium;

/*
Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).

Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о товаре и сохранить.

Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.

Переключение между вкладками происходит не мгновенно, поэтому после переключения можно сделать небольшую паузу (о том, как делать более правильные ожидания, будет рассказано в следующих занятиях).

Картинку с изображением товара нужно уложить в репозиторий вместе с кодом. При этом указывать в коде полный абсолютный путь к файлу плохо, на другой машине работать не будет. Надо средствами языка программирования преобразовать относительный путь в абсолютный.

После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.

Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NewProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    private static Logger log = Logger.getLogger("MyLogger");

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
        driver = null;
    }

    @Test
    public void newProduct() throws InterruptedException {
        System.out.println("Открыть старницу http://192.168.1.98/litecart/admin");
        driver.get("http://localhost/litecart/admin");

        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        log.info("Добавление нового товара");
        addProduct();

        // Выход
        TestUtils.logout(driver, wait);
    }

    public void addProduct() {

        String firstname = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 10);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.linkText("Add New Product")).click();

//        General
        driver.findElement(By.cssSelector("[href='#tab-general']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("[name=status]")));
        driver.findElement(By.cssSelector("[name=status][value='1']")).click();



    }

}
