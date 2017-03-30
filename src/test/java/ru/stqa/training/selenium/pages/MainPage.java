package ru.stqa.training.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickProduct(){
        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link")).click();
    }

    public void clickBasket(){
        driver.findElement(By.cssSelector("#cart > a.link")).click();
    }

    public Integer basketSize(){
        return Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
    }
}
