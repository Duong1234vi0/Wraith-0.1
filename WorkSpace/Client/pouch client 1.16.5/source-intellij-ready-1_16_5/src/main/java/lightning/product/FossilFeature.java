/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.d_862_x;
import lightning.product.g_2336_b;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class FossilFeature
extends Feature<o_2105_O> {
    private static final g_2336_b n_1700_B = new g_2336_b("fossil/spine_1");
    private static final g_2336_b D_4792_h = new g_2336_b("fossil/spine_2");
    private static final g_2336_b s_2632_s = new g_2336_b("fossil/spine_3");
    private static final g_2336_b l_1233_K = new g_2336_b("fossil/spine_4");
    private static final g_2336_b z_1333_t = new g_2336_b("fossil/spine_1_coal");
    private static final g_2336_b O_508_d = new g_2336_b("fossil/spine_2_coal");
    private static final g_2336_b r_715_M = new g_2336_b("fossil/spine_3_coal");
    private static final g_2336_b A_1038_p = new g_2336_b("fossil/spine_4_coal");
    private static final g_2336_b i_1637_u = new g_2336_b("fossil/skull_1");
    private static final g_2336_b Ping = new g_2336_b("fossil/skull_2");
    private static final g_2336_b p_178_J = new g_2336_b("fossil/skull_3");
    private static final g_2336_b RealmsClientConfig = new g_2336_b("fossil/skull_4");
    private static final g_2336_b f_4016_n = new g_2336_b("fossil/skull_1_coal");
    private static final g_2336_b j_276_v = new g_2336_b("fossil/skull_2_coal");
    private static final g_2336_b UploadStatus = new g_2336_b("fossil/skull_3_coal");
    private static final g_2336_b e_1992_r = new g_2336_b("fossil/skull_4_coal");
    private static final g_2336_b[] D_60_a = new g_2336_b[]{n_1700_B, D_4792_h, s_2632_s, l_1233_K, i_1637_u, Ping, p_178_J, RealmsClientConfig};
    private static final g_2336_b[] k_3961_g = new g_2336_b[]{z_1333_t, O_508_d, r_715_M, A_1038_p, f_4016_n, j_276_v, UploadStatus, e_1992_r};

    public FossilFeature(Codec<o_2105_O> p_i231955_1_) {
        super(p_i231955_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        W_2163_m rotation = W_2163_m.n_1700_B(p_241855_3_);
        int i = p_241855_3_.nextInt(D_60_a.length);
        b_2085_h templatemanager = p_241855_1_.J_1907_R().T_2506_i().M_2677_i();
        a_2886_t template = templatemanager.n_1700_B(D_60_a[i]);
        a_2886_t template1 = templatemanager.n_1700_B(k_3961_g[i]);
        Y_1387_d chunkpos = new Y_1387_d(p_241855_4_);
        BoundingBox mutableboundingbox = new BoundingBox(chunkpos.J_1907_R(), 0, chunkpos.R_4764_Y(), chunkpos.G_564_y(), 256, chunkpos.P_1922_E());
        w_1748_S placementsettings = new w_1748_S().n_1700_B(rotation).n_1700_B(mutableboundingbox).n_1700_B(p_241855_3_).n_1700_B(r_4719_P.G_564_y);
        c_1514_x blockpos = template.n_1700_B(rotation);
        int j = p_241855_3_.nextInt(16 - blockpos.getX());
        int k = p_241855_3_.nextInt(16 - blockpos.getZ());
        int l = 256;
        for (int i1 = 0; i1 < blockpos.getX(); ++i1) {
            for (int j1 = 0; j1 < blockpos.getZ(); ++j1) {
                l = Math.min(l, p_241855_1_.n_1700_B(z_2963_s.n_1700_B.R_4764_Y, p_241855_4_.getX() + i1 + j, p_241855_4_.getZ() + j1 + k));
            }
        }
        int k1 = Math.max(l - 15 - p_241855_3_.nextInt(10), 10);
        c_1514_x blockpos1 = template.n_1700_B(p_241855_4_.add(j, k1, k), q_4099_E.n_1700_B, rotation);
        d_862_x integrityprocessor = new d_862_x(0.9f);
        placementsettings.J_1907_R().n_1700_B(integrityprocessor);
        template.n_1700_B(p_241855_1_, blockpos1, blockpos1, placementsettings, p_241855_3_, 4);
        placementsettings.J_1907_R(integrityprocessor);
        d_862_x integrityprocessor1 = new d_862_x(0.1f);
        placementsettings.J_1907_R().n_1700_B(integrityprocessor1);
        template1.n_1700_B(p_241855_1_, blockpos1, blockpos1, placementsettings, p_241855_3_, 4);
        return true;
    }
}


