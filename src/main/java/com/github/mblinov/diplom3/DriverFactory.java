//package com.github.mblinov.diplom3;
//
//import org.junit.rules.ExternalResource;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.GeckoDriverService;
//
//import java.io.File;
//
//public class DriverFactory extends ExternalResource {
//    private WebDriver driver;
//
//    @Override
//    protected void before() {
//        if (System.getProperty("browser", "chrome").equals("firefox")) {
//            setUpFirefox();
//        } else {
//            setUpChrome();
//        }
//    }
//
//    @Override
//    protected void after() {
//        driver.quit();
//    }
//
//    private void setUpChrome() {
//        ChromeOptions options = new ChromeOptions();
//    //    System.setProperty("webdriver.chrome.driver", "B:\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);
//    }
//
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//}
