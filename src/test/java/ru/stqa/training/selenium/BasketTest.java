package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nrpo-sorokina on 28.03.17.
 */
public class BasketTest {

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
    public void ProductBasket (){

        driver.get("http://localhost/litecart/en/");

        System.out.println("Добавляем продукты в корзину");
        for (int i=0; i<3; i++)
            addProduct();

        driver.findElement(By.cssSelector("#cart > a.link")).click();

        System.out.println("Удаляем продукты из корзины");
        while (!isElementPresent(driver, By.cssSelector("#checkout-cart-wrapper > p:nth-child(1) > em")))
        {
            System.out.println("--колличество продуктов в корзине: "+driver.findElements(By.cssSelector("[name='remove_cart_item']")).size());

            if (isElementPresent(driver,By.cssSelector("[name='remove_cart_item']")));
            driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();

            Integer sizeProduct = driver.findElements(By.cssSelector("[name =cart_form]")).size();

            wait.until((WebDriver d) -> d.findElements(By.cssSelector("[name =cart_form]")).size() != sizeProduct ||
                    !isElementPresent(driver, By.cssSelector("[name =cart_form]")));
        }
    }


    public void addProduct (){

        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link")).click();

        Integer count = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        if(isElementPresent(driver, By.cssSelector("[name='options[Size]']"))) {
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByIndex(1);
            System.out.println("размер продукта выбран: "+ driver.findElement(By.cssSelector("[name='options[Size]']")).getAttribute("value"));
        }
        driver.findElement(By.cssSelector("[name='add_cart_product']")).click();
        wait.until((WebDriver d)-> count != Integer.parseInt(d.findElement(By.cssSelector("span.quantity")).getText()));
        System.out.println("--Колличество продуктов в корзине: "+ driver.findElement(By.cssSelector("span.quantity")).getText());

        driver.findElement(By.cssSelector("#site-menu > ul > li.general-0 > a")).click();
    }


    public boolean isElementPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }
}
