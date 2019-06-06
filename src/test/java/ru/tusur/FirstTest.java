package ru.tusur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/Admin/chromedriver.exe");

        driver = new ChromeDriver();
    }

    @Test
    public void FirstTest() {

        driver.get("https://tusur.ru/");

        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Томский государственный университет систем управления и радиоэлектроники"));

    }

    @After
    public void close() {
        driver.quit();
    }
}
