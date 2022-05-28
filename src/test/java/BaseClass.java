import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static base.driver.BrowserUtils.*;

public class BaseClass {

    @BeforeMethod
    public static void beforeMethod() {
        maximizeWindow();
        goTo();
    }

    @AfterMethod
    public static void afterMethod() {
        quit();
    }
}
