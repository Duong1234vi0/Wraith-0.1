/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import java.util.UUID;
import mods.voicechat.api.Position;

public interface Entity {
    public UUID getUuid();

    public Object getEntity();

    public Position getPosition();
}

