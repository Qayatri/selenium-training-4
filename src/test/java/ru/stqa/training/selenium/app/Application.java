package ru.stqa.training.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pages.BasketPage;
import ru.stqa.training.selenium.pages.MainPage;
import ru.stqa.training.selenium.pages.ProductPage;


public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application() {
        System.out.println("Запуск браузера");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }


    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        driver = null;
    }

    public void openMainPage() {
        mainPage.open();
    }

    public void addProduct() {
        int basketSize = mainPage.basketSize();
        mainPage.clickProduct();
        productPage.selectSize();
        productPage.clickAddButton(basketSize);
        productPage.clickMainPage();
    }

    public void clearBasket() {
        mainPage.clickBasket();
        while (!basketPage.cartIsEmpty()) {
            basketPage.deleteProduct();
        }

    }
}
