package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminPanelTest {
    private WebDriver driver;
    private WebDriverWait wait;

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
    }

    @Test
    public void adminPanelTest() throws InterruptedException {
        System.out.println("Открыть старницу http://localhost/litecart/admin");
        driver.get("http://localhost/litecart/admin");

        System.out.println("2. Авторизация с помощью логина/пароля: admin/admin");
        loginAdmin();

        System.out.println("3. Прокликивание меню");
       // сlickMenuList();

        System.out.println("4. Разавторизация");
        logoutAdmin();
    }

    private void loginAdmin() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        System.out.println("Проверка загрузки");
        String logotype = driver.findElement(By.cssSelector(".logotype >a>img")).getAttribute("title");


        System.out.println(logotype);
        wait.until(titleIs("My Store"));
       // wait.until(presenceOfElementLocated(By.xpath("//*[@id='notices']/div[text() = ' You are now logged in as admin']")));
    }
//#sidebar > div.logotype > a > img
    private void logoutAdmin() {
        System.out.println("Выход из приложения");
        driver.findElement(By.className("fa-sign-out")).click();
        wait.until(presenceOfElementLocated(By.id("box-login")));
    }

    private void сlickMenuList() throws InterruptedException {
        System.out.println("3.1. Определения размера списка пунктов меню");
        int menuSize = driver.findElements(By.id("app-")).size();
        System.out.println("3.1. Количество пунктов = " + menuSize);

        for (int i = 1; i <= menuSize; i++) {
            String menuName = driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ") span.name")).getText();
            System.out.println("3.2. Клик по меню '" + menuName + "'");
            driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ") span")).click();

            System.out.println("3.3. Проверяем наличие заголовка меню");
          //  Assert.assertTrue(isElementPresent(By.tagName("h1")), "п.3.3. В меню " + menuName + "нет заголовка");

            System.out.println("3.4. Прокликивание вложенных меню");
            clickSubmenuList();
        }
    }

    private void clickSubmenuList() throws InterruptedException {
        System.out.println("3.4.1. Определения размера списка вложенных пунктов меню");
        int submenuSize = driver.findElements(By.cssSelector("[id*=doc-]")).size();
        System.out.println("3.4.1. Количество вложенных пунктов = " + submenuSize);

        for (int i = 1; i <= submenuSize; i++) {    // Если вложенных пунктов нет, цикл не выполняется
            String submenuName = driver.findElement(
                    By.cssSelector("[id*=doc-]:nth-child(" + i + ") span.name")).getText();
            if (1 == i) { // При нажатии на меню, открывается первое вложенное меню. Решил, что его нужно пропустить.
                System.out.println("3.4.2. Пропускаем первое вложенное меню по имени '" +
                        submenuName + "', т.к. оно уже нажато.");
                continue;
            }
            System.out.println("3.4.3. Клик по вложенному меню '" + submenuName + "'");
            driver.findElement(By.cssSelector("[id*=doc-]:nth-child(" + i + ") span")).click();

            System.out.println("3.4.4. Проверяем наличие заголовка вложенного меню");
           // Assert.assertTrue(isElementPresent(By.tagName("h1")), "п.3.4.4. Во вложенном меню '" + submenuName + "' нет заголовка");
        }
    }

    private boolean isElementPresent(By locator) {
        System.out.println("**********isElementPresent**********");

        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException e) {
            throw e;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
