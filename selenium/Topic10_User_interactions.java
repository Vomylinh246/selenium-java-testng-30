import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

public class Topic10_User_interactions {

    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser(){
        driver = new EdgeDriver();
        action = new Actions(driver);
       driver.manage().window().maximize();
        System.out.println("Starting test before class");

    }


    @Test
    public void TC_01_Button() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        Thread.sleep(2000);//workaround - should use explicitWait or implicitWait
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");


    }
    @Test
    public void TC_03_Hover_To_Element() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Hành Trang Đến Trường']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Luyện Thi Môn Toán']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Toán']")).isDisplayed());


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


    }

    @Test //FIXME: fix this failed test
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
        Assert.assertEquals(allNumbersSelected.get(1).getText(),"3");
        Assert.assertEquals(allNumbersSelected.get(2).getText(),"6");
        Assert.assertEquals(allNumbersSelected.get(3).getText(),"11");

    }

    @Test
    public void TC_06_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click_To_Element(){
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickMeButton = driver.findElement(By.xpath("//span[text()='right click me']"));
        action.contextClick(rightClickMeButton).perform();
        WebElement quitButton = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
        Assert.assertTrue(quitButton.isDisplayed());
        action.moveToElement(quitButton).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertTrue(driver.findElement(By.xpath(
                "//li[contains(@class,'context-menu-icon-quit') " +
                        "and contains(@class,'context-menu-visible') " +
                        "and contains(@class,'context-menu-hover')]")).isDisplayed());

    }

    @Test
    public void TC_08_Drag_And_Drop_Element_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
        action.dragAndDrop(smallCircle, bigCircle).perform();
        Assert.assertEquals(bigCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
    }

    @Test
    public void TC_09_Drag_And_Drop_HTML5() throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement sourceSquare = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement targetSquare = driver.findElement(By.xpath("//div[@id='column-b']"));
        action.dragAndDrop(sourceSquare,targetSquare).perform();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");



    }
   
    @AfterClass
    public void destroyBrowser(){
        driver.quit();

    }






}