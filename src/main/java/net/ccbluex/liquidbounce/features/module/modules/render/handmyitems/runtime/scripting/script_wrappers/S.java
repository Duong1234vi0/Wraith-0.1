/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.resources.Identifier
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;

public class S {
    @Safe
    public void playSound(String id, double volume) {
        Minecraft.getInstance().player.playSound(SoundEvent.createVariableRangeEvent(Identifier.withDefaultNamespace(id)), (float)volume, 1.0f);
    }
}
