/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.sounds.SoundEvent
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.custom_api;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;

public class Sound {
    private SoundEvent sound;

    @Safe
    public void play(float volume, float pitch) {
        Minecraft.getInstance().player.playSound(this.sound, volume, pitch);
    }
}
