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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.io.File;

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

    public void addProduct() throws InterruptedException {

        String name = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 8);

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.linkText("Add New Product")).click();

//      Главная
        driver.findElement(By.cssSelector("[href='#tab-general']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("[name=status]")));
        driver.findElement(By.cssSelector("[name=status][value='1']")).click();

        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(name);
        driver.findElement(By.cssSelector("[name='code']")).sendKeys(TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 6));
        if (driver.findElement(By.cssSelector("[data-name=Root]")).isSelected())
            driver.findElement(By.cssSelector("[data-name=Root]")).click();
        driver.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-3']")).click();
        WebElement quantity = driver.findElement(By.cssSelector("input[name= quantity]"));
        quantity.clear();
        quantity.sendKeys(TestUtils.generateString(new Random(), "123456789", 1));
        new Select(driver.findElement(By.cssSelector("[name=sold_out_status_id]"))).selectByValue("2");
        File file = new File("./src/test/resources/images/zjdun.jpg");
        System.out.println(file.getAbsolutePath());
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys("17.03.2017");
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys("17.04.2017");
        Thread.sleep(3000); //проверяла, что там нагенерировалось


        //Информация
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=manufacturer_id]")));
        new Select(driver.findElement(By.cssSelector("[name=manufacturer_id]"))).selectByValue("1");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys(TestUtils.generateString(new Random(), "qazwsx edcrfvtg byhnujmikolp", 25));
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(TestUtils.generateString(new Random(), "qazwsxe dcrfvtg byh nujmikolp", 25));
        Thread.sleep(3000);


        //Стоимость
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name='purchase_price']")));
        WebElement pr = driver.findElement(By.cssSelector("[name='purchase_price']"));
        pr.clear();
        pr.sendKeys(TestUtils.generateString(new Random(), "123456789", 2));
        new Select(driver.findElement(By.cssSelector("[name='purchase_price_currency_code']"))).selectByValue("USD");
        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("20.0000");
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys("18.0000");
        Thread.sleep(3000);


        //Сохранить
        driver.findElement(By.cssSelector("[name='save']")).click();

        //Проверка добавления
        driver.findElement(By.cssSelector("#doc-catalog span")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        Assert.assertTrue(driver.findElement(By.linkText(name)).isDisplayed());


    }

}
