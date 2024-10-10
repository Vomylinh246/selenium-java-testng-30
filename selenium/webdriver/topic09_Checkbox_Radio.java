import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class topic09_Checkbox_Radio {

    WebDriver driver;

    @Test
    public void TC_01_RunOnFirefox() {
        driver = new FirefoxDriver();
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.quit();
    }

    @Test
    public void TC_01_RunOnChrome() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
//        driver.findElement("//");
    }
//command
    @Test
    public void TC_01_RunOnEdge() {
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

}