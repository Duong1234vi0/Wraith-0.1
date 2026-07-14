/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import lightning.product.A_4115_X;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.x_1688_C;
import lightning.product.x_4991_F;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.viaversion.viamcp.fixes.AttackOrder;

public final class BlockBreakHelper {
    private final IPlayerContext ctx;
    private boolean didBreakLastTick;

    BlockBreakHelper(IPlayerContext ctx) {
        this.ctx = ctx;
    }

    public void stopBreakingBlock() {
        if (this.ctx.player() != null && this.didBreakLastTick) {
            if (!this.ctx.playerController().hasBrokenBlock()) {
                this.ctx.playerController().setHittingBlock(true);
            }
            this.ctx.playerController().resetBlockRemoving();
            this.didBreakLastTick = false;
        }
    }

    public void tick(boolean isLeftClick) {
        HitResult trace = this.ctx.objectMouseOver();
        if (!isLeftClick) {
            this.ctx.minecraft().H_2857_Y = 0;
        }
        if (this.ctx.minecraft().H_2857_Y <= 0 && this.ctx.player() != null && !this.ctx.player().Y_601_j()) {
            if (isLeftClick && trace != null && trace.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
                BlockHitResult blockTrace = (BlockHitResult)trace;
                c_1514_x blockPos = blockTrace.n_1700_B();
                if (!this.ctx.world().getBlockState(blockPos).v_4262_N()) {
                    b_257_Y direction = blockTrace.J_1907_R();
                    A_4115_X.n_1700_B(new x_4991_F(this.ctx.world().getBlockState(blockPos), blockPos, x_4991_F.n_1700_B.n_1700_B));
                    if (this.ctx.playerController().onPlayerDamageBlock(blockPos, direction)) {
                        this.ctx.minecraft().v_4262_N.n_1700_B(blockPos, direction);
                        AttackOrder.sendConditionalSwing(trace, x_1688_C.n_1700_B);
                    }
                    A_4115_X.n_1700_B(new x_4991_F(this.ctx.world().getBlockState(blockPos), blockPos, x_4991_F.n_1700_B.J_1907_R));
                }
            } else {
                this.ctx.playerController().resetBlockRemoving();
            }
        }
    }
}


