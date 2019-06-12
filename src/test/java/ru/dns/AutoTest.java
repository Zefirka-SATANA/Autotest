package ru.dns;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AutoTest extends Settings {

    /**
     *  Проверка перехода к странице выбора смартфонов
     */
    @Test
    public void openSelectPhonePage() {

        driver.manage().window().maximize();
        driver.get("https://www.dns-shop.ru/");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu-catalog")));

        //Переход к нужному разделу
        driver.findElement(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Средства связи']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Смартфоны']")).click();

        //Забрать текс описания раздела
        String name = driver.findElement(By.xpath("//h1[text()='Смартфоны']")).getText();

        //Убедиться, что текст совпадает с ожидаемым
        Assert.assertEquals("Смартфоны", name);
    }

    /**
     *  Проверка корректности минимального значения при выборе диапазона цены
     */
    @Test
    public void checkMinPhonePrice() {

        driver.manage().window().maximize();
        driver.get("https://www.dns-shop.ru/");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")));

        //Переход к нужному разделу
        driver.findElement(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Средства связи']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Смартфоны']")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='left-filters__buttons']")));

        //Выбр нужного диапозона цен
        driver.findElement(By.xpath("//span[text()='4 001 - 10 000']")).click();

        //Приминение фильтра по ценам
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='apply-filters-float-btn']")));
        driver.findElement(By.xpath("//div[@class='apply-filters-float-btn']")).click();

        //Дождать приминения фильтров и взять значение цены у первого элемента
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Цена: от 4 001 р. до 10 000 р.']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-position-index='0']//div[@class='product-price__current']")));
        String price = driver.findElement(By.xpath("//div[@data-position-index='0']//div[@class='product-price__current']")).getText();

        //Удаление пробелов и приведение цены к нужному типу
        price = price.replaceAll("\\s", "");
        int price_check = Integer.parseInt(price);

        //Проверить, что значение цены находится в рамках минимальной границы
        if (price_check <= 4000) {
            Assert.fail("Неверно задан минимальный порог цены");
        }
    }

    /**
     *  Проверка корректности максимального значения при выборе диапазона цены
     */
    @Test
    public void checkMaxPhonePrice() {

        driver.manage().window().maximize();
        driver.get("https://www.dns-shop.ru/");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")));

        //Переход к нужному разделу
        driver.findElement(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Средства связи']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Смартфоны']")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='left-filters__buttons']")));

        //Выбр нужного диапозона цен
        driver.findElement(By.xpath("//span[text()='4 001 - 10 000']")).click();

        //Приминение фильтра по ценам
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='apply-filters-float-btn']")));
        driver.findElement(By.xpath("//div[@class='apply-filters-float-btn']")).click();

        //Выбор фильтрации по убыванию цены
        driver.findElement(By.xpath("//span[text()='По возрастанию цены']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='popover-block popover-block_show']")));
        driver.findElement(By.xpath("//div[@class='popover-block popover-block_show']//span[text()='По убыванию цены']")).click();

        //Получение цены первого элемента, цена которога является макимальной
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-position-index='0']//div[@class='product-price__current']")));
        String price = driver.findElement(By.xpath("//div[@data-position-index='0']//div[@class='product-price__current']")).getText();

        //Удаление пробелов и приведение цены к нужному типу
        price = price.replaceAll("\\s", "");
        int price_check = Integer.parseInt(price);

        //Сравнение полученной цены с максимальным граничным значением
        if (price_check >= 10000) {
            Assert.fail("Неверно задан максимальный порог цены");
        }
    }

    /**
     *  Валидация недопустимых символов для поля "Цена"
     */
    @Test
    public void checkCharSymbolsInFilter() {

        driver.manage().window().maximize();
        driver.get("https://www.dns-shop.ru/");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")));

        //Переход к нужному разделу
        driver.findElement(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Средства связи']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Смартфоны']")).click();

        //Ввод невалидных символов в поле минимальной цены
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='Цена']/ancestor::div//input[@placeholder = 'от 1999']")));
        WebElement price = driver.findElement(By.xpath("//span[text()='Цена']/ancestor::div//input[@placeholder = 'от 1999']"));
        price.sendKeys("[]{}()!*<>/\\|");

        //Переход и нажатие на кнопку приминения фильтрации
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Применить']")));
        WebElement acceptBtn = driver.findElement(By.xpath("//button[text()='Применить']"));
        moveToElement(acceptBtn);
        acceptBtn.click();

        //Проверка поля цены
        moveToElement(price);
        String priceValue = price.getText();
        Assert.assertEquals("", priceValue);
    }

    /**
     *  Проверка корректной фильтрации смартфонов по выбранной марке
     */
    @Test
    public void checkProductName() {

        driver.manage().window().maximize();
        driver.get("https://www.dns-shop.ru/");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")));

        //Переход к нужному разделу
        driver.findElement(By.xpath("//ul[@id='menu-catalog']//span[text()='Смартфоны и смарт-часы']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Средства связи']")).click();
        driver.findElement(By.xpath("//a[@class = 'category-item-desktop']//span[text()='Смартфоны']")).click();

        //Поиск необходимой марки смартфона
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Поиск']")));
        driver.findElement(By.xpath("//input[@placeholder='Поиск']")).sendKeys("Xiaomi");

        //Преминение фильтра по марке смарфона
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='Xiaomi ']")));
        driver.findElement(By.xpath("//span[text()='Xiaomi ']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='apply-filters-float-btn']")));
        driver.findElement(By.xpath("//div[@class='apply-filters-float-btn']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Производитель: Xiaomi']")));

        //Получение текста наименования первого смартфона
        String name = driver.findElement(By.xpath("//div[@data-position-index='0']//a[@class='ui-link']")).getText();

        //Поиск в полученном наименовании необходимого слова
        Assert.assertTrue("Неверная марка смартфона", name.contains("Xiaomi"));
    }
}
