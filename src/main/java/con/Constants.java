package con;

import org.json.simple.JSONObject;
import static utils.JsonUtils.*;
import static utils.FileUtils.*;

public class Constants {
    public static JSONObject config = readJson(read("src/main/resources/config.json"));
}
