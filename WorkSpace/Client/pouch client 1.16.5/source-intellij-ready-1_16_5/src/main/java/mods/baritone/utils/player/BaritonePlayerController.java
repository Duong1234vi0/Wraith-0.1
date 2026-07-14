/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.player;

import lightning.product.BlockHitResult;
import lightning.product.I_14_v;
import lightning.product.V_772_m;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.k_4690_i;
import lightning.product.m_3054_I;
import lightning.product.x_1688_C;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerController;

public final class BaritonePlayerController
implements IPlayerController {
    private final MinecraftClient mc;

    public BaritonePlayerController(MinecraftClient mc) {
        this.mc = mc;
    }

    @Override
    public void syncHeldItem() {
        this.mc.w_1457_N.callSyncCurrentPlayItem();
    }

    @Override
    public boolean hasBrokenBlock() {
        return this.mc.w_1457_N.getCurrentBlock().getY() == -1;
    }

    @Override
    public boolean onPlayerDamageBlock(c_1514_x pos, b_257_Y side) {
        return this.mc.w_1457_N.onPlayerDamageBlock(pos, side);
    }

    @Override
    public void resetBlockRemoving() {
        this.mc.w_1457_N.resetBlockRemoving();
    }

    @Override
    public Z_1993_T windowClick(int windowId, int slotId, int mouseButton, a_408_T type, a_3913_L player) {
        return this.mc.w_1457_N.windowClick(windowId, slotId, mouseButton, type, player);
    }

    @Override
    public I_14_v getGameType() {
        return this.mc.w_1457_N.getCurrentGameType();
    }

    @Override
    public m_3054_I processRightClickBlock(V_772_m player, b_4507_u world, x_1688_C hand, BlockHitResult result) {
        return this.mc.w_1457_N.func_217292_a(player, (k_4690_i)world, hand, result);
    }

    @Override
    public m_3054_I processRightClick(V_772_m player, b_4507_u world, x_1688_C hand) {
        return this.mc.w_1457_N.processRightClick(player, world, hand);
    }

    @Override
    public boolean clickBlock(c_1514_x loc, b_257_Y face) {
        return this.mc.w_1457_N.clickBlock(loc, face);
    }

    @Override
    public void setHittingBlock(boolean hittingBlock) {
        this.mc.w_1457_N.setIsHittingBlock(hittingBlock);
    }
}



