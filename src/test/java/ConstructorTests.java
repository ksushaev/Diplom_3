import config.ConfigForBurgers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTests {

    private WebDriver driver;
    MainPage mainPage;

    @Before
    public void setup() {
        //для запуска в Яндекс браузере
        //System.setProperty("webdriver.chrome.driver", "/Users/ksenianikulsina/WebDriver/bin/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        //options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        driver.get(ConfigForBurgers.URL);
    }

    @Test
    @DisplayName("Активность раздела Булки")
    public void activeBunsTest() {
        mainPage.clickOnFilling();
        mainPage.clickOnBuns();
        Assert.assertEquals("Булки", mainPage.getTextFromCurrentTab());
    }

    @Test
    @DisplayName("Активность раздела Соусы")
    public void activeSauceTest() {
        mainPage.clickOnSauces();
        Assert.assertEquals("Соусы", mainPage.getTextFromCurrentTab());
    }

    @Test
    @DisplayName("Активность раздела Начинки")
    public void activeFillingTest() {
        mainPage.clickOnFilling();
        Assert.assertEquals("Начинки", mainPage.getTextFromCurrentTab());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
