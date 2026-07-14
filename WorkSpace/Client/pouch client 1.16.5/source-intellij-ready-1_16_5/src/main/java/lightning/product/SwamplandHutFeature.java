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
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.MobSpawnSettings;
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.k_594_Q;
import lightning.product.o_2105_O;
import lightning.product.o_3297_d;
import lightning.product.r_4097_j;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;

public class SwamplandHutFeature
extends StructureFeature<o_2105_O> {
    private static final List<MobSpawnSettings.R_4764_Y> Y_259_p = ImmutableList.of((Object)new MobSpawnSettings.R_4764_Y(t_5_h.RetryCallException, 1, 1, 1));
    private static final List<MobSpawnSettings.R_4764_Y> Q_2552_b = ImmutableList.of((Object)new MobSpawnSettings.R_4764_Y(t_5_h.w_1484_f, 1, 1, 1));

    public SwamplandHutFeature(Codec<o_2105_O> p_i231998_1_) {
        super(p_i231998_1_);
    }

    @Override
    public StructureFeature.n_1700_B<o_2105_O> n_1700_B() {
        return n_1700_B::new;
    }

    @Override
    public List<MobSpawnSettings.R_4764_Y> R_4764_Y() {
        return Y_259_p;
    }

    @Override
    public List<MobSpawnSettings.R_4764_Y> w_1484_f() {
        return Q_2552_b;
    }

    public static class n_1700_B
    extends StructureStart<o_2105_O> {
        public n_1700_B(StructureFeature<o_2105_O> p_i225819_1_, int p_i225819_2_, int p_i225819_3_, BoundingBox boundingBox, int p_i225819_5_, long p_i225819_6_) {
            super(p_i225819_1_, p_i225819_2_, p_i225819_3_, boundingBox, p_i225819_5_, p_i225819_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            o_3297_d swamphutpiece = new o_3297_d(this.G_564_y, p_230364_4_ * 16, p_230364_5_ * 16);
            this.J_1907_R.add(swamphutpiece);
            this.J_1907_R();
        }
    }
}


