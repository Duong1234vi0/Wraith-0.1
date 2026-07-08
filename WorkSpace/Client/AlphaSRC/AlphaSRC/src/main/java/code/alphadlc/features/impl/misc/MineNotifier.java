/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package code.alphadlc.features.impl.misc;

import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.module.Module;
import code.alphadlc.features.module.ModuleCategory;
import code.alphadlc.utils.client.chat.ChatMessage;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.math.time.StopWatch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class MineNotifier
extends Module {
    private static final String API_URL = "http://85.208.139.128:8000/api/mines/nearest";
    private static final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
    private final StopWatch updateTimer = new StopWatch();
    private static final long UPDATE_INTERVAL_MS = 5000L;
    private String lastMineInfo = "";

    public MineNotifier() {
        super("MineNotifier", "Mine Notifier", ModuleCategory.MISC);
        this.updateTimer.reset();
    }

    @Override
    public void activate() {
        super.activate();
        ChatMessage.brandmessage("MineNotifier \u0432\u043a\u043b\u044e\u0447\u0435\u043d. \u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u0430\u0432\u0442\u043e\u0448\u0430\u0445\u0442 \u043a\u0430\u0436\u0434\u044b\u0435 5 \u0441\u0435\u043a\u0443\u043d\u0434...");
        this.lastMineInfo = "";
        this.updateTimer.reset();
        this.fetchNearestMine();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        ChatMessage.brandmessage("MineNotifier \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d.");
    }

    @EventHandler
    public void onTick(TickEvent e) {
        if (MineNotifier.mc.field_1724 == null || MineNotifier.mc.field_1687 == null) {
            return;
        }
        if (!this.updateTimer.finished(5000.0)) {
            return;
        }
        this.updateTimer.reset();
        this.fetchNearestMine();
    }

    private void fetchNearestMine() {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).timeout(Duration.ofSeconds(5L)).GET().build();
            ((CompletableFuture)httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
                if (response.statusCode() == 200) {
                    try {
                        String mineInfo;
                        String responseBody = (String)response.body();
                        JsonObject json = JsonParser.parseString((String)responseBody).getAsJsonObject();
                        boolean found = json.get("found").getAsBoolean();
                        if (found && !(mineInfo = json.get("mine").getAsString()).equals(this.lastMineInfo)) {
                            this.lastMineInfo = mineInfo;
                            mc.execute(() -> ChatMessage.brandmessage(mineInfo));
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            })).exceptionally(e -> null);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}
