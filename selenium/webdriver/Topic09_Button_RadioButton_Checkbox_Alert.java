import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic09_Button_RadioButton_Checkbox_Alert {

    WebDriver driver;

    @Test
    public void TC_01_Button() {
        driver = new EdgeDriver();
        driver.get("https://www.fahasa.com/customer/account/create");

        WebElement loginTab = driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]"));
        loginTab.click();
        WebElement loginButton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(loginButton.isEnabled());
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("Linhvtm@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
        Assert.assertTrue(loginButton.isEnabled());
        driver.quit();

    }

    @Test
    public void TC_02_Default_Checkbox_Radio_Button() {
        driver = new EdgeDriver();
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='eq5']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
        checkbox.click();
        Assert.assertFalse(checkbox.isSelected());
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement radioButton = driver.findElement(By.xpath("//input[@id='engine3']"));
        radioButton.click();
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
        driver.quit();
    }

    @Test
    public void TC_03_Default_Checkbox_Radio_Button() {
        driver = new EdgeDriver();
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement radioButton = driver.findElement(By.cssSelector("input#mat-radio-3-input"));
        radioButton.click();
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@id='mat-mdc-checkbox-1-input']"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[@id='mat-mdc-checkbox-2-input']"));
        checkbox1.click();
        checkbox2.click();
        Assert.assertTrue(checkbox1.isSelected() && checkbox2.isSelected());
        if (checkbox1.isSelected() && checkbox2.isSelected()) {
            checkbox1.click();
            checkbox2.click();
        }
        Assert.assertFalse(checkbox1.isSelected() && checkbox2.isSelected());
        driver.quit();

    }

    @Test
    public void TC_04_Select_All_Checkboxes() {
        driver = new EdgeDriver();
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
            Assert.assertTrue(checkbox.isSelected());
        }
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.getAttribute("value").equals("Heart Attack")) {
                checkbox.click();

            }
        }
        WebElement heartAttack = driver.findElement(By.id("input_52_11"));
        Assert.assertTrue(heartAttack.isSelected());
        driver.quit();

    }

    @Test
    public void TC_05_Custom_Checkbox() {
        driver = new EdgeDriver();
        driver.get("https://login.ubuntu.com/");
        WebElement labelButton = driver.findElement(By.xpath("//label[@for='id_new_user']"));
        labelButton.click();
        WebElement radioButton = driver.findElement(By.xpath("//input[@id ='id_new_user']"));
        Assert.assertTrue(radioButton.isSelected());
        driver.quit();


    }

}