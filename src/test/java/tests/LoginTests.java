package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

@ExtendWith(AllureJunit5.class)
public class LoginTests {
    private WebDriver chrome;
    private LoginPage login;
    private InventoryPage inventory;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        chrome = new ChromeDriver();
        login = new LoginPage(chrome);
        inventory = new InventoryPage(chrome);
    }

    @AfterEach
    public void quitBrowser(){
        if (chrome != null) {
            chrome.quit();
        }
    }

    // First test (correct login & password)
    @Test
    @Description("Проверяем успешный логин с правильными данными")
    public void SuccessfulLogin() {
        chrome.get(ConfigReader.getBaseUrl());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        assertTrue(inventory.isPageOpened());
    }

    // Second test (incorrect login & password)
    @Test
    @Description("Проверяем неуспешный логин с неправильными данными")
    public void UnsuccessfulLogin() {
        chrome.get(ConfigReader.getBaseUrl());
        login.enterUsername("nonstandard_user");
        login.enterPassword("public_sauce");
        login.clickLogin();
        assertTrue(login.getErrorMessage());
    }

    // Third test (blocked user)
    @Test
    @Description("Проверяем логин блокированного пользователя")
    public void BlockedUserLogin() {
        chrome.get(ConfigReader.getBaseUrl());
        login.enterUsername("locked_out_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        assertTrue(login.getErrorMessage());
    }

    // Fourth test (empty input)
    @Test
    @Description("Проверяем логин с пустыми полями")
    public void EmptyInputLogin() {
        chrome.get(ConfigReader.getBaseUrl());
        login.clickLogin();
        assertTrue(login.getErrorMessage());
    }

    // Fifth test (glitching user)
    @Test
    @Description("Проверяем успешный логин для пользователя с задержкой")
    public void GlitchingUserLogin() {
        chrome.get(ConfigReader.getBaseUrl());
        login.enterUsername("performance_glitch_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        assertTrue(inventory.isPageOpened());
    }
}
