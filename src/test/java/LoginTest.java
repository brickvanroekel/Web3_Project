//Made by Brick van Roekel & Daan Stallaert

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private static WebDriver driver;
    String path = "http://localhost:8080/Project_war_exploded/Servlet";

    @Before
    public void SetupDriver() {
        // Setup the Chrome driver for the whole class
        System.setProperty("webdriver.chrome.driver", "D:\\user\\Documents\\School\\web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Home");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void NavigationTest_Index_Gives_Homepage(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        assertEquals("Home", driver.getTitle());
    }

    @Test
    public void Test_get_welcome_message_after_login(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("test");
        homePage.setPassword("test");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> h3s = (ArrayList<WebElement>) driver.findElements(By.tagName("h3"));
        assertTrue(homePage.containsWebelement(h3s, "Welcome tester"));
    }

    @Test
    public void Test_throw_error_when_username_empty(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("");
        homePage.setPassword("test");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> errorMessages = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(homePage.containsWebelement(errorMessages, "No userid given"));
    }

    @Test
    public void Test_throw_error_when_password_empty(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("test");
        homePage.setPassword("");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> errorMessages = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(homePage.containsWebelement(errorMessages, "No password given"));
    }

    @Test
    public void Test_throw_errors_when_password_wrong(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("test");
        homePage.setPassword("a");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> errorMessages = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(homePage.containsWebelement(errorMessages, "Password incorrect"));
    }

    @Test
    public void Test_throw_errors_when_username_wrong(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("a");
        homePage.setPassword("test");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> errorMessages = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(homePage.containsWebelement(errorMessages, "Username not found"));
    }

    @Test
    public void Test_throw_errors_when_password_and_username_are_empty(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.setUserid("");
        homePage.setPassword("");
        homePage.submit();

        assertEquals("Home",homePage.getTitle());
        ArrayList<WebElement> errorMessages = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(homePage.containsWebelement(errorMessages, "No userid given"));
        assertTrue(homePage.containsWebelement(errorMessages, "No password given"));
    }
}
