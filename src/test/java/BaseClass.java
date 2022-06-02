import base.driver.BrowserUtils;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.nio.file.Paths;

import static utils.AllureUtils.*;
import static base.driver.BrowserUtils.*;
import static con.Constants.config;

public class BaseClass implements IHookable {

    @BeforeClass
    public static void beforeMethod() {
        maximizeWindow();
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String> builder()
                        .put("Browser", System.getenv("BROWSER"))
                        .put("ENV", getHostName())
                        .put("URL", config.get("url").toString())
                        .build());
    }

    @AfterClass
    public static void afterMethod() {
        quit();
        addAttachment(Paths.get("log.log"), "Log");
    }


    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            BrowserUtils.makeScreenShotByByte(testResult.getMethod().getMethodName());
        }
    }
}
