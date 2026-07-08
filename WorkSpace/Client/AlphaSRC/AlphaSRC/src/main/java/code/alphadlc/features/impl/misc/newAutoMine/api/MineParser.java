/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.class_1297
 *  net.minecraft.class_1531
 */
package code.alphadlc.features.impl.misc.newAutoMine.api;

import code.alphadlc.features.impl.misc.newAutoMine.MiningAnarchyState;
import code.alphadlc.features.impl.misc.newAutoMine.api.MineApi;
import code.alphadlc.features.impl.player.autoMine.api.MineType;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_1297;
import net.minecraft.class_1531;

public class MineParser
implements QuickImports,
MineApi {
    private MiningAnarchyState anarchyState = null;

    public static int formatMMSS(String string) {
        String[] mmss = string.split(":");
        if (mmss.length > 1) {
            return Integer.parseInt(mmss[0]) * 60 + Integer.parseInt(mmss[1]);
        }
        return Integer.parseInt(mmss[0]);
    }

    public int getTimeToMine() {
        for (class_1297 entity : MineParser.mc.field_1687.method_18112()) {
            int id;
            class_1297 nextArmorEntity;
            class_1531 armorStandEntity;
            String string;
            if (!(entity instanceof class_1531) || !(string = (armorStandEntity = (class_1531)entity).method_5477().getString()).contains("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0435\u0440\u0435\u0437") || (nextArmorEntity = MineParser.mc.field_1687.method_8469((id = armorStandEntity.method_5628()) + 1)) == null) continue;
            String duration = nextArmorEntity.method_5477().getString().replaceAll("[^0-9:]", "");
            return MineParser.formatMMSS(duration);
        }
        return 0;
    }

    public CompletableFuture<Void> sendRequest() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://85.208.139.128:8000/api/mines")).timeout(Duration.ofSeconds(5L)).GET().build();
        return ((CompletableFuture)HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
            if (response.statusCode() == 200) {
                this.readResponse((String)response.body());
            }
        })).exceptionally(e -> null);
    }

    public void readResponse(String json) {
        MiningAnarchyState anarchyState1;
        JsonObject mainObject = (JsonObject)GSON.fromJson(json, JsonObject.class);
        if (!mainObject.has("mines")) {
            return;
        }
        String bestAnarchy = null;
        String bestType = null;
        int bestTime = 0;
        for (JsonElement jsonElement : mainObject.get("mines").getAsJsonArray()) {
            String[] anTypeTime = jsonElement.getAsString().split(" ");
            String anarchyString = anTypeTime[0];
            String typeString = anTypeTime[1];
            String timeString = anTypeTime[2];
            int time = MineParser.formatMMSS(timeString);
            if (!typeString.equals("\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f") || time >= bestTime) continue;
            bestTime = time;
            bestAnarchy = anarchyString;
            bestType = typeString;
        }
        if (bestAnarchy != null && bestType != null && !this.anarchyState.equals(anarchyState1 = new MiningAnarchyState(bestAnarchy, this.getTypeFromString(bestType), bestTime))) {
            this.anarchyState = anarchyState1;
        }
    }

    private MineType getTypeFromString(String string) {
        if (string == null) {
            return null;
        }
        if (string.equals("\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f")) {
            return MineType.LEGENDARY;
        }
        if (string.equals("\u041c\u0438\u0444\u0438\u0447\u0435\u0441\u043a\u0430\u044f")) {
            return MineType.MYTHICAL;
        }
        return null;
    }

    public MiningAnarchyState getAnarchyState() {
        return this.anarchyState;
    }
}
