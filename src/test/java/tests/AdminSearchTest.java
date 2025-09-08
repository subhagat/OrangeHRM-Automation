package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import base.DriverFactory;
import pages.LoginPage;
import utils.ConfigReaderUtils;
import utils.ReportUtil;
import utils.ScreenshotUtil;
import pages.AdminPage;

public class AdminSearchTest extends BaseTest {
	
	 @Test
	    public void searchAdminUserTest() throws InterruptedException {
	        ReportUtil.logInfo("🔍 Starting Admin user search test");

	        try {
	            new LoginPage(DriverFactory.getDriver()).login(ConfigReaderUtils.get("username"), ConfigReaderUtils.get("password"));
	            ReportUtil.logInfo("✅ Logged in successfully");

	            AdminPage adminPage = new AdminPage(DriverFactory.getDriver());
	            ReportUtil.logInfo("📂 Navigating to Admin module and searching for user: Admin");
	            adminPage.searchUser("Admin");

	            ReportUtil.logInfo("🔎 Validating search result");
	            Assert.assertTrue(adminPage.isUserFound(), "❌ Admin user not found in search results");

	            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "admin_search_success");
	            Thread.sleep(2000);
	            ReportUtil.logPass("✅ Admin user found successfully", screenshotPath);

	        } catch (AssertionError ae) {
	            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "admin_search_assert_fail");
	            ReportUtil.logFail("❌ Assertion failed: " + ae.getMessage(), screenshotPath);
	            throw ae;

	        } catch (Exception e) {
	            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), "admin_search_exception");
	            ReportUtil.logFail("⚠️ Unexpected error: " + e.getMessage(), screenshotPath);
	            throw new RuntimeException(e);
	        }
	    }
}
