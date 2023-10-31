package com.github.mblinov.diplom3;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.Properties;

public class DriverFactory extends ExternalResource {
    private final String chromeDriverPath;
    private final String yandexChromeDriverPath;
    private final String yandexBrowserPath;
    private WebDriver driver;

    public DriverFactory() {
        try (InputStream inputStream = DriverFactory.class.getClassLoader().getResourceAsStream("webdriver.properties")) {
            Properties appProps = new Properties();
            appProps.load(inputStream);

            chromeDriverPath = appProps.getProperty("path.chromedriver");
            yandexChromeDriverPath = appProps.getProperty("path.yandex.chromedriver");
            yandexBrowserPath = appProps.getProperty("path.yandex.browser");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Driver factory can not be initialized: properties not found");
        }
    }

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
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    private void setUpYandex() {
        System.setProperty("webdriver.chrome.driver", yandexChromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBrowserPath);
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
