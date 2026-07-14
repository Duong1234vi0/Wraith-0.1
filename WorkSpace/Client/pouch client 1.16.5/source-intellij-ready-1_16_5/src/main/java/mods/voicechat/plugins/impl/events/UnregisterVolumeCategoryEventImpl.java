/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.events.UnregisterVolumeCategoryEvent;
import mods.voicechat.plugins.impl.events.VolumeCategoryEventImpl;

public class UnregisterVolumeCategoryEventImpl
extends VolumeCategoryEventImpl
implements UnregisterVolumeCategoryEvent {
    public UnregisterVolumeCategoryEventImpl(VolumeCategory category) {
        super(category);
    }
}

