import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ReservationTest {

    private WebDriver driver;
    private String path = "http://localhost:8080/Project_war_exploded/Servlet";

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "D:\\user\\Documents\\School\\web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Reservation");
    }

    @After
    public void clean() {
        driver.quit();
    }


    @Test
    public void test_Reservation_Incomplete0_Registration() {
        driver.get(path+"?command=Home");

        logIn("1", "d");

        driver.get(path+"?command=Reservation");

        fillOutField("date", "25/11/2020");
        WebElement button=driver.findElement(By.id("book"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("date or hour invalid", errorMsg.getText());
    }

    @Test
    public void test_Reservation_Incomplete1_Registration() {
        driver.get(path+"?command=Home");

        logIn("1", "d");

        driver.get(path+"?command=Reservation");
        fillOutField("hour", "12:00");
        fillOutField("date", "");
        WebElement button=driver.findElement(By.id("book"));
        button.click();

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("date or hour invalid", errorMsg.getText());
    }


    @Test
    public void test_Reservation_Complete_Registration() {
        driver.get(path+"?command=Home");
        logIn("1", "d");

        driver.get(path+"?command=Reservation");

        fillOutField("date", "25/11/2020");
        fillOutField("hour", "12:00");

        WebElement button=driver.findElement(By.id("book"));
        button.click();

        Boolean isPresent = driver.findElements(By.className("alert-succes")).size() > 0;
        assertTrue(isPresent);

    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }


    private void logIn(String userid, String password) {
        fillOutField("userid", userid);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("logIn"));
        button.click();
    }



}

