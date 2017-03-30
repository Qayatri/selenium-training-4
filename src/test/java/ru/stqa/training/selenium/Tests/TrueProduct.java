package ru.stqa.training.selenium.Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ru.stqa.training.selenium.TestUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Supplier;

/*
 *   а) на главной странице и на странице товара совпадает текст названия товара
 *   б) на главной странице и на странице товара совпадают цены (обычная и акционная)
 *   в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
 *   г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
 *   (цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
 *   г) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
 */

@RunWith(Parameterized.class)
public class TrueProduct {

    private Supplier<WebDriver> webDriverSupplier;
    private WebDriver driver;
    private DecimalFormat decimalFormat;

    public TrueProduct(Supplier<WebDriver> webDriverSupplier) {
        this.webDriverSupplier = webDriverSupplier;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideParameters() {
        //noinspection RedundantArrayCreation
        return Arrays.asList(new Object[][]{
                {(Supplier<WebDriver>) ChromeDriver::new},
//                {(Supplier<WebDriver>) FirefoxDriver::new},
//                {(Supplier<WebDriver>) InternetExplorerDriver::new}
        });
    }

    @Before
    public void beforeTest() {
        System.out.println("Запуск браузера");
        driver = webDriverSupplier.get();
        decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        decimalFormat.setParseBigDecimal(true);
    }

    @After
    public void afterTest() {
        System.out.println("Завершение работы браузера");
        driver.quit();
        decimalFormat = null;
    }

    @Test
    public void productTest() throws InterruptedException {
        System.out.println("Открыть старницу http://localhost/litecart/admin");
        driver.get("http://localhost/litecart/en/");
//        List<WebElement> productContainers = getProductContainers(driver);
//        System.out.println("Found " + productContainers.size() + " products");
//        for (WebElement currentProductContainer : productContainers) {
//            ProductInfo productInfo = getProductInfo(currentProductContainer);
//            openProductDetails(currentProductContainer);
//            ProductInfo detailsProductInfo = getProductInfoFromDetailsScreen(driver);
//            assertSameProductInfo(productInfo, detailsProductInfo);
//            driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
//        }
    }

//    public void  isGreyColor(String colorRGB){
//        System.out.println("--> Цвет старой цены: "+colorRGB);
//        String[] greyColor = colorRGB.replaceAll("[a-zA-Z()]","").split(", ");
//
//        Assert.assertEquals(greyColor[0], greyColor[1]);
//        Assert.assertEquals(greyColor[0], greyColor[2]);
//        Assert.assertEquals(greyColor[1], greyColor[2]);
//        System.out.println("--> Совпадение с серым цветом");
//    }
//
//    public void  isRedColor(String colorRGB){
//        System.out.println("--> Цвет новой цены: "+colorRGB);
//        String[] greyColor = colorRGB.replaceAll("[a-zA-Z()]","").split(", ");
//
//        Assert.assertTrue( Integer.parseInt(greyColor[0]) != 0);
//        Assert.assertTrue(Integer.parseInt(greyColor[1]) == 0);
//        Assert.assertTrue(Integer.parseInt(greyColor[2]) == 0);
//        System.out.println("--> Совпадение с красным цветом");
//    }

//    @Test
//    public void productTest() throws InterruptedException {
//        System.out.println("Открыть старницу http://localhost/litecart/admin");
//        driver.get("http://192.168.1.98/litecart/en/");
//        List<WebElement> productContainers = getProductContainers(driver);
//        System.out.println("Found " + productContainers.size() + " products");
//        for (WebElement currentProductContainer : productContainers) {
//            ProductInfo productInfo = getProductInfo(currentProductContainer);
//            openProductDetails(currentProductContainer);
//            ProductInfo detailsProductInfo = getProductInfoFromDetailsScreen(driver);
//            assertSameProductInfo(productInfo, detailsProductInfo);
//            driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(0));
//        }
//    }
//
//    private void assertSameProductInfo(ProductInfo productInfo, ProductInfo detailsProductInfo) {
//        System.out.println("ProductInfo: " + productInfo + ", detailsProductInfo: " + detailsProductInfo);
//        Assert.assertEquals(productInfo.getName(), detailsProductInfo.getName());
//        Assert.assertEquals(productInfo.getPrice(), detailsProductInfo.getPrice());
//        Assert.assertEquals(productInfo.getSpecialPrice(), detailsProductInfo.getSpecialPrice());
//    }
//
//    private void openProductDetails(WebElement productContainer) {
//        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
//        productContainer.findElement(By.className("link")).sendKeys(selectLinkOpeninNewTab);
//        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
//    }
//
//    private List<WebElement> getProductContainers(WebDriver driver) {
//        return driver.findElements(By.className("product"));
//    }
//
//    private ProductInfo getProductInfo(WebElement productContainer) {
//        return ProductInfo.create(
//                getProductPrice(productContainer),
//                getProductSpecialPrice(productContainer),
//                getProductName(productContainer, By.className("name")));
//    }
//
//    private ProductInfo getProductInfoFromDetailsScreen(WebDriver driver) {
//        WebElement priceWrapper = driver.findElement(By.className("price-wrapper"));
//        return ProductInfo.create(
//                getProductPrice(priceWrapper),
//                getProductSpecialPrice(priceWrapper),
//                getProductName(driver.findElement(By.id("box-product")), By.className("title")));
//    }
//
//    private String getProductName(WebElement productContainer, By by) {
//        return productContainer.findElement(by).getText();
//    }
//
//    private BigDecimal getProductPrice(WebElement productContainer) {
//        Optional<WebElement> priceContainer = TestUtils.findElement(productContainer, By.className("price"));
//        if (!priceContainer.isPresent()) {
//            priceContainer = TestUtils.findElement(productContainer, By.className("regular-price"));
//        }
//        return parsePrice(priceContainer.get().getText());
//    }
//
//    private BigDecimal getProductSpecialPrice(WebElement productContainer) {
//        Optional<WebElement> priceContainer = TestUtils.findElement(productContainer, By.className("campaign-price"));
//        return priceContainer.isPresent()
//                ? parsePrice(priceContainer.get().getText())
//                : null;
//    }
//
//    private BigDecimal parsePrice(String priceText) {
//        if (priceText == null || priceText.isEmpty()) {
//            throw new IllegalArgumentException("Price text is empty");
//        }
//
//        try {
//            return (BigDecimal) decimalFormat.parse(priceText.replace("$", ""));
//        } catch (ParseException e) {
//            throw new IllegalStateException("Cannot parse price text");
//        }
//    }
//
//    private static class ProductInfo {
//
//        private final BigDecimal price;
//        private final String name;
//        private final BigDecimal specialPrice;
//
//        private ProductInfo(BigDecimal price, BigDecimal specialPrice, String name) {
//            this.price = price;
//            this.name = name;
//            this.specialPrice = specialPrice;
//        }
//
//        public static ProductInfo create(BigDecimal price, BigDecimal specialPrice, String name) {
//            return new ProductInfo(price, specialPrice, name);
//        }
//
//        public BigDecimal getPrice() {
//            return price;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public Optional<BigDecimal> getSpecialPrice() {
//            return Optional.ofNullable(specialPrice);
//        }
//
//        @Override
//        public String toString() {
//            return "ProductInfo{" +
//                    "price=" + price +
//                    ", name='" + name + '\'' +
//                    ", specialPrice=" + specialPrice +
//                    '}';
//        }
//    }
}
