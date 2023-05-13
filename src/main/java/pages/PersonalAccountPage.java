package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    WebDriver driver;

    //Конструктор
    private By constructor = By.xpath(".//p[text()='Конструктор']");

    //Логотип
    private By logo = By.xpath(".//div/header/nav/div");

    //кнопка Выход
    private final By exitButton = By.xpath("//button[text() = 'Выход']");


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кнопка Выход отображается на странице")
    public boolean isExitButtonVisible() {
        driver.findElement(exitButton).isDisplayed();
        return true;
    }

    @Step("Клик по конструктору")
    public PersonalAccountPage clickOnConstructor() {
        driver.findElement(constructor).click();
        return this;
    }

    @Step("Клик по логотипу")
    public PersonalAccountPage clickOnLogo() {
        driver.findElement(logo).click();
        return this;
    }

    @Step("Клик на кнопку выхода")
    public PersonalAccountPage clickOnExitButton() {
        driver.findElement(exitButton).click();
        return this;
    }
}
