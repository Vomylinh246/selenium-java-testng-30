package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class topic11_12_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // lay ra ID cua tab hien tai - githun
        String githubWindowID = driver.getWindowHandle();
        Thread.sleep(3000);

        // Step 2.1: click vao GOOGLE
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        // lay ra tat car window ID
        Set<String> allWindowID = driver.getWindowHandles();

        // Step 2.2: switch qua tab Google
        for (String id : allWindowID) {
            if (!id.equals(githubWindowID)) {
                driver.switchTo().window(id);
            }
        }
        // Step 3: kiem tra title cua Google
        Assert.assertEquals(driver.getTitle(), "Google");

        //Step 4: switch ve tab github
        String googleWindowID = driver.getWindowHandle();
        for (String id : allWindowID) {
            if (!id.equals(googleWindowID)) {
                driver.switchTo().window(id);
            }
        }

        // Step 5.1: click vao Facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        // Step 5.2: switch vao Facebook tab
        switchToWindowByTitle("Facebook – log in or sign up");

        // Step 6: kiem tra title cua Facbook
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        //Step 10: close tat ca cac tab ngoai tru parent tab(github)
        //lay ra toan bo window id
        Set<String> allWindowId = driver.getWindowHandles();
        for (String id : allWindowId) {
            if (!id.equals(githubWindowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

    private void switchToWindowByTitle(String expectedPageTitle) {
        //lay het toan bo ID cua tab: currently co 3 tab la github, google va facebook
        Set<String> allWindowIDs = driver.getWindowHandles();
        //dung vong lap duyet qua tung id
        for (String id : allWindowIDs) {
            //moi lan duyet cho stwich qua ID truoc
            driver.switchTo().window(id);
            //lay ra title cua tab hien tai
            String pageTitle = driver.getTitle();
            //kiem tra title
            if (pageTitle.equals(expectedPageTitle)) {
                break;
            }
        }
    }

    @Test
    public void TC_01_Window_Tab(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String githubID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchTabByTitle("Google");

    }

    private void switchTabByTitle(String expectedTabTitle) {
        Set<String> allTabID = driver.getWindowHandles();
        for(String id: allTabID){
            driver.switchTo().window(id);
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(expectedTabTitle)){
                break;
            }
        }
    }

    @AfterClass
    public void destroyBrowser() {
        // driver.quit();
    }
}
