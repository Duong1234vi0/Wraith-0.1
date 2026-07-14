/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.V_4423_d;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import mods.voicechat.debug.VoicechatUncaughtExceptionHandler;
import mods.voicechat.gui.GroupType;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.voice.client.AudioChannel;
import mods.voicechat.voice.client.ClientGroupManager;
import mods.voicechat.voice.client.ClientManager;
import mods.voicechat.voice.client.ClientPlayerStateManager;
import mods.voicechat.voice.client.ClientVoicechat;
import mods.voicechat.voice.client.speaker.ALSpeaker;
import mods.voicechat.voice.client.speaker.Speaker;
import mods.voicechat.voice.common.ClientGroup;

public class DebugOverlay {
    private static final MinecraftClient mc = MinecraftClient.A_4115_X();
    private final Map<UUID, AudioChannelInfo> audioChannelInfoMap;
    private boolean active;
    @Nullable
    private TimerThread timer;
    private List<String> rightText = new ArrayList<String>();
    public static final int MAX_AUDIO_CHANNELS = 4;
    private static final int LEFT_PADDING = 5;

    public DebugOverlay() {
        this.audioChannelInfoMap = new LinkedHashMap<UUID, AudioChannelInfo>();
        ClientCompatibilityManager.INSTANCE.onRenderHUD(this::render);
    }

    public void toggle() {
        boolean bl = this.active = !this.active;
        if (this.active) {
            this.audioChannelInfoMap.clear();
            this.timer = new TimerThread();
        } else {
            if (this.timer != null) {
                this.timer.close();
            }
            this.audioChannelInfoMap.clear();
        }
    }

    private void render(g_221_o stack, float tickDelta) {
        if (!this.active) {
            return;
        }
        this.rightText.clear();
        this.rightText.add(String.format("%s %s debug overlay", CommonCompatibilityManager.INSTANCE.getModName(), CommonCompatibilityManager.INSTANCE.getModVersion()));
        this.rightText.add(String.format("Press ALT + %s to toggle", ClientCompatibilityManager.INSTANCE.getBoundKeyOf(V_4423_d.ValueObject).G_564_y().getString()));
        this.rightText.add(null);
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            this.rightText.add("Voice chat not running");
            this.drawRight(stack, this.rightText);
            return;
        }
        this.rightText.add(String.format("UUID: %s", ClientManager.getPlayerStateManager().getOwnID()));
        this.rightText.add(null);
        this.addStateStrings(this.rightText);
        this.rightText.add(null);
        this.addAudioChannelStrings(this.rightText);
        this.drawRight(stack, this.rightText);
    }

    private void addAudioChannelStrings(List<String> strings) {
        strings.add(String.format("Audio Channels: %s", this.audioChannelInfoMap.size()));
        ArrayList<Map.Entry<UUID, AudioChannelInfo>> entries = new ArrayList<Map.Entry<UUID, AudioChannelInfo>>(this.audioChannelInfoMap.entrySet());
        for (int i = 0; i < entries.size() && i < 4; ++i) {
            Map.Entry<UUID, AudioChannelInfo> entry = entries.get(i);
            AudioChannelInfo audioChannel = entry.getValue();
            if (audioChannel.audioBufferCount < 0) {
                strings.add(String.format("ID: %s Packets: %s Reordering: %S Lost: %s Queue: STOPPED", entry.getKey().toString().substring(24), audioChannel.bufferedPackets, audioChannel.packetReorderingBuffer, audioChannel.lostPackets));
                continue;
            }
            strings.add(String.format("ID: %s Packets: %s Reordering: %S Lost: %s Queue: %s/%s", entry.getKey().toString().substring(24), audioChannel.bufferedPackets, audioChannel.packetReorderingBuffer, audioChannel.lostPackets, audioChannel.audioBufferCount, audioChannel.audioBufferSize));
        }
        if (entries.size() > 4) {
            strings.add(String.format("%s more channels", entries.size() - 4));
        }
    }

    private void addStateStrings(List<String> strings) {
        ClientGroupManager groupManager = ClientManager.getGroupManager();
        Collection<ClientGroup> groups = groupManager.getGroups();
        ClientPlayerStateManager stateManager = ClientManager.getPlayerStateManager();
        ClientGroup group = stateManager.getGroup();
        strings.add(String.format("Groups: %s", groups.size()));
        strings.add(this.clientGroupToString(group));
        strings.add(String.format("States: %s Disconnected: %s Disabled: %s Muted: %s", stateManager.getPlayerStates(true).size(), stateManager.isDisconnected(), stateManager.isDisabled(), stateManager.isMuted()));
    }

    private String clientGroupToString(ClientGroup group) {
        if (group == null) {
            return "Group: N/A";
        }
        return String.format("Group: %s Name: %s Password: %s Persistent: %s Type: %s", group.getId().toString().substring(24), group.getName(), group.hasPassword(), group.isPersistent(), GroupType.fromType(group.getType()).name());
    }

    private void updateCache() {
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            this.audioChannelInfoMap.clear();
            return;
        }
        Map<UUID, AudioChannel> audioChannels = client.getAudioChannels();
        this.audioChannelInfoMap.values().removeIf(audioChannelInfo -> !audioChannels.containsKey(audioChannelInfo.id));
        for (Map.Entry<UUID, AudioChannel> entry : audioChannels.entrySet()) {
            AudioChannel audioChannel = entry.getValue();
            AudioChannelInfo info = this.audioChannelInfoMap.computeIfAbsent(entry.getKey(), uuid -> new AudioChannelInfo((UUID)entry.getKey()));
            info.update(audioChannel);
        }
    }

    private void drawRight(g_221_o stack, List<String> strings) {
        for (int i = 0; i < strings.size(); ++i) {
            String text = strings.get(i);
            if (text == null || text.isEmpty()) continue;
            stack.n_1700_B();
            int width = DebugOverlay.mc.t_148_a.J_1907_R(text);
            double d = mc.RealmsServerPing().Q_4569_t() - width - 5;
            float f = i;
            Objects.requireNonNull(DebugOverlay.mc.t_148_a);
            stack.n_1700_B(d, (double)(25.0f + f * (9.0f + 1.0f)), 0.0);
            C_2701_A.fill(stack, -1, -1, width, DebugOverlay.mc.t_148_a.n_1700_B, -1873784752);
            DebugOverlay.mc.t_148_a.J_1907_R(stack, text, 0.0f, 0.0f, 0xFFFFFF);
            stack.J_1907_R();
        }
    }

    private class TimerThread
    extends Thread {
        private boolean stopped;

        private TimerThread() {
            this.setName("Voicechat Debug Overlay Thread");
            this.setDaemon(true);
            this.setUncaughtExceptionHandler(new VoicechatUncaughtExceptionHandler());
            this.start();
        }

        @Override
        public void run() {
            while (!this.stopped) {
                DebugOverlay.this.updateCache();
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException interruptedException) {}
            }
        }

        public void close() {
            this.stopped = true;
            this.interrupt();
        }
    }

    private static class AudioChannelInfo {
        private final UUID id;
        private int audioBufferSize;
        private int audioBufferCount;
        private int bufferedPackets;
        private int packetReorderingBuffer;
        private long lostPackets;

        public AudioChannelInfo(UUID id) {
            this.id = id;
        }

        public AudioChannelInfo update(AudioChannel audioChannel) {
            this.audioBufferSize = 32;
            this.audioBufferCount = -1;
            this.bufferedPackets = audioChannel.getQueue().size();
            this.packetReorderingBuffer = audioChannel.getPacketBuffer().getSize();
            this.lostPackets = audioChannel.getLostPackets();
            Speaker speaker = audioChannel.getSpeaker();
            if (speaker instanceof ALSpeaker) {
                ((ALSpeaker)speaker).fetchQueuedBuffersAsync(bufferCount -> {
                    this.audioBufferCount = bufferCount;
                });
            }
            return this;
        }
    }
}



