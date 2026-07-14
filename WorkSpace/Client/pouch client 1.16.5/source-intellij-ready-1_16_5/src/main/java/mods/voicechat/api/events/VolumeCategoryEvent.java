/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.events.ServerEvent;

public interface VolumeCategoryEvent
extends ServerEvent {
    public VolumeCategory getVolumeCategory();
}

