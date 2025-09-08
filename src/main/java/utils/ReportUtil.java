package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ReportUtil {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void logPass(String message, String screenshotPath) {
        String styledMessage = "<span style='color:green;font-weight:bold;'>" + message + "</span>";
        getTest().pass(styledMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logFail(String message, String screenshotPath) {
        String styledMessage = "<span style='color:red;font-weight:bold;'>" + message + "</span>";
        getTest().fail(styledMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logSkip(String message, String screenshotPath) {
        String styledMessage = "<span style='color:orange;font-weight:bold;'>" + message + "</span>";
        getTest().skip(styledMessage, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    public static void logInfo(String message) {
        String styledMessage = "<span style='color:blue;'>" + message + "</span>";
        getTest().info(styledMessage);
    }
}
