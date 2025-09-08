package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
    public static String capture(WebDriver driver, String testName) throws InterruptedException {
    	Thread.sleep(2000);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String path = "screenshots/" + testName + ".png";
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}