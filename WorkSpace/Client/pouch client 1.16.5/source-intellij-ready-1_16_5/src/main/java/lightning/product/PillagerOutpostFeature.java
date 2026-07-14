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
import lightning.product.V_4739_Y;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.WorldgenRandom;
import lightning.product.g_4497_s;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.JigsawConfiguration;
import lightning.product.t_5_h;
import lightning.product.z_1753_f;

public class PillagerOutpostFeature
extends g_4497_s {
    private static final List<MobSpawnSettings.R_4764_Y> Y_259_p = ImmutableList.of((Object)new MobSpawnSettings.R_4764_Y(t_5_h.p_178_J, 1, 1, 1));

    public PillagerOutpostFeature(Codec<JigsawConfiguration> p_i231977_1_) {
        super(p_i231977_1_, 0, true, true);
    }

    @Override
    public List<MobSpawnSettings.R_4764_Y> R_4764_Y() {
        return Y_259_p;
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, JigsawConfiguration p_230363_10_) {
        int i = p_230363_6_ >> 4;
        int j = p_230363_7_ >> 4;
        p_230363_5_.setSeed((long)(i ^ j << 4) ^ p_230363_3_);
        p_230363_5_.nextInt();
        if (p_230363_5_.nextInt(5) != 0) {
            return false;
        }
        return !this.n_1700_B(p_230363_1_, p_230363_3_, p_230363_5_, p_230363_6_, p_230363_7_);
    }

    private boolean n_1700_B(z_1753_f p_242782_1_, long p_242782_2_, WorldgenRandom p_242782_4_, int p_242782_5_, int p_242782_6_) {
        V_4739_Y structureseparationsettings = p_242782_1_.J_1907_R().n_1700_B(StructureFeature.t_1786_h);
        if (structureseparationsettings == null) {
            return false;
        }
        for (int i = p_242782_5_ - 10; i <= p_242782_5_ + 10; ++i) {
            for (int j = p_242782_6_ - 10; j <= p_242782_6_ + 10; ++j) {
                Y_1387_d chunkpos = StructureFeature.t_1786_h.n_1700_B(structureseparationsettings, p_242782_2_, p_242782_4_, i, j);
                if (i != chunkpos.J_1907_R || j != chunkpos.R_4764_Y) continue;
                return true;
            }
        }
        return false;
    }
}


