package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import pages.LoginPage;
import utils.ConfigReaderUtils;
import utils.ReportUtil;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {

	
    @Test
    public void loginTest() throws InterruptedException {
        ReportUtil.logInfo("🔐 Starting login test");

        try {
            new LoginPage(DriverFactory.getDriver()).login(ConfigReaderUtils.get("username"), ConfigReaderUtils.get("password"));
            ReportUtil.logInfo("🔍 Verifying dashboard access");

            Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("dashboard"), "❌ Dashboard not loaded");
            

            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "login_success");
            ReportUtil.logPass("✅ Login successful", screenshotPath);

        } catch (AssertionError ae) {
            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "login_assert_fail");
            ReportUtil.logFail("❌ Assertion failed: " + ae.getMessage(), screenshotPath);
            throw ae;

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "login_exception");
            ReportUtil.logFail("⚠️ Unexpected error: " + e.getMessage(), screenshotPath);
            throw new RuntimeException(e);
        }
    }
}