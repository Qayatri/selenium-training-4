package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BasketPage extends Page {

    public BasketPage(WebDriver webDriver) {
        super(webDriver);
    }

    private void removeButton() {
        if (isElementPresent(driver, By.cssSelector("[name='remove_cart_item']"))) ;
        driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
    }

    public void deleteProduct() {
        removeButton();
        Integer sizeProduct = driver.findElements(By.cssSelector("[name =cart_form]")).size();
        wait.until((WebDriver d) -> d.findElements(By.cssSelector("[name =cart_form]")).size() != sizeProduct ||
                !isElementPresent(driver, By.cssSelector("[name =cart_form]")));
    }


    public boolean cartIsEmpty() {
        return isElementPresent(driver, By.cssSelector("#checkout-cart-wrapper > p:nth-child(1) > em"));
    }
}
