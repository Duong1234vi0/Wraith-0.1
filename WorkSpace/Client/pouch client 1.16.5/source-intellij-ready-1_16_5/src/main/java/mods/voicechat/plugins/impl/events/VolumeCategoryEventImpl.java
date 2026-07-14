/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.events.VolumeCategoryEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class VolumeCategoryEventImpl
extends ServerEventImpl
implements VolumeCategoryEvent {
    private final VolumeCategory category;

    public VolumeCategoryEventImpl(VolumeCategory category) {
        this.category = category;
    }

    @Override
    public VolumeCategory getVolumeCategory() {
        return this.category;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}

