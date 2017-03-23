package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/*
Задание 11. Сделайте сценарий регистрации пользователя

1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
3) повторный вход в только что созданную учётную запись,
4) и ещё раз выход.

В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.
 */

public class RegisterUser {
    private WebDriver driver;
    private WebDriverWait wait;

    private String firstname;
    private String lastname;
    private String address;
    private String postcode;
    private String city;
    private String countryValue;
    private String email;
    private String phone;
    private String password;


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
    public void registerUser() {
        System.out.println("Вход");
        driver.get("http://localhost/litecart/");
        System.out.println("Регистрация нового пользователя");
        checkIn();
        System.out.println("Вход/выход");
        logInLogOut();

    }

    private void generateData() {
        firstname = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 6);
        lastname = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 8);
        address = TestUtils.generateString(new Random(), "qa zwsxed crfv tgby hnuj mikolp", 25);
        postcode = TestUtils.generateString(new Random(), "1234567890", 5);
        city = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 6);
        email = firstname + "@gmail.com";
        password = TestUtils.generateString(new Random(), "qazwsxedcrfvtgbyhnujmikolp", 8);
        phone = "+1" + TestUtils.generateString(new Random(), "1234567890", 9);
    }

    public void checkIn() {
        generateData();
        countryValue = "US";
        driver.findElement(By.linkText("New customers click here")).click();
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstname);
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastname);
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys(address);
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys(postcode);
        driver.findElement(By.cssSelector("[name=city]")).sendKeys(city);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys(phone);
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);

        new Select(driver.findElement(By.cssSelector("[name=country_code]"))).selectByValue(countryValue);

        wait.until(elementToBeClickable(By.cssSelector("select[name=zone_code]")));
        Select selectState = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        selectState.selectByIndex(new Random().nextInt(selectState.getOptions().size()));


        if (driver.findElement(By.cssSelector("[name=newsletter]")).isSelected())
            driver.findElement(By.cssSelector("[name=newsletter]")).click();

        driver.findElement(By.cssSelector("[name=create_account]")).click();
        wait.until(titleIs("Online Store | My Store"));
        driver.findElement(By.linkText("Logout")).click();
    }

    public void logInLogOut() {

        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=login]")).click();

        driver.findElement(By.linkText("Logout")).click();

    }

}
