package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ScreenshotUtil;
import utils.WaitUtil;
import utils.ReportUtil;
import com.aventstack.extentreports.ExtentTest;

public class LogoutPage {
    WebDriver driver;
    ExtentTest test;

    @FindBy(css = "i.oxd-icon.bi-caret-down-fill") WebElement profileDropdown;
    @FindBy(xpath = "//a[text()='Logout']") WebElement logoutLink;

    public LogoutPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    public void logout() throws InterruptedException {
        WaitUtil.waitForVisibility(driver, profileDropdown, 10);
        profileDropdown.click();
        WaitUtil.waitForVisibility(driver, logoutLink, 10);
        logoutLink.click();

        String screenshotPath = ScreenshotUtil.capture(driver, "logout");
        ReportUtil.logPass("Logged out successfully", screenshotPath);
    }
}
