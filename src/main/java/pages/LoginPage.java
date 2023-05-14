package pages;

import all_for_create_user.CreateUser;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    //кнопка "Зарегистрироваться"
    private By registration = By.className("Auth_link__1fOlj");

    //кнопка "Войти"
    private By buttonOfLogin = By.xpath(".//button[text()='Войти']");
    //кнопка "Email"
    private By emailInLoginPage = By.xpath(".//input[@name='name']");
    //кнопка "Пароль"
    private By passwordInLoginPage = By.xpath(".//input[@name='Пароль']");
    //Восстановить пароль
    private By recoveryPassword = By.xpath("//a[@href='/forgot-password']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на кпопку Зарегистрироваться")
    public LoginPage clickOnReg() {
        driver.findElement(registration).click();
        return this;
    }

    @Step("Заполнение формы логина")
    public LoginPage writeDataInLoginPage(CreateUser user) {
        driver.findElement(emailInLoginPage).sendKeys(user.getEmail());
        driver.findElement(passwordInLoginPage).sendKeys(user.getPassword());
        return this;
    }

    @Step("Прописываем поле Email")
    public LoginPage setEmailInLoginPage(String email) {
        driver.findElement(emailInLoginPage).sendKeys(email);
        return this;
    }

    @Step("Прописываем поле Password")
    public LoginPage setPasswordInLoginPage(String password) {
        driver.findElement(passwordInLoginPage).sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку Войти")
    public LoginPage clickOnSignIn() {
        driver.findElement(buttonOfLogin).click();
        return this;
    }

    @Step("Получение текста кнопки Войти")
    public String isLoginButtonVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(buttonOfLogin));
        return driver.findElement(buttonOfLogin).getText();
    }

    @Step("Клик на Восстановить пароль")
    public void clickOnRecoveryPassword() {
        driver.findElement(recoveryPassword).click();
    }
}
