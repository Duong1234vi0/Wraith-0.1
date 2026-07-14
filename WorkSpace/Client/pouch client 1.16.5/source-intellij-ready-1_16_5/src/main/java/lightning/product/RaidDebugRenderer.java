/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Collection;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.h_3572_K;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;

public class RaidDebugRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;
    private Collection<c_1514_x> J_1907_R = Lists.newArrayList();

    public RaidDebugRenderer(MinecraftClient client) {
        this.n_1700_B = client;
    }

    public void n_1700_B(Collection<c_1514_x> p_222906_1_) {
        this.J_1907_R = p_222906_1_;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        c_1514_x blockpos = this.J_1907_R().R_4764_Y();
        for (c_1514_x blockpos1 : this.J_1907_R) {
            if (!blockpos.withinDistance(blockpos1, 160.0)) continue;
            RaidDebugRenderer.n_1700_B(blockpos1);
        }
    }

    private static void n_1700_B(c_1514_x p_222903_0_) {
        n_4915_r.n_1700_B(p_222903_0_.add(-0.5, -0.5, -0.5), p_222903_0_.add(1.5, 1.5, 1.5), 1.0f, 0.0f, 0.0f, 0.15f);
        int i = -65536;
        RaidDebugRenderer.n_1700_B("Raid center", p_222903_0_, -65536);
    }

    private static void n_1700_B(String p_222905_0_, c_1514_x p_222905_1_, int p_222905_2_) {
        double d0 = (double)p_222905_1_.getX() + 0.5;
        double d1 = (double)p_222905_1_.getY() + 1.3;
        double d2 = (double)p_222905_1_.getZ() + 0.5;
        n_4915_r.n_1700_B(p_222905_0_, d0, d1, d2, p_222905_2_, 0.04f, true, 0.0f, true);
    }

    private h_3572_K J_1907_R() {
        return this.n_1700_B.s_956_w.M_588_G();
    }
}



