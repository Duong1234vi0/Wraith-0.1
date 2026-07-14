/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.D_3718_K;
import lightning.product.BlockGetter;
import lightning.product.BiomeManager;
import lightning.product.J_3017_d;
import lightning.product.K_3381_i;
import lightning.product.K_4074_S;
import lightning.product.P_1103_o;
import lightning.product.StructureSettings;
import lightning.product.T_3975_o;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.NoiseColumn;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class DebugLevelSource
extends z_1753_f {
    public static final Codec<DebugLevelSource> G_564_y = P_1103_o.n_1700_B(V_3137_a.PlayerInfo).xmap(DebugLevelSource::new, DebugLevelSource::v_4262_N).stable().codec();
    private static final List<K_4074_S> v_4262_N = StreamSupport.stream(V_3137_a.q_4610_l.spliterator(), false).flatMap(p_236067_0_ -> p_236067_0_.t_1786_h().n_1700_B().stream()).collect(Collectors.toList());
    private static final int w_1484_f = u_530_F.u_1723_Y(u_530_F.R_4764_Y((float)v_4262_N.size()));
    private static final int t_148_a = u_530_F.u_1723_Y((float)v_4262_N.size() / (float)w_1484_f);
    protected static final K_4074_S P_1922_E = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    protected static final K_4074_S u_1723_Y = a_3742_W.N_4890_q.multiplayerClientSuggestionProvider();
    private final V_3137_a<k_594_Q> s_956_w;

    public DebugLevelSource(V_3137_a<k_594_Q> p_i241974_1_) {
        super(new D_3718_K(p_i241974_1_.R_4764_Y(biomeBiomes.J_1907_R)), new StructureSettings(false));
        this.s_956_w = p_i241974_1_;
    }

    public V_3137_a<k_594_Q> v_4262_N() {
        return this.s_956_w;
    }

    @Override
    protected Codec<? extends z_1753_f> n_1700_B() {
        return G_564_y;
    }

    @Override
    public z_1753_f n_1700_B(long p_230349_1_) {
        return this;
    }

    @Override
    public void n_1700_B(K_3381_i p_225551_1_, ChunkAccess p_225551_2_) {
    }

    @Override
    public void n_1700_B(long p_230350_1_, BiomeManager p_230350_3_, ChunkAccess p_230350_4_, T_3975_o.n_1700_B p_230350_5_) {
    }

    @Override
    public void n_1700_B(K_3381_i p_230351_1_, J_3017_d p_230351_2_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i = p_230351_1_.R_4764_Y();
        int j = p_230351_1_.G_564_y();
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                int i1 = (i << 4) + k;
                int j1 = (j << 4) + l;
                p_230351_1_.n_1700_B((c_1514_x)blockpos$mutable.n_1700_B(i1, 60, j1), u_1723_Y, 2);
                K_4074_S blockstate = DebugLevelSource.J_1907_R(i1, j1);
                if (blockstate == null) continue;
                p_230351_1_.n_1700_B((c_1514_x)blockpos$mutable.n_1700_B(i1, 70, j1), blockstate, 2);
            }
        }
    }

    @Override
    public void n_1700_B(LevelAccessor p_230352_1_, J_3017_d p_230352_2_, ChunkAccess p_230352_3_) {
    }

    @Override
    public int n_1700_B(int x, int z, z_2963_s.n_1700_B heightmapType) {
        return 0;
    }

    @Override
    public BlockGetter n_1700_B(int p_230348_1_, int p_230348_2_) {
        return new NoiseColumn(new K_4074_S[0]);
    }

    public static K_4074_S J_1907_R(int p_177461_0_, int p_177461_1_) {
        int i;
        K_4074_S blockstate = P_1922_E;
        if (p_177461_0_ > 0 && p_177461_1_ > 0 && p_177461_0_ % 2 != 0 && p_177461_1_ % 2 != 0 && (p_177461_0_ /= 2) <= w_1484_f && (p_177461_1_ /= 2) <= t_148_a && (i = u_530_F.n_1700_B(p_177461_0_ * w_1484_f + p_177461_1_)) < v_4262_N.size()) {
            blockstate = v_4262_N.get(i);
        }
        return blockstate;
    }
}


