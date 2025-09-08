package tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import base.BaseTest;
import base.DriverFactory;
import dataproviders.CsvDataProvider;
import pages.LoginPage;
import pages.LogoutPage;
import utils.ScreenshotUtil;
import utils.ReportUtil;
import utils.CsvUtils.Credential;

public class LoginDataProviderTest extends BaseTest {

    @Test(dataProvider = "csvData", dataProviderClass = CsvDataProvider.class)
    public void loginDDT(Credential cred) throws InterruptedException {
        String user = cred.getUsername();
        String pass = cred.getPassword();

        ReportUtil.logInfo("Starting login test with username: " + user + " and password: " + pass);

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(user, pass);
        System.out.println("Running login test for: " + user);

        boolean loginSuccess = DriverFactory.getDriver().getCurrentUrl().contains("dashboard");
        boolean loginFailure = DriverFactory.getDriver().getPageSource().contains("Invalid credentials");

        String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "loginDDT_" + user + pass);

        if (loginSuccess) {
            ReportUtil.logPass("Login successful for user: " + user, screenshotPath);
            new LogoutPage(DriverFactory.getDriver(), test).logout();
            ReportUtil.logInfo("Logged out successfully");

        } else if (loginFailure) {
            ReportUtil.logFail("Login failed as expected for user: " + user, screenshotPath);
            Assert.fail("Login failed.");

        } else {
            ReportUtil.logFail("Unexpected login failure for user: " + user, screenshotPath);
            Assert.fail("Login failed unexpectedly — no dashboard and no error message.");
        }
    }
}
