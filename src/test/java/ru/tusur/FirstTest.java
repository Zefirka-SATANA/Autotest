package ru.tusur;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    @Test
    public void FirstTest() {
        System.setProperty("webdriver.chrome.driver",
                   "C:/Users/Admin/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://tusur.ru/");

        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Томский государственный университет систем управления и радиоэлектроники"));

        driver.quit();
    }
}
