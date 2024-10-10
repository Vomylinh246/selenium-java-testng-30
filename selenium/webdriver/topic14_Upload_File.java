package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class topic14_Upload_File {
    WebDriver driver;
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String jennie = "Jennie.jpg";
    String rose = "Rose.jpg";
    String jenniePath = uploadFolderPath + jennie;
    String rosePath = uploadFolderPath + rose;
    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Upload_Single_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By inputBy = By.xpath("//input[@type='file']");
        // upload file len
        driver.findElement(inputBy).sendKeys(jenniePath);
        Thread.sleep(2000);
        driver.findElement(inputBy).sendKeys(rosePath);
        // verify file da duoc load len
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Jennie.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Rose.jpg']")).isDisplayed());
        // click upload cho tung file
        List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
        for(WebElement startButton: startButtons){
           startButton.click();
        }
    }
    @Test
    public void TC_01_Upload_Multiple_File() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By inputBy = By.xpath("//input[@type='file']");
        // upload file len
        driver.findElement(inputBy).sendKeys(jenniePath + "\n" + rosePath);
        // verify file da duoc load len
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Jennie.jpg']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Rose.jpg']")).isDisplayed());
        // click upload cho tung file
        List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
        for(WebElement startButton: startButtons){
            startButton.click();
        }
    }
}