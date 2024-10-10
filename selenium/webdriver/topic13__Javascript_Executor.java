package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class topic13__Javascript_Executor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }


    @Test
    public void TC_01_Javascript_Executor() {
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org/'");
        //get domain
        String domain = (String) jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(domain, "live.techpanda.org");
        //get URL
        String URL = (String) jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(URL, "http://live.techpanda.org/");
        // click vao Mobile
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Mobile']")));
        //add Samsung vao card
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button")));
        //verify message hien thi
        String messageText = jsExecutor.executeScript("return document.documentElement.innerText;").toString();
        Assert.assertTrue(messageText.contains("Samsung Galaxy was added to your shopping cart."));
        //click Customer service - verify title
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Customer Service']")));
        String pageTitle = jsExecutor.executeScript("return document.title;").toString();
        Assert.assertEquals(pageTitle, "Customer Service");
        // scroll toi Newsletter textbox
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@id='newsletter']")));
        //Input email hop le
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.xpath("//input[@id='newsletter']")), "automation246@gmail.com");
        // click Submit
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@title='Subscribe']")));

        //verify message
        String messageText2 = jsExecutor.executeScript("return document.documentElement.innerText;").toString();
        Assert.assertTrue(messageText2.contains("Thank you for your subscription."));
    }

    @Test
    public void TC_02_Javascript_Executor() {
        jsExecutor.executeScript("window.location = 'https://automationfc.github.io/html5/index.html'");
        //click vao SUBMIT
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='SUBMIT']")));

        WebElement nameField = driver.findElement(By.xpath("//input[@type='name']"));
        // verify message o field Name
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", nameField), "Please fill out this field.");
        //dien gia tri vaof field Name
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.xpath("//input[@type='name']")), "Linh Vo");
        //click vao Submit
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='SUBMIT']")));
        //verify message o field password
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", passwordField), "Please fill out this field.");
        // dien gia tri vao field password
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.xpath("//input[@type='password']")), "abcxyz");
        //click Submit
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='SUBMIT']")));
        //verify message o field Email
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", emailField), "Please fill out this field.");
        //dien gia tri invalid vao field email
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.xpath("//input[@type='email']")), "abc.gmail.com");
        //verify message o field email
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", emailField), "Please include an '@' in the email address. 'abc.gmail.com' is missing an '@'.");
        //dien gia tri valid vao field email
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", driver.findElement(By.xpath("//input[@type='email']")), "abc@gmail.com");
        //click Submit
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='SUBMIT']")));
        WebElement addresslField = driver.findElement(By.xpath("//b[text()='✱ ADDRESS ']/parent::label/following-sibling::select"));
        //verify message
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", addresslField), "Please select an item in the list.");
    }

    @Test
    public void TC_03_Javascript_Executor() {
        // Ubuntu
        jsExecutor.executeScript("window.location = 'https://login.ubuntu.com/'");
        WebElement emailField = driver.findElement(By.xpath("//form[@id='login-form']//input[@type='email']"));
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", emailField, "Linh Vo");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@data-qa-id='login_button']")));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", emailField), "Please include an '@' in the email address. 'Linh Vo' is missing an '@'.");

        driver.switchTo().newWindow(WindowType.TAB);

        // Sieuthimaymocthietbi
        jsExecutor.executeScript("window.location = 'https://sieuthimaymocthietbi.com/account/register'");
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@value='Đăng ký']")));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].validationMessage;", lastNameField), "Please fill out this field.");

    }

    @Test
    public void TC_04_Javascript_Executor() {
        jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4'");
        String email = "linh" + new Random().nextInt(9000) +"@gmail.com";
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='uid']")), "mngr596627");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='password']")), "derEgym");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='LOGIN']")));
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='New Customer']")));
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='name']")), "Vo Linh");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='dob']")), "2000-06-24");
        jsExecutor.executeScript("arguments[0].value = arguments[1]; arguments[0].onkeyup()", driver.findElement(By.xpath("//textarea[@name='addr']")), "Thanh Xuan");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='city']")), "Ha Noi");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='state']")), "Cali");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='pinno']")), "848484");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='telephoneno']")), "845454");
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='emailid']")), email);
        jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1]); arguments[0].onkeyup()", driver.findElement(By.xpath("//input[@name='password']")), "Abcxyz");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@value='Submit']")));
        Assert.assertEquals(jsExecutor.executeScript("return arguments[0].innerText;", driver.findElement(By.xpath("//p[@class='heading3']"))), "Customer Registered Successfully!!!");

    }


}