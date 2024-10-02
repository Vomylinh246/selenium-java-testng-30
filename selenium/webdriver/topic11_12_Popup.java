import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic11_12_Popup {


    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class ,'MuiPaper-root')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@type='submit' and text()='Đăng nhập']")).click();
        Thread.sleep(1000);
        //FIXME: assert element not in DOM since website changed
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        driver.findElement(By.xpath("//div[@role='dialog']//button[@type='button']")).click();

    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='k-popup-account-login']//div[@class='modal-content']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("dam@gmail.com");
        driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//div[@data-view-id='header_user_shortcut']")).click();
        //Kiểm tra Login pop up có hiển thị
        By loginPopup = By.xpath("//div[contains(@class,'ReactModal__Content')]");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(), "Mật khẩu không được để trống");
        driver.findElement(By.xpath("//img[@class='close-img']")).click();
        //Kiểm tra xem Login popup không hiển thị
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

        @Test
                public void TC_04_Fixed_Popup_Not_In_DOM(){
            driver.get("https://www.facebook.com/");

        }

    }

    @AfterClass
    public void destroyBrowser() {

        driver.quit();
    }
}