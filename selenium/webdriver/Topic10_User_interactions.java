import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic10_User_interactions {

    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser(){
        driver = new EdgeDriver();
        action = new Actions(driver);
       driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Button() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        Thread.sleep(2000);//workaround - should use explicitWait or implicitWait
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
        driver.quit();

    }
    @Test
    public void TC_03_Hover_To_Element() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Hành Trang Đến Trường']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Luyện Thi Môn Toán']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Toán']")).isDisplayed());
        driver.quit();

    }

    @Test
    public void TC_04_Click_And_Hold_Element(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        Assert.assertEquals(allNumbers.size(), 20);
        action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
        List<WebElement> allNumbersSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class, 'ui-selected')]"));
        Assert.assertEquals(allNumbersSelected.size(), 4);
        // TODO: verify 4 so duoc chon la 1,2,3,4
        driver.quit();

    }

    @Test
    public void TC_05_Click_And_Select_Random_Element(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.keyDown(Keys.CONTROL).perform();
        action.click(allNumbers.get(0)).
                click(allNumbers.get(2)).
                click(allNumbers.get(5)).
                click(allNumbers.get(10)).perform();
        action.keyUp(Keys.CONTROL).perform();
        List<WebElement> allNumbersSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(allNumbersSelected.size(), 4);
        Assert.assertEquals(allNumbersSelected.get(0).getText(),"1");
        Assert.assertEquals(allNumbersSelected.get(2).getText(),"3");
        Assert.assertEquals(allNumbersSelected.get(5).getText(),"6");
        Assert.assertEquals(allNumbersSelected.get(10).getText(),"11");
        driver.quit();
    }

    @Test
    public void TC_06_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
    }





}