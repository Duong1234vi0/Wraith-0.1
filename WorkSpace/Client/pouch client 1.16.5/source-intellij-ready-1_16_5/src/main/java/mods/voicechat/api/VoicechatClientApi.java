/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.Group;
import mods.voicechat.api.Position;
import mods.voicechat.api.VoicechatApi;
import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.audiochannel.ClientEntityAudioChannel;
import mods.voicechat.api.audiochannel.ClientLocationalAudioChannel;
import mods.voicechat.api.audiochannel.ClientStaticAudioChannel;
import mods.voicechat.api.config.ConfigAccessor;

public interface VoicechatClientApi
extends VoicechatApi {
    public boolean isMuted();

    public boolean isDisabled();

    public boolean isDisconnected();

    @Nullable
    public Group getGroup();

    public ClientEntityAudioChannel createEntityAudioChannel(UUID var1);

    public ClientLocationalAudioChannel createLocationalAudioChannel(UUID var1, Position var2);

    public ClientStaticAudioChannel createStaticAudioChannel(UUID var1);

    public void registerClientVolumeCategory(VolumeCategory var1);

    default public void unregisterClientVolumeCategory(VolumeCategory category) {
        this.unregisterClientVolumeCategory(category.getId());
    }

    public void unregisterClientVolumeCategory(String var1);

    public ConfigAccessor getClientConfig();
}

