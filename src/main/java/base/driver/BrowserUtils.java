package base.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

import static base.driver.Driver.getDriver;
import static utils.FileUtils.copy;
import static con.Constants.*;

public class BrowserUtils {


    public static boolean makeScreenShot(String name) {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File pathToSS = new File( config.get("pathScreen") + name + ".png");
        copy(scrFile, pathToSS.getAbsolutePath());
        return pathToSS.exists();
    }

    public static void close() {
        getDriver().close();
    }

    public static void quit() {
        getDriver().quit();
    }

    public static void goTo() {
        getDriver().get(config.get("url").toString());
    }

    public static void goTo(String URN) {
        getDriver().get(config.get("url").toString() + "/" + URN);
    }

    public static void maximizeWindow() {
        getDriver().manage().window().maximize();
    }
}
