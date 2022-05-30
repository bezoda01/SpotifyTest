import base.driver.BrowserUtils;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static base.driver.BrowserUtils.*;
import static con.Constants.config;

public class BaseClass implements IHookable {

    @BeforeClass
    public static void beforeMethod() {
        maximizeWindow();
        goTo();
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String> builder()
                        .put("Browser", config.get("type").toString())
                        .put("ENV", getHostName())
                        .put("URL", config.get("url").toString())
                        .build());
    }

    @AfterSuite
    public static void afterMethod() {
        quit();
    }


    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            BrowserUtils.makeScreenShotByByte(testResult.getMethod().getMethodName());
        }
    }
}
