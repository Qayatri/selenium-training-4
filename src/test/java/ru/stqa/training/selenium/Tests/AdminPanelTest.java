package ru.stqa.training.selenium.Tests;

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
import ru.stqa.training.selenium.TestUtils;

import java.util.List;


public class AdminPanelTest {
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
    public void adminPanelTest() throws InterruptedException {
        System.out.println("Открыть старницу http://192.168.1.98/litecart/admin");
        driver.get("http://localhost/litecart/admin");

        //Вход
        TestUtils.loginAsAdmin(driver, wait);

        System.out.println("Выполнение теста");
        clickMenuList();

        //Выход
        TestUtils.logout(driver, wait);
    }



    private void clickMenuList() throws InterruptedException {
        System.out.println("Размер списка пунктов меню");
        List<WebElement> topLevelMenuItems = driver.findElements(By.id("app-"));
        System.out.println("Количество пунктов = " + topLevelMenuItems.size());

        for (int i = 1; i <= topLevelMenuItems.size(); i++) {
            WebElement currentMenuItem = driver.findElement(By.cssSelector("#app-:nth-child(" + i + ")"));
            String menuItemName = currentMenuItem.getText();
            System.out.println("Клик по меню '" + menuItemName + "'");
            currentMenuItem.click();
            Assert.assertTrue("В меню " + menuItemName + "нет заголовка",
                    isHeaderPresent());
            System.out.println("Вложенное меню");
            clickSubmenuList();
        }
    }

    private void clickSubmenuList() throws InterruptedException {
        System.out.println("Размера списка вложенных пунктов меню");
        List<WebElement> subMenuItems = driver.findElements(By.cssSelector("[id^=doc-]"));
        System.out.println("Количество элементов = " + subMenuItems.size());

        for (int i = 1; i <= subMenuItems.size(); i++) {   // Если вложенных пунктов нет, цикл не выполняется
            WebElement currentMenuItem = driver.findElement(By.cssSelector("[id^=doc-]:nth-child(" + i + ")"));
            String subMenuItemName = currentMenuItem.getText();
            System.out.println("Клик по меню '" + subMenuItemName + "'");
            currentMenuItem.click();
            Assert.assertTrue("Во вложенном меню '" + subMenuItemName + "' нет заголовка",
                    isHeaderPresent());
        }
    }

    private boolean isHeaderPresent() {
        return isElementPresent(By.tagName("h1"));
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
