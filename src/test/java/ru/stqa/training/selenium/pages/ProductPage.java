package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectSize() {
        if (isElementPresent(driver, By.cssSelector("[name='options[Size]']"))) {
            new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByIndex(1);
        }
    }

    public void clickAddButton() {
        int count = getBasketSize();
        driver.findElement(By.cssSelector("[name='add_cart_product']")).click();
        wait.until((WebDriver d) -> getBasketSize() == count + 1);
    }

    private int getBasketSize() {
        return Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
    }

    public void clickMainPage() {
        driver.findElement(By.cssSelector("#site-menu > ul > li.general-0 > a")).click();
    }

}
