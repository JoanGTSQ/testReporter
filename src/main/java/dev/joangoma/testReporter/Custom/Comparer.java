package dev.joangoma.testReporter.Custom;

import dev.joangoma.testReporter.ExtentTest;
import dev.joangoma.testReporter.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Comparer {
    public static void checkCSS(WebDriver driver, String locator, String text, String testCase, ExtentTest test) {
        if (driver.findElement(By.cssSelector(locator)).getAttribute("value").equals(text)) {
            if (text.equals("")) {
                test.log(Status.INFO, testCase + "cleared successfully" + text);
            } else {
                test.log(Status.INFO, testCase + "found " + text);
            }
        } else {
            if (text.equals("")) {
                test.log(Status.WARNING, testCase + "can not be cleared");
            } else {
                test.log(Status.WARNING, testCase + "not found " + text);
            }

        }
    }

    public static boolean checkXPath(WebDriver driver, String locator, String text, String testCase,ExtentTest test) {
        if (driver.findElement(By.xpath(locator)).getText().equals(text)) {
            if (text.equals("")) {
                test.log(Status.INFO, testCase + "cleared successfully" + text);
            } else {
                test.log(Status.INFO, testCase + "found " + text);
            }
            return true;
        } else {
            if (text.equals("")) {
                test.log(Status.WARNING, testCase + "can not be cleared");
            } else {
                test.log(Status.WARNING, testCase + "not found " + text);
            }
            return false;
        }
    }

    public static boolean checkXPathAttribute(WebDriver driver, String locator, String text, String testCase, ExtentTest test) {
        if (driver.findElement(By.xpath(locator)).getAttribute("value").equals(text)) {
            if (text.equals("")) {
                test.log(Status.INFO, testCase + "cleared successfully" + text);

            } else {
                test.log(Status.INFO, testCase + "found " + text);
            }
            return true;
        } else {
            if (text.equals("")) {
                test.log(Status.WARNING, testCase + "can not be cleared");
            } else {
                test.log(Status.WARNING, testCase + "not found " + text);
            }
            return false;
        }
    }

}

