package g.perfecto.utilities;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.openqa.selenium.remote.JsonException;


public class TokenStorage {
  
  public static Path tokensFilePath = Paths.get(System.getenv("HOMEPATH"), "securityTokens.json");
  private static JSONObject tokensObj = null;
  private static Log log = LogFactory.getLog(TokenStorage.class);
  
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

  public static String getTokenForCloud(String host) 
  {
    if (host.endsWith(".perfectomobile.com"))
      host = host.replace(".perfectomobile.com", "");
    
    String token = getTokensObject().getJSONObject("tokens").getString(host);
    log.debug("Found token for " + host + " cloud!");

    return token;
  }
}