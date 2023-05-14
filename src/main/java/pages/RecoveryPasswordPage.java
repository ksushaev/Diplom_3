package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    WebDriver driver;

    //кнопка Войти на странице восстановления пароля
    private By buttonSignInRecoveryPassword = By.xpath("//a[@href='/login']");

    public RecoveryPasswordPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Клик на кнопку Войти на странице восстановления пароля")
    public void clickOnButtonSignInRecoveryPassword() {
        driver.findElement(buttonSignInRecoveryPassword).click();
    }
}
