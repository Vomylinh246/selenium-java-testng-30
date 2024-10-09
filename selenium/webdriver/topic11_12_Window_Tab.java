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
    public void TC_13_Window_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //buoc nay can de lam step 10
        String githubID = driver.getWindowHandle();

        //Step 2.1: Click Google link
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        // Step 2.2: Switch qua tab Google
        switchByTitle("Google");

        // Step 4: Switch ve parent window
        switchByTitle("Selenium WebDriver");

        // Step 5.1 CLick Facebook link:
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // Step 5.2: Switch qua tab Facebook
         switchByTitle("Log into Facebook");

        // Step 6: Kiem tra title cua Facebook
        Assert.assertEquals(driver.getTitle(), "Log into Facebook");

        // Step 7: Switch ve parent window
        switchByTitle("Selenium WebDriver");

        // Step 8.1: Click TKI link
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();

        // Step 8.2: Switch qua tab TIKI
        switchByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // Step 9: Kiem tra title cua Tiki
        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        //Step 10: close tat ca tab ngoai tru parent window(github)
        Set<String> allTabID = driver.getWindowHandles();
        for (String id : allTabID) {
            driver.switchTo().window(id);
            if (!id.equals(githubID)) {
                driver.close();
            }
        }
    }

    @Test
    public void TC_14_Window_Tab() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        // Step 2: Click vao Mobile tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        // Step 3.1: Add to compare SonyXperia
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        // Step 3.2: Verify text hien thi
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
        // Step 4.1: Add to compare Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        Thread.sleep(3000);
        // Step 4.2: Verify text hien thi
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        // Step 5: Click to Compare button
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        Thread.sleep(3000);
        // Step 6: Switch qua cua so moi
        switchByTitle("Products Comparison List - Magento Commerce");
        // Step 7: Verify title cua window
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        //Step 8.1: Close tab
        driver.close();
        // Step 8.2: chuyen ve parent window
        switchByTitle("Mobile");
        //Step 9: Click Clear All
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        // Step 10: Accpet Alert
        driver.switchTo().alert().accept();
        //Step 11: Verify message
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_15_Window_Tab() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//span[@class='tb' and text()='Đăng nhập']")).click();
        // Step 3: switch qua cua so moi
        switchByTitle("Login");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        // Step 5: Verify message
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='loginID' and contains(@class,'gigya-error-type-server')]")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='password' and contains(@class,'gigya-error-type-server')]")).getText(), "This field is required");
        // Step 6.1: Close Login window
        driver.close();
        // Step 6.2: Switch ve trang truoc do
        switchByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        // Step 7.1: Nhap tu khoa bat ki vao o search
        driver.findElement(By.xpath("//input[@placeholder='Tìm kiếm Tiếng Anh']")).sendKeys("automation");
        // Step 7.2: click vao Search
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
    }

    @Test
    public void TC_16_Window_Tab() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String parentID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@data-action='login']")).click();

        Thread.sleep(5000);

        Set<String> AllID = driver.getWindowHandles();
        for(String id: AllID){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
            }
        }
       //driver.findElement(By.xpath("//button[@class='sam-wait__button sam-wait__button--login']")).click();

//        switchByTitle("Harvard Division of Continuing Education Login Portal");
        Thread.sleep(5000);
        driver.close();
        Thread.sleep(5000);
        switchByTitle("DCE Course Search");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(@class,'sam-wait__close')]")).click();
        // Nhap thong tin hop le vao field
        driver.findElement(By.xpath("//input[@id='crit-keyword']")).sendKeys("Data Science: An Artificial Ecosystem");


    }

    private void switchByTitle(String expectedTabTitle) {
        Set<String> allTabID = driver.getWindowHandles();
        for (String id : allTabID) {
            driver.switchTo().window(id);
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(expectedTabTitle)) {
                break;
            }
        }
    }

    @AfterClass
    public void destroyBrowser() {
        // driver.quit();
    }
}
