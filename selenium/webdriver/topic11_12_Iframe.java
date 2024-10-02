import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic11_12_Iframe {


    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();


    }

    @Test
    public void TC_11_Iframe() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.xpath("//img[@alt='Campus Safety Survey']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frame-one85593366']")));
        new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-2']"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-3']"))).selectByVisibleText("North Dorm");
        driver.findElement(By.xpath("//label[@for='RESULT_RadioButton-4_0']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[contains(@class,'menu-item-login') and contains(@class, 'fs-btn--transparent-white')]")).click();

        driver.findElement(By.xpath("//button[@id='login']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='message-error']")).getText(), "Username and password are both required.");
    }


    @AfterClass
    public void destroyBrowser() {
        driver.quit();
    }
}