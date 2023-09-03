package ru.digitalLeague;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelectionMobilePhoneTest {
    private WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://yandex.by");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldOpenMarket() {
        // вводим в поисковую строку Яндекс Маркет и нажимаем Enter
        driver.findElement(By.xpath("//input[@class='search3__input mini-suggest__input']"))
                .sendKeys("Яндекс Маркет", Keys.ENTER);
        // находим первую ссылку на Яндекс Маркет
        WebElement linkMarket = driver.findElement(By.xpath("(//h2[contains(@class,'OrganicTitle-LinkText')])[2]"));
        // кликаем по ссылке с помощью JS-кода
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", linkMarket);
//        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("a.focus-ring[aria-label='Маркет']"))));
        // ищем логотип с текстом Маркет
        WebElement marketText = driver.findElement(By.cssSelector("a.focus-ring[aria-label='Маркет']"));
        String actualText = marketText.getText();
        String expected = "Маркет";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldOpenSmartphoneTopic() {
        driver.findElement(By.xpath("//input[@class='search3__input mini-suggest__input']"))
                .sendKeys("Яндекс Маркет", Keys.ENTER);
        WebElement linkMarket = driver.findElement(By.xpath("(//h2[contains(@class,'OrganicTitle-LinkText')])[2]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", linkMarket);
        // вводим в поле поиска - Смартфоны
        driver.findElement(By.xpath("//*[@id='header-search']"))
                .sendKeys("Смартфоны", Keys.ENTER);
        // проверяем что находимся в разделе Смартфоны
        WebElement smartphoneText = driver.findElement(By.xpath("//h1[contains(text(),'Смартфон')]"));
        String actualText = smartphoneText.getText();
        String expected = "Смартфоны";
        assertEquals(expected, actualText);
    }

    @Test
    public void shouldSetPrice() {
        driver.findElement(By.xpath("//input[@class='search3__input mini-suggest__input']"))
                .sendKeys("Яндекс Маркет", Keys.ENTER);
        WebElement linkMarket = driver.findElement(By.xpath("(//h2[contains(@class,'OrganicTitle-LinkText')])[2]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", linkMarket);
        driver.findElement(By.xpath("//*[@id='header-search']"))
                .sendKeys("Смартфоны", Keys.ENTER);
        // вводим цену до 20_000
        driver.findElement(By.xpath("//input[@id='range-filter-field-glprice_lm5gzgc2suf_max']"))
                .sendKeys("20000", Keys.ENTER);
        // кликаем по сортировки цены 2 раза
        WebElement sortPrice = driver.findElement(By.xpath("//button[contains(text(),'по цене')]"));
        new Actions(driver)
                .doubleClick(sortPrice)
                .perform();
        // проверяем что наибольшая цена в списке товаров 20_000
        WebElement firstPrice = driver.findElement(By.xpath("(//span[@data-auto='price-value'])[1]"));
        String actual = firstPrice.getAttribute("children");
        String expected = "20000";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSetDiagonal() {
        driver.findElement(By.xpath("//input[@class='search3__input mini-suggest__input']"))
                .sendKeys("Яндекс Маркет", Keys.ENTER);
        WebElement linkMarket = driver.findElement(By.xpath("(//h2[contains(@class,'OrganicTitle-LinkText')])[2]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", linkMarket);
        driver.findElement(By.xpath("//*[@id='header-search']"))
                .sendKeys("Смартфоны", Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='range-filter-field-glprice_lm5gzgc2suf_max']"))
                .sendKeys("20000", Keys.ENTER);
        // выбираем диагональ 3.5"-4.9"
        WebElement chooseDiagonal = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[32]"));
        new Actions(driver)
                .click(chooseDiagonal)
                .perform();
        // проверяем что параметры диагонали выбраны
//        boolean isChecked;
//        WebElement isChecked = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[32]")).isSelected();
    }

    @Test
    public void shouldChoose5PopularManufacturers() {
        driver.findElement(By.xpath("//input[@class='search3__input mini-suggest__input']"))
                .sendKeys("Яндекс Маркет", Keys.ENTER);
        WebElement linkMarket = driver.findElement(By.xpath("(//h2[contains(@class,'OrganicTitle-LinkText')])[2]"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", linkMarket);
        driver.findElement(By.xpath("//*[@id='header-search']"))
                .sendKeys("Смартфоны", Keys.ENTER);
        driver.findElement(By.xpath("//input[@id='range-filter-field-glprice_lm5gzgc2suf_max']"))
                .sendKeys("20000", Keys.ENTER);
        WebElement chooseDiagonal = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[32]"));
        new Actions(driver)
                .click(chooseDiagonal)
                .perform();
        // выбираем первых 5 производителей
        WebElement chooseManufacturer1 = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[1]"));
        new Actions(driver)
                .click(chooseManufacturer1)
                .perform();
        WebElement chooseManufacturer2 = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[2]"));
        new Actions(driver)
                .click(chooseManufacturer2)
                .perform();
        WebElement chooseManufacturer3 = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[3]"));
        new Actions(driver)
                .click(chooseManufacturer3)
                .perform();
        WebElement chooseManufacturer4 = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[4]"));
        new Actions(driver)
                .click(chooseManufacturer4)
                .perform();
        WebElement chooseManufacturer5 = driver.findElement(By.xpath("(//span[@class='_1Mp5C'])[5]"));
        new Actions(driver)
                .click(chooseManufacturer5)
                .perform();
        // проверяем что производители выбрались
    }

}
