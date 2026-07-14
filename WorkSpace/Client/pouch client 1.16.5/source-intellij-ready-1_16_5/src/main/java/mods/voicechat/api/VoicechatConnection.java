/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api;

import javax.annotation.Nullable;
import mods.voicechat.api.Group;
import mods.voicechat.api.ServerPlayer;

public interface VoicechatConnection {
    @Nullable
    public Group getGroup();

    public boolean isInGroup();

    public void setGroup(@Nullable Group var1);

    public boolean isConnected();

    public void setConnected(boolean var1);

    public boolean isDisabled();

    public void setDisabled(boolean var1);

    public boolean isInstalled();

    public ServerPlayer getPlayer();
}

