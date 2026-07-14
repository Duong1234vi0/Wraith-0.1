/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VolumeCategory;
import mods.voicechat.api.events.RegisterVolumeCategoryEvent;
import mods.voicechat.plugins.impl.events.VolumeCategoryEventImpl;

public class RegisterVolumeCategoryEventImpl
extends VolumeCategoryEventImpl
implements RegisterVolumeCategoryEvent {
    public RegisterVolumeCategoryEventImpl(VolumeCategory category) {
        super(category);
    }
}

