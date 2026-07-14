/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import lightning.product.N_4263_v;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientVoicechat;

public class TalkCache {
    private static final long TIMEOUT = 250L;
    private static final Talk DEFAULT = new Talk(0L, false);
    private final Map<UUID, Talk> cache = new HashMap<UUID, Talk>();
    private Supplier<Long> timestampSupplier = System::currentTimeMillis;

    public void setTimestampSupplier(Supplier<Long> timestampSupplier) {
        this.timestampSupplier = timestampSupplier;
    }

    public void updateTalking(UUID entity, boolean whispering) {
        Talk talk = this.cache.get(entity);
        if (talk == null) {
            talk = new Talk(this.timestampSupplier.get(), whispering);
            this.cache.put(entity, talk);
        } else {
            talk.timestamp = this.timestampSupplier.get();
            talk.whispering = whispering;
        }
    }

    public boolean isTalking(N_4263_v entity) {
        return this.isTalking(entity.w_2705_t());
    }

    public boolean isWhispering(N_4263_v entity) {
        return this.isWhispering(entity.w_2705_t());
    }

    public boolean isTalking(UUID entity) {
        ClientVoicechat client;
        if (entity.equals(ClientManager.getPlayerStateManager().getOwnID()) && (client = ClientManager.getClient()) != null && client.getMicThread() != null && client.getMicThread().isTalking()) {
            return true;
        }
        Talk lastTalk = this.cache.getOrDefault(entity, DEFAULT);
        return this.timestampSupplier.get() - lastTalk.timestamp < 250L;
    }

    public boolean isWhispering(UUID entity) {
        ClientVoicechat client;
        if (entity.equals(ClientManager.getPlayerStateManager().getOwnID()) && (client = ClientManager.getClient()) != null && client.getMicThread() != null && client.getMicThread().isWhispering()) {
            return true;
        }
        Talk lastTalk = this.cache.getOrDefault(entity, DEFAULT);
        return lastTalk.whispering && this.timestampSupplier.get() - lastTalk.timestamp < 250L;
    }

    private static class Talk {
        private long timestamp;
        private boolean whispering;

        public Talk(long timestamp, boolean whispering) {
            this.timestamp = timestamp;
            this.whispering = whispering;
        }
    }
}

