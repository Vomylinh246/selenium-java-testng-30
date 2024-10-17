package testng;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Always_Run {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Run Before Class");
    }

    @Test
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    @Test
    public void TC_03() {
        System.out.println("Run TC 03");
    }
}
