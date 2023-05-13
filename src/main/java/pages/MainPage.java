package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    WebDriver driver;

    //личный кабинет
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");

    //заголовок "Соберите бургер"
    private By theMainPageOfBurger = By.xpath("//div/main/section[1]/h1");

    //кнопка "Оформить заказ"
    private By buttonMakeOrder = By.xpath("//button[text()='Оформить заказ']");
    //кнопка "Войти в аккаунт"
    private By entranceInAcc = By.xpath(".//button[text()='Войти в аккаунт']");

    //Булки
    private By buns = By.xpath(".//span[text()='Булки']");

    //Начинки
    private By filling = By.xpath(".//span[text()='Начинки']");

    //Соусы
    private By sauce = By.xpath(".//span[text()='Соусы']");

    //Текущая вкладка
    private By currentTab = By.xpath(".//div[contains(@*,'current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на личный кабинет")
    public void clickOnPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    @Step("Клик на Войти в аккаунт")
    public void clickOnEntranceInAcc() {
        driver.findElement(entranceInAcc).click();
    }

    @Step("Появление кнопки оформить заказ")
    public boolean isMakeOrderButtonDisplayed() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(buttonMakeOrder));
        return true;
    }

    @Step("Проверка загрузки главной страницы сайта")
    public boolean isTheMainPageOfBurgerVisible() {
        driver.findElement(theMainPageOfBurger).isDisplayed();
        return true;
    }

    @Step("Клик на Булки")
    public void clickOnBuns() {
        driver.findElement(buns).click();
    }

    @Step("Клик на соусы")
    public void clickOnSauces() {
        driver.findElement(sauce).click();
    }

    @Step("Клик на начинки")
    public void clickOnFilling() {
        driver.findElement(filling).click();
    }

    @Step("Получить заголовок текущего раздела")
    public String getTextFromCurrentTab() {
        return driver.findElement(currentTab).getText();
    }
}
