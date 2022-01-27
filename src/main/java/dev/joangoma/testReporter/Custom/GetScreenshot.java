package dev.joangoma.testReporter.Custom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetScreenshot {
    public static void setPath(String path) {
        GetScreenshot.path = path;
    }

    private static String path;

    public static void setIDTEST(int IDTEST) {
        GetScreenshot.IDTEST = IDTEST;
    }

    private static int IDTEST;

    public static String capture(WebDriver driver, String screenShotName, String dateFolder) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = path +dateFolder+"//"+ IDTEST +"//"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        return "../ErrorScreenshots/" +dateFolder+"/"+ IDTEST +"/"+screenShotName+".png";
    }
    public static String capture(WebDriver driver, String screenShotName, String dateFolder, Point point, int w, int h) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String dest = path +dateFolder+"//"+ IDTEST +"//"+screenShotName+".png";
        File destination = new File(dest);
        BufferedImage fullImg = ImageIO.read(screenshot);
        BufferedImage rectScreenshot  = fullImg.getSubimage(point.getX(),point.getY(),
                w,h);
        ImageIO.write(rectScreenshot,"png",screenshot);
        FileUtils.copyFile(screenshot, destination);

        return "../ErrorScreenshots/" +dateFolder+"/"+ IDTEST +"/"+screenShotName+".png";
    }
    public static String capture65(WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
