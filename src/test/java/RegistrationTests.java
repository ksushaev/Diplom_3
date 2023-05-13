import config.ConfigForBurgers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

public class RegistrationTests {

    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String name = RandomStringUtils.randomAlphabetic(10);
    String email = RandomStringUtils.randomAlphabetic(10) + "@mail.ru";
    String password = RandomStringUtils.randomAlphabetic(6);


    @Before
    public void setup() {
        //для запуска в Яндекс браузере
        //System.setProperty("webdriver.chrome.driver", "/Users/ksenianikulsina/WebDriver/bin/yandexdriver");
        ChromeOptions options = new ChromeOptions();
        //options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.get(ConfigForBurgers.URL);
        mainPage.clickOnPersonalAccount();
        loginPage.clickOnReg();
    }


    @Test
    @DisplayName("Регистрация нового пользователя с корректными данными")
    public void RegistrationOfNormalUserTest() {
        registrationPage.writeNameForReg(name)
                .writeEmailForReg(email)
                .writePasswordForReg(password)
                .tapOnSignUpButton();
        String buttonOnLoginPage = loginPage.isLoginButtonVisible();
        Assert.assertEquals("Войти", buttonOnLoginPage);
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем менее 6 знаков")
    public void RegistrationOfUserWithBadPasswordTest() {
        password = RandomStringUtils.randomAlphabetic(5);
        registrationPage.writeNameForReg(name)
                .writeEmailForReg(email)
                .writePasswordForReg(password)
                .tapOnSignUpButton();
        Assert.assertTrue(registrationPage.wrongPasswordIsVisible());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
