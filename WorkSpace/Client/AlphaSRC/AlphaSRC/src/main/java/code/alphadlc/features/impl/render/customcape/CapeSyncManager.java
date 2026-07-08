/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package code.alphadlc.features.impl.render.customcape;

import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.render.CustomCape;
import code.alphadlc.main.listener.Listener;
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

public class CapeSyncManager
implements Listener,
QuickImports {
    private static final String CAPE_MARKER = "__custom_cape__";
    private static final String CAPE_PREFIX = "__custom_cape__:";
    private static final long BROADCAST_INTERVAL_MS = 5000L;
    private static final long STATE_TIMEOUT_MS = 35000L;
    private static CapeSyncManager instance;
    private final Map<UUID, TimedRemoteCapeState> remoteStates = new ConcurrentHashMap<UUID, TimedRemoteCapeState>();
    private long nextBroadcastMs;
    private boolean wasConnected;
    private String lastServerId = "";
    private LocalCapeSnapshot lastSentSnapshot;
    private final Consumer<JsonObject> relayListener = this::handleRelayPacket;

    public static CapeSyncManager create() {
        instance = new CapeSyncManager();
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        if (manager != null) {
            manager.addRelayListener(CapeSyncManager.instance.relayListener);
        }
        return instance;
    }

    public static CapeSyncManager getInstance() {
        return instance;
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (CapeSyncManager.mc.field_1724 == null || CapeSyncManager.mc.field_1687 == null) {
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

    public boolean hasRemoteCape(UUID uuid) {
        TimedRemoteCapeState state = this.remoteStates.get(uuid);
        return state != null && state.enabled();
    }

    private void broadcastLocalStateIfNeeded(EmotionWheelManager manager) {
        boolean intervalElapsed;
        boolean connected = manager.isSyncConnected();
        LocalCapeSnapshot snapshot = this.captureLocalSnapshot();
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
        boolean sent = manager.sendRelayPacket(this.encodeSnapshot(snapshot), now, json -> json.addProperty("customCapeEnabled", Boolean.valueOf(snapshot.enabled())));
        if (!sent) {
            this.wasConnected = false;
            return;
        }
        this.lastSentSnapshot = snapshot;
        this.nextBroadcastMs = now + 5000L;
        this.wasConnected = true;
    }

    private LocalCapeSnapshot captureLocalSnapshot() {
        CustomCape module = CustomCape.getInstance();
        if (module == null || !module.isState()) {
            return new LocalCapeSnapshot(false);
        }
        return new LocalCapeSnapshot(true);
    }

    private void pruneRemoteStates() {
        long cutoff = System.currentTimeMillis() - 35000L;
        this.remoteStates.entrySet().removeIf(entry -> ((TimedRemoteCapeState)entry.getValue()).updatedAt() < cutoff);
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
            Boolean enabled = this.decodeSnapshot(emotionValue, json);
            if (enabled == null) {
                return;
            }
            if (!json.has("playerUuid")) {
                return;
            }
            UUID uuid = UUID.fromString(json.get("playerUuid").getAsString());
            if (CapeSyncManager.mc.field_1724 != null && CapeSyncManager.mc.field_1724.method_5667().equals(uuid)) {
                return;
            }
            this.remoteStates.put(uuid, new TimedRemoteCapeState(enabled, System.currentTimeMillis()));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String encodeSnapshot(LocalCapeSnapshot snapshot) {
        JsonObject payload = new JsonObject();
        payload.addProperty("enabled", Boolean.valueOf(snapshot.enabled()));
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(gson.toJson((JsonElement)payload).getBytes(StandardCharsets.UTF_8));
        return CAPE_PREFIX + encoded;
    }

    @Nullable
    private Boolean decodeSnapshot(String emotionValue, JsonObject json) {
        if (emotionValue.startsWith(CAPE_PREFIX)) {
            try {
                String encoded = emotionValue.substring(CAPE_PREFIX.length());
                String decodedPayload = new String(Base64.getUrlDecoder().decode(encoded), StandardCharsets.UTF_8);
                JsonObject payload = JsonParser.parseString((String)decodedPayload).getAsJsonObject();
                return payload.has("enabled") && payload.get("enabled").getAsBoolean();
            }
            catch (Exception ignored) {
                return null;
            }
        }
        if (!CAPE_MARKER.equals(emotionValue)) {
            return null;
        }
        return json.has("customCapeEnabled") && json.get("customCapeEnabled").getAsBoolean();
    }

    private record LocalCapeSnapshot(boolean enabled) {
    }

    private record TimedRemoteCapeState(boolean enabled, long updatedAt) {
    }
}
