/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
import java.util.Random;
import lightning.product.ImprovedNoise;
import lightning.product.BigContext;
import lightning.product.LazyArea;
import lightning.product.LinearCongruentialGenerator;
import lightning.product.s_1037_T;
import lightning.product.t_4013_W;

public class a_1540_b
implements BigContext<LazyArea> {
    private final Long2IntLinkedOpenHashMap n_1700_B;
    private final int J_1907_R;
    private final ImprovedNoise R_4764_Y;
    private final long G_564_y;
    private long P_1922_E;

    public a_1540_b(int maxCacheSizeIn, long seedIn, long seedModifierIn) {
        this.G_564_y = a_1540_b.J_1907_R(seedIn, seedModifierIn);
        this.R_4764_Y = new ImprovedNoise(new Random(seedIn));
        this.n_1700_B = new Long2IntLinkedOpenHashMap(16, 0.25f);
        this.n_1700_B.defaultReturnValue(Integer.MIN_VALUE);
        this.J_1907_R = maxCacheSizeIn;
    }

    public LazyArea J_1907_R(s_1037_T pixelTransformer) {
        return new LazyArea(this.n_1700_B, this.J_1907_R, pixelTransformer);
    }

    @Override
    public LazyArea n_1700_B(s_1037_T pixelTransformer, LazyArea area) {
        return new LazyArea(this.n_1700_B, Math.min(1024, area.n_1700_B() * 4), pixelTransformer);
    }

    @Override
    public LazyArea n_1700_B(s_1037_T p_212860_1_, LazyArea firstArea, LazyArea secondArea) {
        return new LazyArea(this.n_1700_B, Math.min(1024, Math.max(firstArea.n_1700_B(), secondArea.n_1700_B()) * 4), p_212860_1_);
    }

    @Override
    public void n_1700_B(long x, long z) {
        long i = this.G_564_y;
        i = LinearCongruentialGenerator.n_1700_B(i, x);
        i = LinearCongruentialGenerator.n_1700_B(i, z);
        i = LinearCongruentialGenerator.n_1700_B(i, x);
        this.P_1922_E = i = LinearCongruentialGenerator.n_1700_B(i, z);
    }

    @Override
    public int n_1700_B(int bound) {
        int i = (int)Math.floorMod(this.P_1922_E >> 24, (long)bound);
        this.P_1922_E = LinearCongruentialGenerator.n_1700_B(this.P_1922_E, this.G_564_y);
        return i;
    }

    @Override
    public ImprovedNoise n_1700_B() {
        return this.R_4764_Y;
    }

    private static long J_1907_R(long left, long right) {
        long lvt_4_1_ = LinearCongruentialGenerator.n_1700_B(right, right);
        lvt_4_1_ = LinearCongruentialGenerator.n_1700_B(lvt_4_1_, right);
        lvt_4_1_ = LinearCongruentialGenerator.n_1700_B(lvt_4_1_, right);
        long lvt_6_1_ = LinearCongruentialGenerator.n_1700_B(left, lvt_4_1_);
        lvt_6_1_ = LinearCongruentialGenerator.n_1700_B(lvt_6_1_, lvt_4_1_);
        return LinearCongruentialGenerator.n_1700_B(lvt_6_1_, lvt_4_1_);
    }

    @Override
    public /* synthetic */ t_4013_W n_1700_B(s_1037_T s_1037_T2) {
        return this.J_1907_R(s_1037_T2);
    }
}


