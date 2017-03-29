package ru.stqa.training.selenium.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.pages.MainPage;

/**
 * Created by nrpo-sorokina on 29.03.17.
 */
public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    public Application() {
        System.out.println("Запуск браузера");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
//        productPage = new ProductPage(driver);
//        cartPage = new CartPage(driver);
    }

//    public void beforeTest() {
//        System.out.println("Запуск браузера");
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, 10);
//        mainPage = new MainPage(driver);
//    }

    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        driver = null;
    }

    public void openMainPage(){
        mainPage.open();
    }

}
