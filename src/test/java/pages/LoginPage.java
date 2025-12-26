package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вводим имя пользователя: {username}")
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    @Step("Вводим пароль: {password}")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Кликаем кнопку Login")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Получаем сообщение об ошибке")
    public boolean getErrorMessage() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}

