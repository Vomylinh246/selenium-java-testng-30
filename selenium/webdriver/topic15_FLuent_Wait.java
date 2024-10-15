package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.function.Function;

public class topic15_FLuent_Wait {

    WebDriver driver;
    FluentWait<WebDriver> driverFluentWait;



    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driverFluentWait = new FluentWait<>(driver);
    }
    @Test
    public void TC_09_Fluent_Wait(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.xpath("//div[@id='start']/button")).click();

Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
    }
    public WebElement findElement(By by) {
       FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(30));
        driverFluentWait.pollingEvery(Duration.ofMillis(300));
        driverFluentWait.ignoring(NoSuchElementException.class);
        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(by);
            }
        });
    }
    public boolean isElementDisplayed(By by) {
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(30));
        driverFluentWait.pollingEvery(Duration.ofMillis(300));
        driverFluentWait.ignoring(NoSuchElementException.class);
        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }



    @AfterClass
    public void destroyBrowser() {
        driver.quit();
    }

}