/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.emotions.EmotionWheelManager;
import code.alphadlc.events.player.TickEvent;
import code.alphadlc.features.impl.misc.Globals;
import code.alphadlc.features.impl.render.ItemReplacer;
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

public class ItemReplacerSyncManager
implements Listener,
QuickImports {
    private static final String SWORD_MODEL_PREFIX = "__sword_model__:";
    private static final long BROADCAST_INTERVAL_MS = 5000L;
    private static final long STATE_TIMEOUT_MS = 35000L;
    private static ItemReplacerSyncManager instance;
    private final Map<UUID, TimedRemoteSwordState> remoteStates = new ConcurrentHashMap<UUID, TimedRemoteSwordState>();
    private long nextBroadcastMs;
    private boolean wasConnected;
    private String lastServerId = "";
    private LocalSwordSnapshot lastSentSnapshot;
    private final Consumer<JsonObject> relayListener = this::handleRelayPacket;

    public static ItemReplacerSyncManager create() {
        instance = new ItemReplacerSyncManager();
        EmotionWheelManager manager = EmotionWheelManager.getInstance();
        if (manager != null) {
            manager.addRelayListener(ItemReplacerSyncManager.instance.relayListener);
        }
        return instance;
    }

    public static ItemReplacerSyncManager getInstance() {
        return instance;
    }

    @Nullable
    public String getRemoteModelLabel(UUID uuid) {
        TimedRemoteSwordState state = this.remoteStates.get(uuid);
        if (state == null || !state.enabled()) {
            return null;
        }
        return state.modelLabel();
    }

    public boolean hasRemoteState(UUID uuid) {
        TimedRemoteSwordState state = this.remoteStates.get(uuid);
        return state != null && state.enabled();
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (!Globals.isItemReplacerSyncEnabled()) {
            this.clearState();
            this.wasConnected = false;
            return;
        }
        if (ItemReplacerSyncManager.mc.field_1724 == null || ItemReplacerSyncManager.mc.field_1687 == null) {
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

    private void broadcastLocalStateIfNeeded(EmotionWheelManager manager) {
        boolean intervalElapsed;
        boolean connected = manager.isSyncConnected();
        LocalSwordSnapshot snapshot = this.captureLocalSnapshot();
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
            json.addProperty("swordReplacerEnabled", Boolean.valueOf(snapshot.enabled()));
            json.addProperty("swordModelLabel", snapshot.modelLabel());
        });
        if (!sent) {
            this.wasConnected = false;
            return;
        }
        this.lastSentSnapshot = snapshot;
        this.nextBroadcastMs = now + 5000L;
        this.wasConnected = true;
    }

    private LocalSwordSnapshot captureLocalSnapshot() {
        ItemReplacer replacer = Instance.get(ItemReplacer.class);
        if (replacer == null || !replacer.isState()) {
            return new LocalSwordSnapshot(false, "");
        }
        return new LocalSwordSnapshot(true, replacer.getSelectedVariant().label);
    }

    private void pruneRemoteStates() {
        long cutoff = System.currentTimeMillis() - 35000L;
        this.remoteStates.entrySet().removeIf(entry -> ((TimedRemoteSwordState)entry.getValue()).updatedAt() < cutoff);
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
            if (ItemReplacerSyncManager.mc.field_1724 != null && ItemReplacerSyncManager.mc.field_1724.method_5667().equals(uuid)) {
                return;
            }
            this.remoteStates.put(uuid, new TimedRemoteSwordState(decodedSnapshot.enabled(), decodedSnapshot.modelLabel(), System.currentTimeMillis()));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String encodeSnapshot(LocalSwordSnapshot snapshot) {
        JsonObject payload = new JsonObject();
        payload.addProperty("enabled", Boolean.valueOf(snapshot.enabled()));
        payload.addProperty("modelLabel", snapshot.modelLabel());
        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(gson.toJson((JsonElement)payload).getBytes(StandardCharsets.UTF_8));
        return SWORD_MODEL_PREFIX + encoded;
    }

    @Nullable
    private DecodedSnapshot decodeSnapshot(String emotionValue, JsonObject json) {
        if (emotionValue.startsWith(SWORD_MODEL_PREFIX)) {
            try {
                String encoded = emotionValue.substring(SWORD_MODEL_PREFIX.length());
                String decodedPayload = new String(Base64.getUrlDecoder().decode(encoded), StandardCharsets.UTF_8);
                JsonObject payload = JsonParser.parseString((String)decodedPayload).getAsJsonObject();
                boolean enabled = payload.has("enabled") && payload.get("enabled").getAsBoolean();
                String modelLabel = payload.has("modelLabel") ? payload.get("modelLabel").getAsString() : "";
                return new DecodedSnapshot(enabled, modelLabel);
            }
            catch (Exception ignored) {
                return null;
            }
        }
        if (!emotionValue.startsWith("__sword_model__")) {
            return null;
        }
        boolean enabled = json.has("swordReplacerEnabled") && json.get("swordReplacerEnabled").getAsBoolean();
        String modelLabel = json.has("swordModelLabel") ? json.get("swordModelLabel").getAsString() : "";
        return new DecodedSnapshot(enabled, modelLabel);
    }

    private record TimedRemoteSwordState(boolean enabled, String modelLabel, long updatedAt) {
    }

    private record LocalSwordSnapshot(boolean enabled, String modelLabel) {
    }

    private record DecodedSnapshot(boolean enabled, String modelLabel) {
    }
}
