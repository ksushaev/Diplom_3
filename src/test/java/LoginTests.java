import all_for_create_user.ChecksForUser;
import all_for_create_user.CreateUser;
import all_for_create_user.DataForLoginUser;
import config.ConfigForBurgers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;

    PersonalAccountPage personalAccountPage;
    CreateUser user = CreateUser.getUserWithRandomStringUtils();
    ChecksForUser checksForUser = new ChecksForUser();
    private final DataForLoginUser dataForLoginUser = new DataForLoginUser().from(user);
    private String accessToken = "";

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
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        checksForUser.createUserWithOkStatus(user);
        driver.get(ConfigForBurgers.URL);
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    public void loginByEntranceInAccButtonTest() {
        mainPage.clickOnEntranceInAcc();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход по кнопке Личный кабинет")
    public void loginByButtonPersonalAccountTest() {
        mainPage.clickOnPersonalAccount();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void entranceFromRegistrationPageTest() {
        mainPage.clickOnPersonalAccount();
        loginPage.clickOnReg();
        registrationPage.clickOnEntranceInReg();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void entranceFromPasswordRecoveryTest() {
        mainPage.clickOnPersonalAccount();
        loginPage.clickOnRecoveryPassword();
        recoveryPasswordPage.clickOnButtonSignInRecoveryPassword();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Выход пользователя")
    public void logOutTest() {
        mainPage.clickOnEntranceInAcc();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        mainPage.clickOnPersonalAccount();
        personalAccountPage.clickOnExitButton();
        String buttonOnLoginPage = loginPage.isLoginButtonVisible();
        Assert.assertEquals("Войти", buttonOnLoginPage);
    }

    @After
    public void tearDown() {
        String accessToken = checksForUser.autorizationOfUser(dataForLoginUser)
                .extract().path("accessToken");
        checksForUser.deleteUser(accessToken);
        driver.quit();
    }
}
