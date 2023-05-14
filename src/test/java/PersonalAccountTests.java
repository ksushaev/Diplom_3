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

public class PersonalAccountTests {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    RecoveryPasswordPage recoveryPasswordPage;

    PersonalAccountPage personalAccountPage;
    CreateUser user = CreateUser.getUserWithRandomStringUtils();
    ChecksForUser checksForUser = new ChecksForUser();
    private final DataForLoginUser dataForLoginUser = DataForLoginUser.from(user);

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
        mainPage.clickOnPersonalAccount();
        loginPage.writeDataInLoginPage(user)
                .clickOnSignIn();
        mainPage.isMakeOrderButtonDisplayed();
    }

    @Test
    @DisplayName("Переход в личный кабинет зарегистрированного пользователя")
    public void clickToPersonalAccTest() {
        mainPage.clickOnPersonalAccount();
        Assert.assertTrue(personalAccountPage.isExitButtonVisible());
    }

    @Test
    @DisplayName("Проверка переход по клику на «Конструктор»")
    public void clickOnConstructor() {
        mainPage.clickOnPersonalAccount();
        personalAccountPage.clickOnConstructor();
        Assert.assertTrue(mainPage.isTheMainPageOfBurgerVisible());
    }

    @Test
    @DisplayName("Проверка перехода на логотип")
    public void clickOnLogo() {
        mainPage.clickOnPersonalAccount();
        personalAccountPage.clickOnLogo();
        Assert.assertTrue(mainPage.isTheMainPageOfBurgerVisible());
    }

    @After
    public void tearDown() {
        String accessToken = checksForUser.autorizationOfUser(dataForLoginUser)
                .extract().path("accessToken");
        checksForUser.deleteUser(accessToken);
        driver.quit();
    }
}
