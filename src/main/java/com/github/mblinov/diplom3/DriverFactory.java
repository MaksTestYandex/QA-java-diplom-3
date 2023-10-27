package com.github.mblinov.diplom3;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() {
        if ("yandex".equals(System.getProperty("browser"))) {
            setUpYandex();
        } else {
            setUpChrome();
        }
    }

    @Override
    protected void after() {
        driver.quit();
    }

    private void setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "B:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    private void setUpYandex() {
        System.setProperty("webdriver.chrome.driver", "B:\\WebDriver\\bin\\yandexdriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\mablinov\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
