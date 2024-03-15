package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class topic03_Xpath {


    WebDriver driver;

    @BeforeMethod
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }


    @Test
    public void should_ErrorMessage_WithEmptyData() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void should_ErrorMessage_WithInvalidEmail() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtFirstname']")).sendKeys("John Kennedy");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("123@456@789");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCEmail']")).sendKeys("123@456@789");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPhone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void should_ErrorMessage_WithIncorrectConfirmEmail() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtFirstname']")).sendKeys("John Kennedy");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPhone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void should_ErrorMessage_WithPasswordLess6Characters() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtFirstname']")).sendKeys("John Kennedy");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPhone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void should_ErrorMEssage_WithIncorrectConfirmPassword() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtFirstname']")).sendKeys("John Kennedy");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPhone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void should_ErrorMessage_WithInvalidPhoneNumber() {
        //Action
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtFirstname']")).sendKeys("John Kennedy");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtPhone']")).sendKeys("012345678999");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
    }

    @AfterMethod
    public void closingBrowser() {
        driver.quit();
    }

}