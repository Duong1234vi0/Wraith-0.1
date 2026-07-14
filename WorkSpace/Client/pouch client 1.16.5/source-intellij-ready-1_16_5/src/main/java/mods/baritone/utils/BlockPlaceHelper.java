/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils;

import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.m_3054_I;
import lightning.product.x_1688_C;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;

public class BlockPlaceHelper {
    private final IPlayerContext ctx;
    private int rightClickTimer;

    BlockPlaceHelper(IPlayerContext playerContext) {
        this.ctx = playerContext;
    }

    public void tick(boolean rightClickRequested) {
        if (this.rightClickTimer > 0) {
            --this.rightClickTimer;
            return;
        }
        HitResult mouseOver = this.ctx.objectMouseOver();
        if (!rightClickRequested || this.ctx.player().e_4240_b() || mouseOver == null || mouseOver.R_4764_Y() != HitResult.n_1700_B.J_1907_R) {
            return;
        }
        this.rightClickTimer = (Integer)Baritone.settings().rightClickSpeed.value;
        for (x_1688_C hand : x_1688_C.values()) {
            if (this.ctx.playerController().processRightClickBlock(this.ctx.player(), this.ctx.world(), hand, (BlockHitResult)mouseOver) == m_3054_I.n_1700_B) {
                this.ctx.player().n_1700_B(hand);
                return;
            }
            if (this.ctx.player().R_4764_Y(hand).n_1700_B() || this.ctx.playerController().processRightClick(this.ctx.player(), this.ctx.world(), hand) != m_3054_I.n_1700_B) continue;
            return;
        }
    }
}


