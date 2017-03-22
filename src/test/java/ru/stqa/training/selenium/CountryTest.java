package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class CountryTest {
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
    public void countryTest() throws InterruptedException {
        System.out.println("Открыть старницу http://192.168.1.98/litecart/admin");
        driver.get("http://localhost/litecart/admin");

        // Вход
        TestUtils.loginAsAdmin(driver, wait);

        System.out.println("Страны - проверка сортировки");
        countrySort();

        System.out.println("Зоны - проверка сортировки, если есть в стране");


        // Выход
        TestUtils.logout(driver, wait);
}

    private void assertIsAlphabeticalOrder(List<String> strings) {
        if (strings.size() > 1) {
            for (int i = 0; i < strings.size() - 2; i++) {
                String country1 = strings.get(i);
                String country2 = strings.get(i + 1);
                Assert.assertTrue("Country 1: " + country1 + ", country 2: " + country2,
                        country1.compareTo(country2) < 0);
            }
        }
    }

    private void countrySort(){
        driver.findElement(By.cssSelector("#app-:nth-child(3)")).click();
        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable > tbody > tr.row > td:nth-child(5) > a"));

        List<String> countryNames = new ArrayList<>();
        for (WebElement currentElement : countryElements) {
            countryNames.add(currentElement.getText());
        }

        assertIsAlphabeticalOrder(countryNames);

    }

}

//        Collections.sort(countryNames, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });

//
//        List<String> countryNames2 = countryElements.stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());