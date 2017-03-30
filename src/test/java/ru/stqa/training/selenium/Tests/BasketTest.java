package ru.stqa.training.selenium.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stqa.training.selenium.app.Application;

//Версия для задания 19
public class BasketTest {

    private Application application;

    @Before
    public void beforeTest() {
        application = new Application();
    }

    @After
    public void afterTest() {
        application.quit();
        application = null;
    }

    @Test
    public void ProductBasket() {
        application.openMainPage();
        for (int i = 0; i < 3; i++) {
            application.addProduct();
        }
        application.clearBasket();
    }
}

/*
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
    public void quit() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        driver = null;
    }

    @Test
    public void ProductBasket() {

        driver.get("http://localhost/litecart/"); //с главной
//        addProduct(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link"));

//        driver.get("http://localhost/litecart/en/rubber-ducks-c-1/"); //из каталога
//        addProduct(By.cssSelector("#box-category > div > ul.listing-wrapper.products > li:nth-child(2) > a.link"));

        System.out.println("Добавляем продукты в корзину");
        for (int i = 0; i < 3; i++) {
            addProduct(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link"));
        }

        driver.findElement(By.cssSelector("#cart > a.link")).click();
        System.out.println("Удаляем продукты из корзины");
        deleteProduct();

    }

    public void addProduct(By by) {

        driver.findElement(by).click();

        Integer count = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        if (TestUtils.isElementPresent(driver, By.cssSelector("[name='options[Size]']"))) {
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByIndex(1);
        }
        driver.findElement(By.cssSelector("[name='add_cart_product']")).click();
        wait.until((WebDriver d) -> count != Integer.parseInt(d.findElement(By.cssSelector("span.quantity")).getText()));

        driver.findElement(By.cssSelector("#site-menu > ul > li.general-0 > a")).click();

    }

}*/
