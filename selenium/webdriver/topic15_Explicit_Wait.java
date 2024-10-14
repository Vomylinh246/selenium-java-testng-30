package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class topic15_Explicit_Wait {

    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String jennie = "Jennie.jpg";
    String jenniePath = uploadFolderPath + jennie;


    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    @Test
    public void TC_04_Explicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }

    @Test
    public void TC_05_Explicit_Wait() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
    }

    @Test
    public void TC_06_Explicit_Wait() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        //Step 2: Wait va verify Date time duoc hien thi
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']"))).isDisplayed());
        //Step 3: Wait and verify text khi chua select date
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"), "No Selected Dates to display.")));
        //Step 4: Wait and click ngay thang
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()=15]"))).click();
        //Step 5: Wait cho den khi ajax loading icon invisible and verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'RadCalendar1')]/div[@class='raDiv']"))));
        // Step 6: Wait den khi ngay 15 duoc selected
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td/a[text()=15]"))).isDisplayed());
        // Step 7: Wait and verify text hien thi
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"), "Tuesday, October 15, 2024")));
    }

    @Test
    public void TC_07_Explicit_Wait(){
        driver.get("https://gofile.io/?t=uploadFiles");
        //Wait loading icon
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner-border']"))));
        //Wait and click Update file
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Upload Files']"))).click();
        // Add files
        driver.findElement(By.xpath("//button[text()='Add files']")).sendKeys(jenniePath);
        // Wait loading icon
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='row mainUploadInitInfo']"))));
        //Wait loading bar
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(@class,'justify-content-center')]"))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a[@class='ajaxLink']"))).click();
    }

    @AfterClass
    public void destroyBrowser() {
        driver.quit();
    }

}