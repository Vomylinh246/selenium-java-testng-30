package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic15_Selenium_WebDriver_Wait {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void TC_02_Implicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_Static_Wait() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }
//    @Test
//    public void TC_04_Explicit_Wait(){
//        //Wait cho element khong hien thu, khong con trong HTML (truoc do co ton tai)
//        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(""))));
//        //Wait cho element khong hien thi (con/khong con trong HTML)
//        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
//        // Wait cho element duoc hien thi (phai co trong HTML/tren UI)
//        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
//        //Wait cho element duoc phep click (button/link/radio button/ checkbox)
//        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
//        //Wait cho URL cua page tuyet doi
//        explicitWait.until(ExpectedConditions.urlToBe(""));
//        //Wait cho URL cua page tuong doi
//        explicitWait.until(ExpectedConditions.urlContains(""));
//        //Wait cho URL cua page thoa man bieu thuc (Regex)
//        explicitWait.until(ExpectedConditions.urlMatches(""));
//        //Wait cho 1 doan JS tra ve kieu du lieu String
//        explicitWait.until(ExpectedConditions.jsReturnsValue(""));
//        //Wait cho Alert xuat hien trong HTML
//        explicitWait.until(ExpectedConditions.alertIsPresent());
//        //Wait cho title cua page tuyet doi
//        explicitWait.until(ExpectedConditions.titleIs(""));
//        //Wait cho title cua page tuong doi
//        explicitWait.until(ExpectedConditions.titleContains(""));
//        //Wait cho element thoa man 2 hoac n dieu kien
//        explicitWait.until(ExpectedConditions.and());
//        //Wait cho element thoa man 1 trong 2/n dieu kien
//        explicitWait.until(ExpectedConditions.or());
//        //Wait cho element xuat hien trong HTML
//        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
//        //Wait cho 1 element co thuoc tinh chua 1 gia tri nao do
//        explicitWait.until(ExpectedConditions.attributeContains(By.xpath()"")));
//        //wWait cho element co thuoc tinh bang 1 gia tri nao do
//        explicitWait.until(ExpectedConditions.attributeToBe(By.xpath("")));
//        //Wait cho element co thuoc tinh khong duoc rong/null
//        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(By.xpath("")));
//        //Wait cho element co thuoc tinh o trong DOM co gia tri nao do
//        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.xpath(""))));
//        //Wait cho element da duoc chon hay khong (Checkbox/ Radio Button/ Dropdown Item...)
//        explicitWait.until(ExpectedConditions.elementToBeSelected(By.xpath("")));
//        //Wait cho element chua duoc chon thanh cong
//        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(""), true));
//        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(""), false));
//        //Wait cho frame/iframe xuat hien va switch vao
//        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("")));
//        //Wait cho 1 doan lenh JS duoc thuc thi va khong tra ve bat cu Exception nao het
//        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions(""));
//        //Phu dinh lai dieu kien Wiat
//        explicitWait.until(ExpectedConditions.not());
//        //Wait cho 1 list element bang bao nhieu item
//        explicitWait.until(ExpectedConditions.numberOfElementsToBe());
//        //Wait cho 1 list element nho hon bao nhieu item
//        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan());
//        //Wait cho 1 list element nho hon bao nhieu item
//        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan());
//        //Wait cho so luong window/tab bang bao nhieu
//        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
//        // Wait cho 1 doan text bang tuyet doi
//        explicitWait.until(ExpectedConditions.textToBe(By.xpath(""),""));
//        //Wiat cho 1 element hay bi change/ refresh lai/ update lai
//        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.numberOfWindowsToBe(3)));
//    }

    @AfterClass
    public void destroyBrowser() {
        driver.quit();
    }

}