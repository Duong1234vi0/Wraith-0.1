package ua.babka.integration.discord;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.babka.integration.discord.Button;
import ua.babka.integration.discord.Party;

public record ActivityInfo(String details, String state, String largeImage, String largeText, String smallImage, String smallText, Long startTime, Party party, Button button1, Button button2) {
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        if (this.details != null) {
            jsonObject.addProperty("details", this.details);
        }
        if (this.state != null) {
            jsonObject.addProperty("state", this.state);
        }
        jsonObject.add("assets", (JsonElement)this.getAssetsObject());
        jsonObject.add("timestamps", (JsonElement)this.getTimestampsObject());
        if (this.party != null) {
            jsonObject.add("party", (JsonElement)this.party.toJson());
        }
        if (this.button1 != null || this.button2 != null) {
            jsonObject.add("buttons", (JsonElement)this.getButtonsArray());
        }
        jsonObject.addProperty("instance", Boolean.valueOf(false));
        return jsonObject;
    }

    private JsonObject getAssetsObject() {
        JsonObject jsonObject = new JsonObject();
        if (this.largeImage != null) {
            jsonObject.addProperty("large_image", this.largeImage);
        }
        if (this.largeText != null) {
            jsonObject.addProperty("large_text", this.largeText);
        }
        if (this.smallImage != null) {
            jsonObject.addProperty("small_image", this.smallImage);
        }
        if (this.smallText != null) {
            jsonObject.addProperty("small_text", this.smallText);
        }
        return jsonObject;
    }

    private JsonObject getTimestampsObject() {
        JsonObject jsonObject = new JsonObject();
        if (this.startTime != null) {
            jsonObject.addProperty("start", (Number)this.startTime);
        }
        return jsonObject;
    }

    private JsonArray getButtonsArray() {
        JsonArray jsonArray = new JsonArray();
        if (this.button1 != null) {
            jsonArray.add((JsonElement)this.button1.toJson());
        }
        if (this.button2 != null) {
            jsonArray.add((JsonElement)this.button2.toJson());
        }
        return jsonArray;
    }

    public ActivityInfo withDetails(String details) {
        return this.details == details ? this : new ActivityInfo(details, this.state, this.largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withState(String state) {
        return this.state == state ? this : new ActivityInfo(this.details, state, this.largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withLargeImage(String largeImage) {
        return this.largeImage == largeImage ? this : new ActivityInfo(this.details, this.state, largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withLargeText(String largeText) {
        return this.largeText == largeText ? this : new ActivityInfo(this.details, this.state, this.largeImage, largeText, this.smallImage, this.smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withSmallImage(String smallImage) {
        return this.smallImage == smallImage ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, smallImage, this.smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withSmallText(String smallText) {
        return this.smallText == smallText ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, this.smallImage, smallText, this.startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withStartTime(Long startTime) {
        return this.startTime == startTime ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, this.smallImage, this.smallText, startTime, this.party, this.button1, this.button2);
    }

    public ActivityInfo withParty(Party party) {
        return this.party == party ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, party, this.button1, this.button2);
    }

    public ActivityInfo withButton1(Button button1) {
        return this.button1 == button1 ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, this.party, button1, this.button2);
    }

    public ActivityInfo withButton2(Button button2) {
        return this.button2 == button2 ? this : new ActivityInfo(this.details, this.state, this.largeImage, this.largeText, this.smallImage, this.smallText, this.startTime, this.party, this.button1, button2);
    }
}

