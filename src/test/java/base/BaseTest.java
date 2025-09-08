package base;

import com.aventstack.extentreports.ExtentTest;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;

import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    public Properties prop;
    public ExtentTest test;

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeSuite
    public void cleanScreenshotsOnce() {
        FileCleanerUtil.cleanScreenshotsOnce("screenshots");
    }
    
    @BeforeMethod
    public void setup(Method method) {
//    	 	String testName = method.getName();
//    		FileCleanerUtil.cleanIfReRun("screenshots", testName);
        DriverFactory.initDriver(ConfigReaderUtils.get("browser"));
        DriverFactory.getDriver().get(ConfigReaderUtils.get("url"));
        DriverFactory.getDriver().manage().window().maximize();

        test = ExtentManager.getReporter().createTest(method.getName());
        testThread.set(test);
        ReportUtil.setTest(test);
    }

    public ExtentTest getTest() {
        return testThread.get();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
    	String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), result.getName());
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
            	    ReportUtil.logFail("❌ Test Failed", screenshotPath);
                ReportUtil.logInfo("❌ Test Failed");
                break;
            case ITestResult.SUCCESS:            
            		ReportUtil.logPass("✅ Test Passed", screenshotPath);
                ReportUtil.logInfo( "✅ Test Passed");
                break;
            case ITestResult.SKIP:
            		ReportUtil.logSkip("⏭️ Test Skipped", screenshotPath);
                ReportUtil.logInfo( "⏭️ Test Skipped");
                break;
        }

        DriverFactory.quitDriver();
    }
}
