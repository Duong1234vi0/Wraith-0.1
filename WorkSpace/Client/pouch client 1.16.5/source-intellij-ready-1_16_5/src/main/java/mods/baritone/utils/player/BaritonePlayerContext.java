/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.player;

import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.V_772_m;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.cache.IWorldData;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerController;
import mods.baritone.api.api.java.baritone.api.utils.RayTraceUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.utils.player.BaritonePlayerController;

public final class BaritonePlayerContext
implements IPlayerContext {
    private final Baritone baritone;
    private final MinecraftClient mc;
    private final IPlayerController playerController;

    public BaritonePlayerContext(Baritone baritone, MinecraftClient mc) {
        this.baritone = baritone;
        this.mc = mc;
        this.playerController = new BaritonePlayerController(mc);
    }

    @Override
    public MinecraftClient minecraft() {
        return this.mc;
    }

    @Override
    public V_772_m player() {
        return this.mc.Y_259_p;
    }

    @Override
    public IPlayerController playerController() {
        return this.playerController;
    }

    @Override
    public b_4507_u world() {
        return this.mc.Y_601_j;
    }

    @Override
    public IWorldData worldData() {
        return this.baritone.getWorldProvider().getCurrentWorld();
    }

    @Override
    public BetterBlockPos viewerPos() {
        N_4263_v entity = this.mc.g_2268_R();
        return entity == null ? this.playerFeet() : BetterBlockPos.from(entity.b_2312_j());
    }

    @Override
    public Rotation playerRotations() {
        return this.baritone.getLookBehavior().getEffectiveRotation().orElseGet(() -> IPlayerContext.super.playerRotations());
    }

    @Override
    public HitResult objectMouseOver() {
        return RayTraceUtils.rayTraceTowards(this.player(), this.playerRotations(), this.playerController().getBlockReachDistance());
    }
}



