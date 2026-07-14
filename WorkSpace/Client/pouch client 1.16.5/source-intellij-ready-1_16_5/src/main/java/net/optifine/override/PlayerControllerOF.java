/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.override;

import lightning.product.BlockHitResult;
import lightning.product.N_4263_v;
import lightning.product.V_3163_W;
import lightning.product.V_772_m;
import lightning.product.W_2853_p;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.k_4690_i;
import lightning.product.m_3054_I;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;

public class PlayerControllerOF
extends V_3163_W {
    private boolean acting = false;
    private c_1514_x lastClickBlockPos = null;
    private N_4263_v lastClickEntity = null;

    public PlayerControllerOF(MinecraftClient mcIn, W_2853_p netHandler) {
        super(mcIn, netHandler);
    }

    @Override
    public boolean clickBlock(c_1514_x loc, b_257_Y face) {
        this.acting = true;
        this.lastClickBlockPos = loc;
        boolean flag = super.clickBlock(loc, face);
        this.acting = false;
        return flag;
    }

    @Override
    public boolean onPlayerDamageBlock(c_1514_x posBlock, b_257_Y directionFacing) {
        this.acting = true;
        this.lastClickBlockPos = posBlock;
        boolean flag = super.onPlayerDamageBlock(posBlock, directionFacing);
        this.acting = false;
        return flag;
    }

    @Override
    public m_3054_I processRightClick(a_3913_L player, b_4507_u worldIn, x_1688_C hand) {
        this.acting = true;
        m_3054_I actionresulttype = super.processRightClick(player, worldIn, hand);
        this.acting = false;
        return actionresulttype;
    }

    @Override
    public m_3054_I func_217292_a(V_772_m player, k_4690_i worldIn, x_1688_C hand, BlockHitResult rayTrace) {
        this.acting = true;
        this.lastClickBlockPos = rayTrace.n_1700_B();
        m_3054_I actionresulttype = super.func_217292_a(player, worldIn, hand, rayTrace);
        this.acting = false;
        return actionresulttype;
    }

    @Override
    public m_3054_I interactWithEntity(a_3913_L player, N_4263_v target, x_1688_C hand) {
        this.lastClickEntity = target;
        return super.interactWithEntity(player, target, hand);
    }

    @Override
    public m_3054_I interactWithEntity(a_3913_L player, N_4263_v target, EntityHitResult ray, x_1688_C hand) {
        this.lastClickEntity = target;
        return super.interactWithEntity(player, target, ray, hand);
    }

    public boolean isActing() {
        return this.acting;
    }

    public c_1514_x getLastClickBlockPos() {
        return this.lastClickBlockPos;
    }

    public N_4263_v getLastClickEntity() {
        return this.lastClickEntity;
    }
}



