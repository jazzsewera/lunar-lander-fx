package lunarlander;

import com.google.gson.*;

public class Configuration {

  Gson gson = new Gson();

  // Method converting Java obj to JSON
  public String serialize(SomePrimitives obj) {
    String json = gson.toJson(obj);
    return json;
  }

}
