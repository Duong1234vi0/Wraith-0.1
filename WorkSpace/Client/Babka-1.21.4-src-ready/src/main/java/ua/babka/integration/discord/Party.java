package ua.babka.integration.discord;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public record Party(String id, int currentSize, int maxSize) {
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        JsonArray sizeArray = new JsonArray();
        sizeArray.add((Number)this.currentSize);
        sizeArray.add((Number)this.maxSize);
        jsonObject.add("size", (JsonElement)sizeArray);
        return jsonObject;
    }
}

