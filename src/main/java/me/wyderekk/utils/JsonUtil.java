package me.wyderekk.utils;

import me.wyderekk.main.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import static me.wyderekk.main.Main.parser;

public class JsonUtil {

    public static JSONObject stringToJSON(String json) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getToken() {
        try {
            Object obj = parser.parse(new FileReader(Main.path));
            JSONObject jsonObject = (JSONObject) obj;
            return (String) jsonObject.get("token");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
