package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationPage {

    WebDriver driver;

    //поле Имя
    private By nameLocator = By.xpath(".//fieldset[1]/div/div/input");
    //поле Email
    private By emailLocator = By.xpath(".//fieldset[2]/div/div/input");
    //поле Пароль
    private By passwordLocator = By.xpath(".//fieldset[3]/div/div/input");

    //кнопка Зарегистрироваться
    private By buttonSignUpLocator = By.xpath("//button[text()='Зарегистрироваться']");

    //Неверный пароль
    private By wrongPassword = By.xpath("//p[text()='Некорректный пароль']");

    //Войти в форме регистрации
    private By entranceInReg = By.xpath("//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение поля Имя")
    public RegistrationPage writeNameForReg(String name) {
        driver.findElement(nameLocator).sendKeys(name);
        return this;
    }

    @Step("Заполнение поля Email")
    public RegistrationPage writeEmailForReg(String email) {
        driver.findElement(emailLocator).sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public RegistrationPage writePasswordForReg(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    @Step("Тап на кнопку регистрации")
    public void tapOnSignUpButton() {
        driver.findElement(buttonSignUpLocator).click();
    }

    @Step("Появление надписи Неверный пароль")
    public boolean wrongPasswordIsVisible() {
        return driver.findElement(wrongPassword).isDisplayed();
    }

    @Step("Клик на Войти в форме регистрации")
    public void clickOnEntranceInReg() {
        driver.findElement(entranceInReg).click();
    }
}
