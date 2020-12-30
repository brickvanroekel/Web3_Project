//Made by Brick van Roekel & Daan Stallaert
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
    public HomePage(WebDriver driver){
        super(driver);
        driver.get(getPath()+"?command=Home");
    }

    @FindBy(id = "userid")
    WebElement useridField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "logIn")
    WebElement logInButton;


    public void setUserid(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submit(){
        logInButton.click();

    }


}

