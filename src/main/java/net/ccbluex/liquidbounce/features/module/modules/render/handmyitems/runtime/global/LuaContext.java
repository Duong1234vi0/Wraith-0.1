/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  net.minecraft.client.player.AbstractClientPlayer
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.InteractionHand
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.global;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.patricles.Particle;
import java.util.List;
import net.minecraft.client.player.AbstractClientPlayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;

public class LuaContext {
    public PoseStack matrices;
    public boolean bl;
    public float swingProgress;
    public ItemStack item;
    public AbstractClientPlayer player;
    public InteractionHand hand;
    public boolean mainHand;
    public float deltaTime;
    public float equipProgress;
    public float mainHandSwingProgress;
    public float offHandSwingProgress;
    public boolean mainHandSwitchEvent;
    public boolean offHandSwitchEvent;
    public boolean swingMHand;
    public boolean swingOHand;
    public boolean interact;
    public boolean blockBreaking;
    public List<Particle> particles;

    public void update(PoseStack matrices, boolean bl, float swingProgress, ItemStack item, AbstractClientPlayer player, InteractionHand hand, boolean mainHand, float deltaTime, float equipProgress, float mainHandSwingProgress, float offHandSwingProgress, boolean mainHandSwitchEvent, boolean offHandSwitchEvent, boolean swingMHand, boolean swingOHand, boolean interact, boolean blockBreaking, List<Particle> particles) {
        this.matrices = matrices;
        this.bl = bl;
        this.swingProgress = swingProgress;
        this.item = item;
        this.player = player;
        this.hand = hand;
        this.mainHand = mainHand;
        this.deltaTime = deltaTime;
        this.equipProgress = equipProgress;
        this.mainHandSwingProgress = mainHandSwingProgress;
        this.offHandSwingProgress = offHandSwingProgress;
        this.mainHandSwitchEvent = mainHandSwitchEvent;
        this.offHandSwitchEvent = offHandSwitchEvent;
        this.swingMHand = swingMHand;
        this.swingOHand = swingOHand;
        this.interact = interact;
        this.blockBreaking = blockBreaking;
        this.particles = particles;
    }
}
