package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        //FIXME: assert element not in DOM since website changed -> Done
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='notistack-snackbar']")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        driver.findElement(By.xpath("//div[@role='dialog']//button[@type='button']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class ,'MuiPaper-root')]")).size(), 0);

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

    }

    @Test
    public void TC_05_Random_Popup_In_DOM() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        driver.manage().deleteAllCookies();
        By randomPopup = By.xpath("//div[@id='popmake-39268']");
        Thread.sleep(5000);
        if (!driver.findElements(randomPopup).isEmpty() && driver.findElement(randomPopup).isDisplayed()) {
            driver.findElement(By.xpath("//div[@id='popmake-39268']//button[@type='button']")).click();
        }
        driver.findElement(By.xpath("//div[@id='mega-menu-wrap-primary']//a[text()='Liên hệ']"));


    }


    @Test
    public void TC_06_Random_Popup_Not_In_DOM() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        driver.manage().deleteAllCookies();
        By randomPopup = By.xpath("//div[contains(@class, 'lepopup-popup-container')]/div[1]");
        Thread.sleep(60000);
        if (!driver.findElements(randomPopup).isEmpty() && driver.findElement(randomPopup).isDisplayed()) {
            driver.findElement(By.xpath("//a[@onclick='return lepopup_close();' and not(contains(@class,'lepopup-inherited lepopup-inherited'))]")).click();
        }
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Aigle Testing Explained");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();
    }

    @Test
    public void TC_07_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
        driver.manage().deleteAllCookies();
        By randomPopup = By.xpath("//div[contains(@class,'modal-content')]");

        if (driver.findElement(randomPopup).isDisplayed()) {
            driver.findElement(By.xpath("//button[@class='close']")).click();
            driver.findElement(By.xpath("//a[text()=' Đăng nhập']")).click();
        }
        //!driver.findElements(randomPopup).isEmpty() &&

    }

    @Test
    public void TC_08_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement parent = driver.findElement(By.xpath("//div[@id='shadow_host']"));
        SearchContext firstShadow = parent.getShadowRoot();
        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(), "some text");
        WebElement secondParent = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadow = secondParent.getShadowRoot();
        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content")).getText(), "nested text");
        Assert.assertFalse(firstShadow.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

    }

    @Test
    public void TC_09_Shadow_DOM_Popup() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);
        WebElement firstParent = driver.findElement(By.xpath("//book-app"));
        SearchContext firstShadow = firstParent.getShadowRoot();

        firstShadow.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        WebElement secondParent = firstShadow.findElement(By.cssSelector("book-input-decorator"));
        SearchContext secondShadow = secondParent.getShadowRoot();
        secondShadow.findElement(By.cssSelector("div.icon")).click();
    }


    @AfterClass
    public void destroyBrowser() {

        driver.quit();
    }
}
