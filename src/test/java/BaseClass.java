import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.nio.file.Paths;

import static selen.settings.Settings.*;
import static selen.Selen.*;
import static utils.AllureUtils.addAttachment;

public class BaseClass implements IHookable {

    @BeforeClass
    public static void beforeMethod() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String> builder()
                        .put("Browser", settings.get("browserName").toString())
                        .put("ENV", browser().getHostName())
                        .put("URL", settings.get("url").toString())
                        .build());
    }

    @AfterClass
    public static void afterMethod() {
        browser().quit();
        addAttachment(Paths.get("log.log"), "Log");
    }


    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            browser().makeScreenShotByByte(testResult.getMethod().getMethodName());
        }
    }
}
