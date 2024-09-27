import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class topic_default_dropdown {


    WebDriver driver;

    @BeforeMethod
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }


    @Test
    public void Register() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // điền input cho Fist name
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Vo");

        // điền input cho Last name
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Linh");

        //chọn dropdown Day
        Select select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        //verify giá trị trong dropdown Day + số lượng giá trị
        select.selectByVisibleText("1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
        Assert.assertEquals(select.getOptions().size(), 32);

//chọn dropdown Month
        Select select1 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        select1.selectByVisibleText("May");
        Assert.assertEquals(select1.getFirstSelectedOption().getText(), "May");
        Assert.assertEquals(select1.getOptions().size(), 13);

//chọn dropdown Year
        Select select2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        select2.selectByVisibleText("1980");
        Assert.assertEquals(select2.getFirstSelectedOption().getText(), "1980");
        Assert.assertEquals(select2.getOptions().size(), 112);

        // Generate random text for email
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        //điền email
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(generatedString + "@gmail.com");
        //điền input cho Password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //điền input cho Confirm Password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

// bấm vào button Register
        driver.findElement(By.xpath("//button[@name='register-button']")).click();

        //wait
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //verify home page
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
        driver.findElement(By.xpath("//div[@class='header-links']//a[@href='/customer/info']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        select1 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        select2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
        Assert.assertEquals(select1.getFirstSelectedOption().getText(), "May");
        Assert.assertEquals(select2.getFirstSelectedOption().getText(), "1980");
           }


    @AfterMethod
    public void closingBrowser() {
        driver.quit();
    }

}