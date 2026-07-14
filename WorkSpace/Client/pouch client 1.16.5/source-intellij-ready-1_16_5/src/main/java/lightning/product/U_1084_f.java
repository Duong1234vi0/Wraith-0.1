/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import lightning.product.StructureFeature;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.o_2105_O;
import lightning.product.o_2945_w;
import lightning.product.r_4097_j;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;

public class U_1084_f
extends StructureFeature<o_2105_O> {
    private static final List<MobSpawnSettings.R_4764_Y> Y_259_p = ImmutableList.of((Object)new MobSpawnSettings.R_4764_Y(t_5_h.x_607_J, 1, 2, 4));

    public U_1084_f(Codec<o_2105_O> p_i231975_1_) {
        super(p_i231975_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return false;
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, o_2105_O p_230363_10_) {
        for (k_594_Q biome : p_230363_2_.n_1700_B(p_230363_6_ * 16 + 9, p_230363_1_.u_1723_Y(), p_230363_7_ * 16 + 9, 16)) {
            if (biome.P_1922_E().n_1700_B(this)) continue;
            return false;
        }
        for (k_594_Q biome1 : p_230363_2_.n_1700_B(p_230363_6_ * 16 + 9, p_230363_1_.u_1723_Y(), p_230363_7_ * 16 + 9, 29)) {
            if (biome1.Y_601_j() == k_594_Q.R_4764_Y.M_588_G || biome1.Y_601_j() == k_594_Q.R_4764_Y.h_1847_R) continue;
            return false;
        }
        return true;
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    @Override
    public List<MobSpawnSettings.R_4764_Y> R_4764_Y() {
        return Y_259_p;
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        private boolean P_1922_E;

        public n_1700_B(StructureFeature<o_2105_O> p_i225814_1_, int p_i225814_2_, int p_i225814_3_, BoundingBox p_i225814_4_, int p_i225814_5_, long p_i225814_6_) {
            super(p_i225814_1_, p_i225814_2_, p_i225814_3_, p_i225814_4_, p_i225814_5_, p_i225814_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            this.J_1907_R(p_230364_4_, p_230364_5_);
        }

        private void J_1907_R(int chunkX, int chunkZ) {
            int i = chunkX * 16 - 29;
            int j = chunkZ * 16 - 29;
            b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(this.G_564_y);
            this.J_1907_R.add(new o_2945_w.s_956_w(this.G_564_y, i, j, direction));
            this.J_1907_R();
            this.P_1922_E = true;
        }

        @Override
        public void n_1700_B(WorldGenLevel p_230366_1_, J_3017_d p_230366_2_, z_1753_f p_230366_3_, Random p_230366_4_, BoundingBox p_230366_5_, Y_1387_d p_230366_6_) {
            if (!this.P_1922_E) {
                this.J_1907_R.clear();
                this.J_1907_R(this.u_1723_Y(), this.v_4262_N());
            }
            super.n_1700_B(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_);
        }
    }
}


