//Made by Brick van Roekel & Daan Stallaert
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class Page {
    String path = "http://localhost:8080/Project_war_exploded/Servlet";
    WebDriver driver;

    @FindBy(className = "alert-danger")
    WebElement errorMessagesField;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public boolean containsWebelement(ArrayList<WebElement> elements, String tekst){
        for(WebElement element:elements){
            if(element.getText().equals(tekst)){
                return true;
            }
        }
        return false;
    }

}
