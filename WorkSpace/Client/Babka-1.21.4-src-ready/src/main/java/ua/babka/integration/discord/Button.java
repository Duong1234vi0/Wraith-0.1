package ua.babka.integration.discord;

import com.google.gson.JsonObject;

public record Button(String text, String url) {
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("label", this.text);
        jsonObject.addProperty("url", this.url);
        return jsonObject;
    }
}

