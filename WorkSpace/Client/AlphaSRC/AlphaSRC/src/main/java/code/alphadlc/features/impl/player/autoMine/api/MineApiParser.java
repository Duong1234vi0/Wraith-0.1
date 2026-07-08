/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package code.alphadlc.features.impl.player.autoMine.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MineApiParser {
    static final String API_URL = "http://85.208.139.128:8000/api/mines";
    static final Gson GSON = new Gson();
    static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
    static final Pattern MINE_PATTERN = Pattern.compile("(/an\\d+)\\s+(\\S+)\\s+(\\d{2}):(\\d{2})");
    static final String LEGENDARY_TYPE = "\u041b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u0430\u044f";
    private String bestAnarchyCommand = null;
    private String bestMineType = null;
    private int bestTimeSeconds = -1;
    private boolean found = false;
    private long lastFetchTime = 0L;
    static final long FETCH_INTERVAL_MS = 2000L;

    public CompletableFuture<Void> fetchAsync() {
        if (System.currentTimeMillis() - this.lastFetchTime < 2000L) {
            return CompletableFuture.completedFuture(null);
        }
        this.lastFetchTime = System.currentTimeMillis();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).timeout(Duration.ofSeconds(5L)).GET().build();
        return ((CompletableFuture)HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
            if (response.statusCode() == 200) {
                this.parseResponse((String)response.body());
            }
        })).exceptionally(e -> null);
    }

    public void fetchSync() {
        if (System.currentTimeMillis() - this.lastFetchTime < 2000L) {
            return;
        }
        this.lastFetchTime = System.currentTimeMillis();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).timeout(Duration.ofSeconds(5L)).GET().build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                this.parseResponse(response.body());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void parseResponse(String json) {
        try {
            JsonObject obj = (JsonObject)GSON.fromJson(json, JsonObject.class);
            if (!obj.has("mines")) {
                this.found = false;
                return;
            }
            JsonArray mines = obj.getAsJsonArray("mines");
            String bestCmd = null;
            String bestType = null;
            int bestTime = Integer.MAX_VALUE;
            for (JsonElement element : mines) {
                int seconds;
                String mineStr = element.getAsString();
                Matcher matcher = MINE_PATTERN.matcher(mineStr);
                if (!matcher.find()) continue;
                String cmd = matcher.group(1);
                String type = matcher.group(2);
                int minutes = Integer.parseInt(matcher.group(3));
                int totalSeconds = minutes * 60 + (seconds = Integer.parseInt(matcher.group(4)));
                if (totalSeconds >= bestTime) continue;
                bestCmd = cmd;
                bestType = type;
                bestTime = totalSeconds;
            }
            if (bestCmd != null) {
                this.bestAnarchyCommand = bestCmd;
                this.bestMineType = bestType;
                this.bestTimeSeconds = bestTime;
                this.found = true;
            } else {
                this.found = false;
                this.bestAnarchyCommand = null;
                this.bestMineType = null;
                this.bestTimeSeconds = -1;
            }
        }
        catch (Exception e) {
            this.found = false;
        }
    }

    public boolean isMineReady(int thresholdSeconds) {
        return this.found && this.bestTimeSeconds >= 0 && this.bestTimeSeconds < thresholdSeconds;
    }

    public String getAnarchyCommand() {
        return this.bestAnarchyCommand;
    }

    public String getMineType() {
        return this.bestMineType;
    }

    public int getTimeSeconds() {
        return this.bestTimeSeconds;
    }

    public String getFormattedTime() {
        if (this.bestTimeSeconds < 0) {
            return "--:--";
        }
        int min = this.bestTimeSeconds / 60;
        int sec = this.bestTimeSeconds % 60;
        return String.format("%02d:%02d", min, sec);
    }

    public boolean isFound() {
        return this.found;
    }

    public void reset() {
        this.bestAnarchyCommand = null;
        this.bestMineType = null;
        this.bestTimeSeconds = -1;
        this.found = false;
    }

    public String getBestAnarchyCommand() {
        return this.bestAnarchyCommand;
    }

    public String getBestMineType() {
        return this.bestMineType;
    }

    public int getBestTimeSeconds() {
        return this.bestTimeSeconds;
    }

    public long getLastFetchTime() {
        return this.lastFetchTime;
    }
}
