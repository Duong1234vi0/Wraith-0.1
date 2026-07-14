package ua.babka.util.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import net.minecraft.util.Formatting;
import ua.babka.util.ChatUtil;
import ua.babka.util.IMinecraft;
import ua.babka.util.network.PartyPlayerPos;

public final class PartyApiClient
implements IMinecraft {
    private static final HttpClient HTTP = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
    private static final String BASE_URL = "http://bybybybyvich.pythonanywhere.com";
    private static final ExecutorService PARTY_EXECUTOR = Executors.newSingleThreadExecutor(r2 -> {
        Thread t2 = new Thread(r2, "Party-API-Thread");
        t2.setDaemon(true);
        return t2;
    });
    private static volatile List<PartyPlayerPos> cached = List.of();

    public static void postAsync(String path, JsonObject body, Consumer<JsonObject> callback) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + path)).POST(HttpRequest.BodyPublishers.ofString(body.toString())).header("Content-Type", "application/json").build();
        ((CompletableFuture)((CompletableFuture)HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::body, (Executor)PARTY_EXECUTOR)).thenAcceptAsync(text -> {
            try {
                if (!text.startsWith("{")) {
                    return;
                }
                JsonObject json = JsonParser.parseString((String)text).getAsJsonObject();
                mc.execute(() -> callback.accept(json));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }, (Executor)PARTY_EXECUTOR)).exceptionally(e2 -> {
            e2.printStackTrace();
            return null;
        });
    }

    public static void fetchPartyStateAsync() {
        if (PartyApiClient.mc.player == null) {
            return;
        }
        JsonObject req = new JsonObject();
        req.addProperty("player", PartyApiClient.mc.player.getNameForScoreboard());
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://bybybybyvich.pythonanywhere.com/party/state")).POST(HttpRequest.BodyPublishers.ofString(req.toString())).header("Content-Type", "application/json").build();
        HTTP.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAcceptAsync(response -> {
            try {
                if (response.statusCode() != 200) {
                    return;
                }
                String body = ((String)response.body()).trim();
                if (!body.startsWith("{")) {
                    return;
                }
                JsonObject json = JsonParser.parseString((String)body).getAsJsonObject();
                JsonArray arr = json.getAsJsonArray("members");
                ArrayList<PartyPlayerPos> list = new ArrayList<PartyPlayerPos>();
                for (JsonElement e2 : arr) {
                    JsonObject o2 = e2.getAsJsonObject();
                    list.add(new PartyPlayerPos(o2.get("playerId").getAsString(), o2.get("x").getAsDouble(), o2.get("y").getAsDouble(), o2.get("z").getAsDouble()));
                }
                cached = list;
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    public static void fetchInvitesAsync() {
        if (PartyApiClient.mc.player == null) {
            return;
        }
        JsonObject req = new JsonObject();
        req.addProperty("player", PartyApiClient.mc.player.getNameForScoreboard());
        PartyApiClient.postAsync("/party/invites", req, json -> {
            JsonArray arr = json.getAsJsonArray("invites");
            for (JsonElement e2 : arr) {
                String party = e2.getAsString();
                ChatUtil.send((Object)(String.valueOf(Formatting.GRAY) + "Вас пригласили в пати " + String.valueOf(Formatting.WHITE) + party + String.valueOf(Formatting.GRAY) + ", напишите " + String.valueOf(Formatting.WHITE) + ".party join " + party));
            }
        });
    }

    public static List<PartyPlayerPos> getCached() {
        return cached;
    }
}

