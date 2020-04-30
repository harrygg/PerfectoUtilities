package g.perfecto.utilities;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.JsonException;

import com.google.gson.JsonObject;

public class Authenticator {
  
  public static Path tokensFilePath = Paths.get(System.getenv("HOMEPATH"), "perfectoTokens.json");
  private static JSONObject tokensObj = null;
  private static Log log = LogFactory.getLog(Authenticator.class);
  
  private static JSONObject getTokensObject() {
    try {

      if (tokensObj == null) {
        String jsonString = new String(Files.readAllBytes(tokensFilePath), StandardCharsets.UTF_8).trim();
        tokensObj = new JSONObject(jsonString);
      }
    } catch (JsonException je) {
      log.error("The file " + tokensFilePath + " does not contain a valid JSON object!");
      je.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tokensObj;
  }

  public static String getTokenForCloud(String host) {
    String token = null;
    int count = 0;
    JSONArray tokens = getTokensObject().getJSONArray("tokens");
    for (int i = 0; i < tokens.length(); i++) {
      JSONObject t = tokens.getJSONObject(i);
      String key = t.keys().next();
      if (key.equals(host)) {
        log.debug("Found token for " + host + " cloud!");
        token = t.getString(key);
        count++;
      }
    }
    if (count > 1)
      log.warn("Multiple tokens found for cloud " + host);
    return token;
  }
}