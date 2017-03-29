package ru.stqa.training.selenium.pages;


import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

    public MainPage(WebDriver webDriver){
        super(webDriver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

}
