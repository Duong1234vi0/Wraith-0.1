/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import lightning.product.BlockHitResult;
import lightning.product.I_14_v;
import lightning.product.V_772_m;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.x_1688_C;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public interface IPlayerController {
    public void syncHeldItem();

    public boolean hasBrokenBlock();

    public boolean onPlayerDamageBlock(c_1514_x var1, b_257_Y var2);

    public void resetBlockRemoving();

    public Z_1993_T windowClick(int var1, int var2, int var3, a_408_T var4, a_3913_L var5);

    public I_14_v getGameType();

    public m_3054_I processRightClickBlock(V_772_m var1, b_4507_u var2, x_1688_C var3, BlockHitResult var4);

    public m_3054_I processRightClick(V_772_m var1, b_4507_u var2, x_1688_C var3);

    public boolean clickBlock(c_1514_x var1, b_257_Y var2);

    public void setHittingBlock(boolean var1);

    default public double getBlockReachDistance() {
        return this.getGameType().P_1922_E() ? 5.0 : (double)((Float)BaritoneAPI.getSettings().blockReachDistance.value).floatValue();
    }
}


