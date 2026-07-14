/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.x_1688_C;

public class UseOnContext {
    @Nullable
    private final a_3913_L player;
    private final x_1688_C hand;
    private final BlockHitResult rayTraceResult;
    private final b_4507_u world;
    private final Z_1993_T item;

    public UseOnContext(a_3913_L player, x_1688_C handIn, BlockHitResult rayTraceResultIn) {
        this(player.O_508_d, player, handIn, player.R_4764_Y(handIn), rayTraceResultIn);
    }

    protected UseOnContext(b_4507_u worldIn, @Nullable a_3913_L player, x_1688_C handIn, Z_1993_T heldItem, BlockHitResult rayTraceResultIn) {
        this.player = player;
        this.hand = handIn;
        this.rayTraceResult = rayTraceResultIn;
        this.item = heldItem;
        this.world = worldIn;
    }

    protected final BlockHitResult func_242401_i() {
        return this.rayTraceResult;
    }

    public c_1514_x getPos() {
        return this.rayTraceResult.n_1700_B();
    }

    public b_257_Y getFace() {
        return this.rayTraceResult.J_1907_R();
    }

    public e_2866_D getHitVec() {
        return this.rayTraceResult.P_1922_E();
    }

    public boolean isInside() {
        return this.rayTraceResult.G_564_y();
    }

    public Z_1993_T getItem() {
        return this.item;
    }

    @Nullable
    public a_3913_L getPlayer() {
        return this.player;
    }

    public x_1688_C getHand() {
        return this.hand;
    }

    public b_4507_u getWorld() {
        return this.world;
    }

    public b_257_Y getPlacementHorizontalFacing() {
        return this.player == null ? b_257_Y.R_4764_Y : this.player.o_2767_H();
    }

    public boolean hasSecondaryUseForPlayer() {
        return this.player != null && this.player.z_3000_g();
    }

    public float getPlacementYaw() {
        return this.player == null ? 0.0f : this.player.p_178_J;
    }
}


