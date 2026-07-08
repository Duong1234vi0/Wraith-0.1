/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package code.alphadlc.features.impl.render.custommodels;

import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.render.CustomModelModule;
import code.alphadlc.main.listener.Listener;
import code.alphadlc.utils.client.Instance;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.display.interfaces.QuickImports;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class CustomModelSyncManager
implements Listener,
QuickImports {
    private static final String CUSTOM_MODEL_MARKER = "__custom_model__";
    private static final String CUSTOM_MODEL_PREFIX = "__custom_model__:";
    private static final long BROADCAST_INTERVAL_MS = 5000L;
    private static final long STATE_TIMEOUT_MS = 35000L;
    private static CustomModelSyncManager instance;
    private final Map<UUID, TimedRemoteModelState> remoteStates = new ConcurrentHashMap<UUID, TimedRemoteModelState>();
    private long nextBroadcastMs;
    private boolean wasConnected;
    private String lastServerId = "";
    private LocalModelSnapshot lastSentSnapshot;
    private final Consumer<JsonObject> relayListener = this::handleRelayPacket;

    public static CustomModelSyncManager create() {
        instance = new CustomModelSyncManager();
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        if (manager != null) {
            manager.addRelayListener(CustomModelSyncManager.instance.relayListener);
        }
        return instance;
    }

    public static CustomModelSyncManager getInstance() {
        return instance;
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (CustomModelSyncManager.mc.field_1724 == null || CustomModelSyncManager.mc.field_1687 == null) {
            this.clearState();
            this.wasConnected = false;
            return;
        }
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        if (manager == null) {
            this.clearState();
            this.wasConnected = false;
            return;
        }
        manager.addRelayListener(this.relayListener);
        String currentServerId = manager.getDebugServerId();
        if (!Objects.equals(this.lastServerId, currentServerId)) {
            this.remoteStates.clear();
            this.lastServerId = currentServerId;
            this.nextBroadcastMs = 0L;
            this.lastSentSnapshot = null;
        }
        this.pruneRemoteStates();
        this.broadcastLocalStateIfNeeded(manager);
    }

    @Nullable
    public RemoteModelState getRemoteState(UUID uuid) {
        TimedRemoteModelState state = this.remoteStates.get(uuid);
        if (state == null) {
            return null;
        }
        return new RemoteModelState(state.enabled(), state.modelType());
    }

    public boolean hasRemoteState(UUID uuid) {
        return this.remoteStates.containsKey(uuid);
    }

    private void broadcastLocalStateIfNeeded(EmotionWheelManager manager) {
        boolean intervalElapsed;
        boolean connected = manager.isSyncConnected();
        LocalModelSnapshot snapshot = this.captureLocalSnapshot();
        long now = System.currentTimeMillis();
        if (!connected) {
            this.wasConnected = false;
            return;
        }
        boolean changed = !snapshot.equals(this.lastSentSnapshot);
        boolean reconnected = !this.wasConnected;
        boolean bl = intervalElapsed = now >= this.nextBroadcastMs;
        if (!(changed || reconnected || intervalElapsed)) {
            this.wasConnected = true;
            return;
        }
        boolean sent = manager.sendRelayPacket(this.encodeSnapshot(snapshot), now, json -> {
            json.addProperty("customModelEnabled", Boolean.valueOf(snapshot.enabled()));
            json.addProperty("customModelType", snapshot.modelType());
        });
        if (!sent) {
            this.wasConnected = false;
            return;
        }
        this.lastSentSnapshot = snapshot;
        this.nextBroadcastMs = now + 5000L;
        this.wasConnected = true;
    }

    private LocalModelSnapshot captureLocalSnapshot() {
        CustomModelModule module = Instance.get(CustomModelModule.class);
        if (module == null || !module.isState()) {
            return new LocalModelSnapshot(false, "");
        }
        return new LocalModelSnapshot(true, module.models.getSelected());
    }

    private void pruneRemoteStates() {
        long cutoff = System.currentTimeMillis() - 35000L;
        this.remoteStates.entrySet().removeIf(entry -> ((TimedRemoteModelState)entry.getValue()).updatedAt() < cutoff);
    }

    private void clearState() {
        this.remoteStates.clear();
        this.lastServerId = "";
        this.nextBroadcastMs = 0L;
        this.lastSentSnapshot = null;
    }

    private void handleRelayPacket(JsonObject json) {
        try {
            if (!json.has("emotion")) {
                return;
            }
            String emotionValue = json.get("emotion").getAsString();
            DecodedSnapshot decodedSnapshot = this.decodeSnapshot(emotionValue, json);
            if (decodedSnapshot == null) {
                return;
            }
            if (!json.has("playerUuid")) {
                return;
            }
            UUID uuid = UUID.fromString(json.get("playerUuid").getAsString());
            if (CustomModelSyncManager.mc.field_1724 != null && CustomModelSyncManager.mc.field_1724.method_5667().equals(uuid)) {
                return;
            }
            this.remoteStates.put(uuid, new TimedRemoteModelState(decodedSnapshot.enabled(), decodedSnapshot.modelType(), System.currentTimeMillis()));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String encodeSnapshot(LocalModelSnapshot snapshot) {
        JsonObject payload = new JsonObject();
        payload.addProperty("enabled", Boolean.valueOf(snapshot.enabled()));
        payload.addProperty("modelType", snapshot.modelType());
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(gson.toJson((JsonElement)payload).getBytes(StandardCharsets.UTF_8));
        return CUSTOM_MODEL_PREFIX + encoded;
    }

    @Nullable
    private DecodedSnapshot decodeSnapshot(String emotionValue, JsonObject json) {
        if (emotionValue.startsWith(CUSTOM_MODEL_PREFIX)) {
            try {
                String encoded = emotionValue.substring(CUSTOM_MODEL_PREFIX.length());
                String decodedPayload = new String(Base64.getUrlDecoder().decode(encoded), StandardCharsets.UTF_8);
                JsonObject payload = JsonParser.parseString((String)decodedPayload).getAsJsonObject();
                boolean enabled = payload.has("enabled") && payload.get("enabled").getAsBoolean();
                String modelType = payload.has("modelType") ? payload.get("modelType").getAsString() : "";
                return new DecodedSnapshot(enabled, modelType);
            }
            catch (Exception ignored) {
                return null;
            }
        }
        if (!CUSTOM_MODEL_MARKER.equals(emotionValue)) {
            return null;
        }
        boolean enabled = json.has("customModelEnabled") && json.get("customModelEnabled").getAsBoolean();
        String modelType = json.has("customModelType") ? json.get("customModelType").getAsString() : "";
        return new DecodedSnapshot(enabled, modelType);
    }

    private record LocalModelSnapshot(boolean enabled, String modelType) {
    }

    private record TimedRemoteModelState(boolean enabled, String modelType, long updatedAt) {
    }

    public record RemoteModelState(boolean enabled, String modelType) {
    }

    private record DecodedSnapshot(boolean enabled, String modelType) {
    }
}
