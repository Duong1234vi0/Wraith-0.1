/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.V_772_m;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.k_4690_i;
import lightning.product.y_3008_A;
import mods.baritone.api.api.java.baritone.api.cache.IWorldData;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerController;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;

public interface IPlayerContext {
    public MinecraftClient minecraft();

    public V_772_m player();

    public IPlayerController playerController();

    public b_4507_u world();

    default public Iterable<N_4263_v> entities() {
        return ((k_4690_i)this.world()).J_1907_R();
    }

    default public Stream<N_4263_v> entitiesStream() {
        return StreamSupport.stream(this.entities().spliterator(), false);
    }

    public IWorldData worldData();

    public HitResult objectMouseOver();

    default public BetterBlockPos playerFeet() {
        BetterBlockPos feet = new BetterBlockPos(this.player().s_4990_V().J_1907_R, this.player().s_4990_V().R_4764_Y + 0.1251, this.player().s_4990_V().G_564_y);
        try {
            if (this.world().getBlockState(feet).J_1907_R() instanceof y_3008_A) {
                return feet.up();
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return feet;
    }

    default public e_2866_D playerFeetAsVec() {
        return new e_2866_D(this.player().s_4990_V().J_1907_R, this.player().s_4990_V().R_4764_Y, this.player().s_4990_V().G_564_y);
    }

    default public e_2866_D playerHead() {
        return new e_2866_D(this.player().s_4990_V().J_1907_R, this.player().s_4990_V().R_4764_Y + (double)this.player().X_1313_W(), this.player().s_4990_V().G_564_y);
    }

    public BetterBlockPos viewerPos();

    default public Rotation playerRotations() {
        return new Rotation(this.player().p_178_J, this.player().f_4016_n);
    }

    public static double eyeHeight(boolean ifSneaking) {
        return ifSneaking ? 1.27 : 1.62;
    }

    default public Optional<c_1514_x> getSelectedBlock() {
        HitResult result = this.objectMouseOver();
        if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            return Optional.of(((BlockHitResult)result).n_1700_B());
        }
        return Optional.empty();
    }

    default public boolean isLookingAt(c_1514_x pos) {
        return this.getSelectedBlock().equals(Optional.of(pos));
    }
}



