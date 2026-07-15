/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.renderer.SubmitNodeCollector
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.multiplayer.ClientLevel
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.access;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.multiplayer.ClientLevel;

public interface AlternateBlockRenderer {
    public void renderSingleBlockWithEmission(BlockState var1, PoseStack var2, SubmitNodeCollector var3, int var4, ClientLevel var5, AbstractClientPlayer var6);
}
