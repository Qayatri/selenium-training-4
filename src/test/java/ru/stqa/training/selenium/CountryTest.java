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
        driver.findElement(By.cssSelector("#app-:nth-child(3)")).click();
        countrySort(By.cssSelector("table.dataTable > tbody > tr.row > td:nth-child(5) > a"));

        System.out.println("Зоны - проверка сортировки, если есть в стране");
        zoneCountrySort();

        System.out.println("Геозоны - проверка сортировки списка зон");
        driver.findElement(By.cssSelector("#app-:nth-child(6)")).click();
        geoZoneSort();

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

    private void countrySort(By index) {
        List<WebElement> countryElements;
        countryElements = driver.findElements(index);

        List<String> countryNames = new ArrayList<>();

        for (WebElement currentElement : countryElements) {
            countryNames.add(currentElement.getText());
//            System.out.println(currentElement.getText());
        }
        assertIsAlphabeticalOrder(countryNames);

    }

    private void zoneCountrySort() {
        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable > tbody > tr.row > td:nth-child(6)"));
        int[] zoneCount = new int[countryElements.size()];
        for (int i = 0; i < countryElements.size(); i++) {
//            System.out.println(countryElements.get(i).getText());
            zoneCount[i] = Integer.parseInt(countryElements.get(i).getText());
        }
        System.out.println("**********");
        for (int i = 0; i < zoneCount.length; i++) {
            if (zoneCount[i] > 0) {
//                System.out.println(driver.findElement(By.cssSelector(".row:nth-child(" + (i+2) + ") td:nth-child(5) a")).getText());
                driver.findElement(By.cssSelector(".row:nth-child(" + (i + 2) + ") td:nth-child(5) a")).click();
                countrySort(By.cssSelector("#table-zones td:nth-child(3)"));
                driver.navigate().back();
            }

        }
    }

    private void geoZoneSort() {
        List<WebElement> countryElements = driver.findElements(By.cssSelector("table.dataTable > tbody > tr.row > td:nth-child(4)"));
        int[] zoneCount = new int[countryElements.size()];
        for (int i = 0; i < countryElements.size(); i++) {
//            System.out.println(countryElements.get(i).getText());
            zoneCount[i] = Integer.parseInt(countryElements.get(i).getText());
        }
        System.out.println("**********");
        for (int i = 0; i < zoneCount.length; i++) {
            if (zoneCount[i] > 0) {
//                System.out.println(driver.findElement(By.cssSelector(".row:nth-child(" + (i+2) + ") td:nth-child(3) a")).getText());
                driver.findElement(By.cssSelector(".row:nth-child(" + (i + 2) + ") td:nth-child(3) a")).click();
                countrySort(By.cssSelector("select[name*=zone_code] [selected]"));
                driver.navigate().back();
            }

        }

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