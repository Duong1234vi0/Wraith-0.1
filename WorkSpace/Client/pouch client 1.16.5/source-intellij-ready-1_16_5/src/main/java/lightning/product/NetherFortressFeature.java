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
import lightning.product.E_3771_B;
import lightning.product.StructureFeature;
import lightning.product.BoundingBox;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.i_3880_A;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.o_2105_O;
import lightning.product.r_4097_j;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;

public class NetherFortressFeature
extends StructureFeature<o_2105_O> {
    private static final List<MobSpawnSettings.R_4764_Y> Y_259_p = ImmutableList.of((Object)new MobSpawnSettings.R_4764_Y(t_5_h.u_1723_Y, 10, 2, 3), (Object)new MobSpawnSettings.R_4764_Y(t_5_h.c_132_F, 5, 4, 4), (Object)new MobSpawnSettings.R_4764_Y(t_5_h.RowButton, 8, 5, 5), (Object)new MobSpawnSettings.R_4764_Y(t_5_h.V_1446_Y, 2, 5, 5), (Object)new MobSpawnSettings.R_4764_Y(t_5_h.B_1668_F, 3, 4, 4));

    public NetherFortressFeature(Codec<o_2105_O> p_i231972_1_) {
        super(p_i231972_1_);
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, o_2105_O p_230363_10_) {
        return p_230363_5_.nextInt(5) < 2;
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
        public n_1700_B(StructureFeature<o_2105_O> p_i225812_1_, int p_i225812_2_, int p_i225812_3_, BoundingBox p_i225812_4_, int p_i225812_5_, long p_i225812_6_) {
            super(p_i225812_1_, p_i225812_2_, p_i225812_3_, p_i225812_4_, p_i225812_5_, p_i225812_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, o_2105_O p_230364_7_) {
            i_3880_A.Q_4569_t fortresspieces$start = new i_3880_A.Q_4569_t(this.G_564_y, (p_230364_4_ << 4) + 2, (p_230364_5_ << 4) + 2);
            this.J_1907_R.add(fortresspieces$start);
            fortresspieces$start.n_1700_B(fortresspieces$start, this.J_1907_R, this.G_564_y);
            List<E_3771_B> list = fortresspieces$start.G_564_y;
            while (!list.isEmpty()) {
                int i = this.G_564_y.nextInt(list.size());
                E_3771_B structurepiece = list.remove(i);
                structurepiece.n_1700_B(fortresspieces$start, this.J_1907_R, this.G_564_y);
            }
            this.J_1907_R();
            this.n_1700_B(this.G_564_y, 48, 70);
        }
    }
}


