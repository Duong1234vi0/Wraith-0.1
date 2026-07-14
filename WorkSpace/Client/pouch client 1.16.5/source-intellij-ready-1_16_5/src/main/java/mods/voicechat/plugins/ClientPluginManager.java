/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.e_2866_D;
import mods.voicechat.Voicechat;
import mods.voicechat.api.ClientVoicechatSocket;
import mods.voicechat.api.events.ClientReceiveSoundEvent;
import mods.voicechat.api.events.ClientSoundEvent;
import mods.voicechat.api.events.ClientVoicechatInitializationEvent;
import mods.voicechat.api.events.CreateOpenALContextEvent;
import mods.voicechat.api.events.DestroyOpenALContextEvent;
import mods.voicechat.api.events.MergeClientSoundEvent;
import mods.voicechat.api.events.OpenALSoundEvent;
import mods.voicechat.plugins.PluginManager;
import mods.voicechat.plugins.impl.ClientVoicechatSocketImpl;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.events.ClientReceiveSoundEventImpl;
import mods.voicechat.plugins.impl.events.ClientSoundEventImpl;
import mods.voicechat.plugins.impl.events.ClientVoicechatInitializationEventImpl;
import mods.voicechat.plugins.impl.events.CreateOpenALContextEventImpl;
import mods.voicechat.plugins.impl.events.DestroyOpenALContextEventImpl;
import mods.voicechat.plugins.impl.events.MergeClientSoundEventImpl;
import mods.voicechat.plugins.impl.events.OpenALSoundEventImpl;
import mods.voicechat.voice.common.Utils;

public class ClientPluginManager {
    private final PluginManager pluginManager;
    private static ClientPluginManager instance;

    public ClientPluginManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public ClientVoicechatSocket getClientSocketImplementation() {
        ClientVoicechatInitializationEventImpl event = new ClientVoicechatInitializationEventImpl();
        this.pluginManager.dispatchEvent(ClientVoicechatInitializationEvent.class, event);
        ClientVoicechatSocket socket = event.getSocketImplementation();
        if (socket == null) {
            socket = new ClientVoicechatSocketImpl();
            Voicechat.LOGGER.debug("Using default voicechat client socket implementation", new Object[0]);
        } else {
            Voicechat.LOGGER.info("Using custom voicechat client socket implementation: {}", socket.getClass().getName());
        }
        return socket;
    }

    @Nullable
    public short[] onMergeClientSound(@Nullable short[] rawAudio) {
        MergeClientSoundEventImpl event = new MergeClientSoundEventImpl();
        this.pluginManager.dispatchEvent(MergeClientSoundEvent.class, event);
        List<short[]> audioToMerge = event.getAudioToMerge();
        if (audioToMerge == null) {
            return rawAudio;
        }
        if (rawAudio != null) {
            audioToMerge.add(0, rawAudio);
        }
        return Utils.combineAudio(audioToMerge);
    }

    @Nullable
    public short[] onClientSound(short[] rawAudio, boolean whispering) {
        ClientSoundEventImpl clientSoundEvent = new ClientSoundEventImpl(rawAudio, whispering);
        boolean cancelled = this.pluginManager.dispatchEvent(ClientSoundEvent.class, clientSoundEvent);
        if (cancelled) {
            return null;
        }
        return clientSoundEvent.getRawAudio();
    }

    public short[] onReceiveEntityClientSound(UUID id, short[] rawAudio, boolean whispering, float distance) {
        ClientReceiveSoundEventImpl.EntitySoundImpl clientSoundEvent = new ClientReceiveSoundEventImpl.EntitySoundImpl(id, rawAudio, whispering, distance);
        this.pluginManager.dispatchEvent(ClientReceiveSoundEvent.EntitySound.class, clientSoundEvent);
        return clientSoundEvent.getRawAudio();
    }

    public short[] onReceiveLocationalClientSound(UUID id, short[] rawAudio, e_2866_D pos, float distance) {
        ClientReceiveSoundEventImpl.LocationalSoundImpl clientSoundEvent = new ClientReceiveSoundEventImpl.LocationalSoundImpl(id, rawAudio, new PositionImpl(pos), distance);
        this.pluginManager.dispatchEvent(ClientReceiveSoundEvent.LocationalSound.class, clientSoundEvent);
        return clientSoundEvent.getRawAudio();
    }

    public short[] onReceiveStaticClientSound(UUID id, short[] rawAudio) {
        ClientReceiveSoundEventImpl.StaticSoundImpl clientSoundEvent = new ClientReceiveSoundEventImpl.StaticSoundImpl(id, rawAudio);
        this.pluginManager.dispatchEvent(ClientReceiveSoundEvent.StaticSound.class, clientSoundEvent);
        return clientSoundEvent.getRawAudio();
    }

    public void onALSound(int source, @Nullable UUID channelId, @Nullable e_2866_D pos, @Nullable String category, Class<? extends OpenALSoundEvent> eventClass) {
        this.pluginManager.dispatchEvent(eventClass, new OpenALSoundEventImpl(channelId, pos == null ? null : new PositionImpl(pos), category, source));
    }

    public void onCreateALContext(long context, long device) {
        this.pluginManager.dispatchEvent(CreateOpenALContextEvent.class, new CreateOpenALContextEventImpl(context, device));
    }

    public void onDestroyALContext(long context, long device) {
        this.pluginManager.dispatchEvent(DestroyOpenALContextEvent.class, new DestroyOpenALContextEventImpl(context, device));
    }

    public static ClientPluginManager instance() {
        if (instance == null) {
            instance = new ClientPluginManager(PluginManager.instance());
        }
        return instance;
    }
}

