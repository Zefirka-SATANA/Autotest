package ru.dns;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class Settings {
    ChromeDriver driver;
    WebDriverWait wait;

    void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        Action action = actions.moveToElement(element).build();
        action.perform();
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void close() {
        driver.quit();
    }
}
