/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lombok.Generated;

public class n_473_l
extends I_1790_n<List<n_1700_B>> {
    public n_473_l() {
        super("temp\\friends.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new ArrayList();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        JsonArray friendsArray = new JsonArray();
        for (n_1700_B friend : (List)this.P_1922_E) {
            JsonObject friendObj = new JsonObject();
            friendObj.addProperty("name", friend.n_1700_B());
            friendObj.addProperty("addedTime", friend.J_1907_R().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            if (friend.R_4764_Y() != null) {
                friendObj.addProperty("displayAs", friend.R_4764_Y());
            }
            friendsArray.add((JsonElement)friendObj);
        }
        config.add("friends", (JsonElement)friendsArray);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((List)this.P_1922_E).clear();
        JsonArray friendsArray = jsonObject.getAsJsonArray("friends");
        if (friendsArray != null) {
            for (JsonElement element : friendsArray) {
                if (!element.isJsonObject()) continue;
                JsonObject friendObj = element.getAsJsonObject();
                this.n_1700_B(friendObj, "name", (JsonElement nameElement) -> this.n_1700_B(friendObj, "addedTime", (JsonElement timeElement) -> {
                    try {
                        String friendName = nameElement.getAsString();
                        String timeStr = timeElement.getAsString();
                        LocalDateTime addedTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        JsonElement displayEl = friendObj.get("displayAs");
                        String displayAs = displayEl != null && !displayEl.isJsonNull() ? displayEl.getAsString() : null;
                        ((List)this.P_1922_E).add(new n_1700_B(friendName, addedTime, displayAs));
                    }
                    catch (Exception e) {
                        System.err.println("Failed to parse friend entry: " + e.getMessage());
                    }
                }));
            }
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    @Override
    public void n_1700_B(String friendName) {
        if (friendName == null || friendName.trim().isEmpty()) {
            System.err.println("Cannot add friend: name is null or empty");
            return;
        }
        String name = friendName.trim();
        if (!this.R_4764_Y(name)) {
            ((List)this.P_1922_E).add(new n_1700_B(name, LocalDateTime.now(), null));
            this.G_564_y();
        }
    }

    public void n_1700_B(String friendName, String displayAs) {
        if (friendName == null || friendName.trim().isEmpty()) {
            System.err.println("Cannot add friend: name is null or empty");
            return;
        }
        String name = friendName.trim();
        n_1700_B existing = ((List)this.P_1922_E).stream().filter(e -> e.n_1700_B().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (existing != null) {
            existing.n_1700_B(displayAs);
        } else {
            ((List)this.P_1922_E).add(new n_1700_B(name, LocalDateTime.now(), displayAs));
        }
        this.G_564_y();
    }

    public void J_1907_R(String friendName) {
        if (friendName == null) {
            System.err.println("Cannot remove friend: name is null");
            return;
        }
        boolean removed = ((List)this.P_1922_E).removeIf(entry -> entry.n_1700_B().equalsIgnoreCase(friendName.trim()));
        if (removed) {
            this.G_564_y();
        }
    }

    public void M_588_G() {
        if (!((List)this.P_1922_E).isEmpty()) {
            ((List)this.P_1922_E).clear();
            this.G_564_y();
        }
    }

    public boolean R_4764_Y(String friendName) {
        if (friendName == null) {
            return false;
        }
        return ((List)this.P_1922_E).stream().anyMatch(entry -> entry.n_1700_B().equalsIgnoreCase(friendName.trim()));
    }

    public n_1700_B G_564_y(String friendName) {
        if (friendName == null) {
            return null;
        }
        String t = friendName.trim();
        return ((List)this.P_1922_E).stream().filter(entry -> entry.n_1700_B().equalsIgnoreCase(t)).findFirst().orElse(null);
    }

    public List<n_1700_B> P_4830_p() {
        return new ArrayList<n_1700_B>((Collection)this.P_1922_E);
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final LocalDateTime J_1907_R;
        private String R_4764_Y;

        public n_1700_B(String name, LocalDateTime addedTime, String displayAs) {
            this.n_1700_B = name;
            this.J_1907_R = addedTime;
            this.R_4764_Y = lightning.product.n_473_l$n_1700_B.J_1907_R(displayAs);
        }

        public void n_1700_B(String displayAs) {
            this.R_4764_Y = lightning.product.n_473_l$n_1700_B.J_1907_R(displayAs);
        }

        private static String J_1907_R(String s) {
            if (s == null) {
                return null;
            }
            String t = s.trim();
            return t.isEmpty() ? null : t;
        }

        public String toString() {
            return this.n_1700_B + " (" + this.J_1907_R.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")";
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public LocalDateTime J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public String R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}

