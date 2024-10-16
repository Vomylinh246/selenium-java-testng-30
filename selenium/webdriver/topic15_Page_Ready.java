package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic15_Page_Ready {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void TC_10_Page_Ready_Explicit_Wait() {
        driver.get("https://admin-demo.nopcommerce.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='master-wrapper-page']")));
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ajaxBusy']/span"))));
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'login-page')]//form")).isDisplayed());
    }

    @Test
    public void TC_10_Page_Ready_Function() {
        driver.get("https://admin-demo.nopcommerce.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='master-wrapper-page']")));
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Dashboard Page
        Assert.assertTrue(isPageLoadedSuccess());
        //Product Pgae
        //Assert.assertTrue(isPageLoadedSuccess());
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'login-page')]//form")).isDisplayed());
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null)) && (jQuery.active === 0);");
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jsLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void destroyBrowser() {
        driver.quit();
    }
}