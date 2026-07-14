/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import lightning.product.D_3718_K;
import lightning.product.BlockGetter;
import lightning.product.J_3017_d;
import lightning.product.K_3381_i;
import lightning.product.K_4074_S;
import lightning.product.NoiseColumn;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;
import lightning.product.z_2376_a;
import lightning.product.z_2963_s;

public class W_2121_d
extends z_1753_f {
    public static final Codec<W_2121_d> G_564_y = z_2376_a.n_1700_B.fieldOf("settings").xmap(W_2121_d::new, W_2121_d::v_4262_N).codec();
    private final z_2376_a P_1922_E;

    public W_2121_d(z_2376_a p_i231902_1_) {
        super(new D_3718_K(p_i231902_1_.R_4764_Y()), new D_3718_K(p_i231902_1_.P_1922_E()), p_i231902_1_.G_564_y(), 0L);
        this.P_1922_E = p_i231902_1_;
    }

    @Override
    protected Codec<? extends z_1753_f> n_1700_B() {
        return G_564_y;
    }

    @Override
    public z_1753_f n_1700_B(long p_230349_1_) {
        return this;
    }

    public z_2376_a v_4262_N() {
        return this.P_1922_E;
    }

    @Override
    public void n_1700_B(K_3381_i p_225551_1_, ChunkAccess p_225551_2_) {
    }

    @Override
    public int R_4764_Y() {
        K_4074_S[] ablockstate = this.P_1922_E.v_4262_N();
        for (int i = 0; i < ablockstate.length; ++i) {
            K_4074_S blockstate;
            K_4074_S k_4074_S = blockstate = ablockstate[i] == null ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : ablockstate[i];
            if (z_2963_s.n_1700_B.P_1922_E.P_1922_E().test(blockstate)) continue;
            return i - 1;
        }
        return ablockstate.length;
    }

    @Override
    public void n_1700_B(LevelAccessor p_230352_1_, J_3017_d p_230352_2_, ChunkAccess p_230352_3_) {
        K_4074_S[] ablockstate = this.P_1922_E.v_4262_N();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        z_2963_s heightmap = p_230352_3_.getHeightmap(z_2963_s.n_1700_B.R_4764_Y);
        z_2963_s heightmap1 = p_230352_3_.getHeightmap(z_2963_s.n_1700_B.n_1700_B);
        for (int i = 0; i < ablockstate.length; ++i) {
            K_4074_S blockstate = ablockstate[i];
            if (blockstate == null) continue;
            for (int j = 0; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    p_230352_3_.setBlockState(blockpos$mutable.n_1700_B(j, i, k), blockstate, false);
                    heightmap.n_1700_B(j, i, k, blockstate);
                    heightmap1.n_1700_B(j, i, k, blockstate);
                }
            }
        }
    }

    @Override
    public int n_1700_B(int x, int z, z_2963_s.n_1700_B heightmapType) {
        K_4074_S[] ablockstate = this.P_1922_E.v_4262_N();
        for (int i = ablockstate.length - 1; i >= 0; --i) {
            K_4074_S blockstate = ablockstate[i];
            if (blockstate == null || !heightmapType.P_1922_E().test(blockstate)) continue;
            return i + 1;
        }
        return 0;
    }

    @Override
    public BlockGetter n_1700_B(int p_230348_1_, int p_230348_2_) {
        return new NoiseColumn((K_4074_S[])Arrays.stream(this.P_1922_E.v_4262_N()).map(p_236072_0_ -> p_236072_0_ == null ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : p_236072_0_).toArray(K_4074_S[]::new));
    }
}


