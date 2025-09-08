package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public class AdminPage {
    WebDriver driver;

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminTab;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement usernameSearchBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-card']//div[contains(text(),'Admin')]")
    WebElement resultCell;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchUser(String username) {
        WaitUtil.waitForVisibility(driver, adminTab, 10);
        adminTab.click();

        WaitUtil.waitForVisibility(driver, usernameSearchBox, 10);
        usernameSearchBox.sendKeys(username);

        searchButton.click();
    }

    public boolean isUserFound() {
        WaitUtil.waitForVisibility(driver, resultCell, 10);
        return resultCell.isDisplayed();
    }
}
