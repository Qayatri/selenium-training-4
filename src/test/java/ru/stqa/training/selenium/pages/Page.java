package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nrpo-sorokina on 29.03.17.
 */
public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver webDriver) {
        this.driver = driver;
        wait = new WebDriverWait(webDriver, 10);
    }

    boolean isElementPresent(WebDriver webDriver, By by) {
        return webDriver.findElements(by).size() > 0;
    }
}
