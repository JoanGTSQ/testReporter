package dev.joangoma.testReporter.Custom;

import dev.joangoma.testReporter.ExtentTest;
import dev.joangoma.testReporter.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void addScreenshotToLog(SimpleDateFormat sdf, SimpleDateFormat sdfFolder, WebDriver driver, ExtentTest test, String testCase, String text, boolean fail, boolean pass) throws IOException {
        String dateFile = sdf.format(new Date());
        String dateFileFolder = sdfFolder.format(new Date());
        String screenshot = GetScreenshot.capture(driver,testCase +"_"+dateFile,dateFileFolder);
        if (fail){
            test.fail(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else if (pass){
            test.pass(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else {
            test.info(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        }
    }
    public static void addScreenshotToLogPoint(SimpleDateFormat sdf, SimpleDateFormat sdfFolder, WebDriver driver, ExtentTest test, String testCase, String text, String locator, boolean fail, boolean pass) throws IOException {
        String dateFile = sdf.format(new Date());
        String dateFileFolder = sdfFolder.format(new Date());

        String screenshot = GetScreenshot.capture(driver,testCase +"_"+dateFile,dateFileFolder, driver.findElement(By.xpath(locator)).getLocation(),
                driver.findElement(By.xpath(locator)).getSize().width,driver.findElement(By.xpath(locator)).getSize().height);

        if (fail){
            test.fail(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else if (pass){
            test.pass(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        } else {
            test.info(testCase +text,
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
        }
    }

}
