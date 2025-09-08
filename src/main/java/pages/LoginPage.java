package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;


public class LoginPage {
    WebDriver driver;

    @FindBy(name = "username") WebElement username;
    @FindBy(name = "password") WebElement password;
    @FindBy(css = "button[type='submit']") WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
    	WaitUtil.waitForVisibility(driver, username, 10);
    	
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }

	
}
