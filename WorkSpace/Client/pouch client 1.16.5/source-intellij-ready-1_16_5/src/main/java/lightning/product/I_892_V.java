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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lightning.product.E_4700_p;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructureStart;
import lightning.product.g_2336_b;
import lightning.product.h_4966_V;
import lightning.product.j_3341_s;
import lightning.product.RuinedPortalConfiguration;
import lightning.product.k_594_Q;
import lightning.product.q_4099_E;
import lightning.product.r_4097_j;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;

public class I_892_V
extends StructureFeature<RuinedPortalConfiguration> {
    private static final String[] Y_259_p = new String[]{"ruined_portal/portal_1", "ruined_portal/portal_2", "ruined_portal/portal_3", "ruined_portal/portal_4", "ruined_portal/portal_5", "ruined_portal/portal_6", "ruined_portal/portal_7", "ruined_portal/portal_8", "ruined_portal/portal_9", "ruined_portal/portal_10"};
    private static final String[] Q_2552_b = new String[]{"ruined_portal/giant_portal_1", "ruined_portal/giant_portal_2", "ruined_portal/giant_portal_3"};

    public I_892_V(Codec<RuinedPortalConfiguration> p_i231984_1_) {
        super(p_i231984_1_);
    }

    @Override
    public StructureFeature.n_1700_B<RuinedPortalConfiguration> n_1700_B() {
        return J_1907_R::new;
    }

    private static boolean n_1700_B(c_1514_x p_236337_0_, k_594_Q p_236337_1_) {
        return p_236337_1_.n_1700_B(p_236337_0_) < 0.15f;
    }

    private static int n_1700_B(Random p_236339_0_, z_1753_f p_236339_1_, h_4966_V.n_1700_B p_236339_2_, boolean p_236339_3_, int p_236339_4_, int p_236339_5_, BoundingBox p_236339_6_) {
        int k;
        if (p_236339_2_ == h_4966_V.n_1700_B.u_1723_Y) {
            i = p_236339_3_ ? I_892_V.n_1700_B(p_236339_0_, 32, 100) : (p_236339_0_.nextFloat() < 0.5f ? I_892_V.n_1700_B(p_236339_0_, 27, 29) : I_892_V.n_1700_B(p_236339_0_, 29, 100));
        } else if (p_236339_2_ == h_4966_V.n_1700_B.G_564_y) {
            int j = p_236339_4_ - p_236339_5_;
            i = I_892_V.J_1907_R(p_236339_0_, 70, j);
        } else if (p_236339_2_ == h_4966_V.n_1700_B.P_1922_E) {
            int i1 = p_236339_4_ - p_236339_5_;
            i = I_892_V.J_1907_R(p_236339_0_, 15, i1);
        } else {
            i = p_236339_2_ == h_4966_V.n_1700_B.J_1907_R ? p_236339_4_ - p_236339_5_ + I_892_V.n_1700_B(p_236339_0_, 2, 8) : p_236339_4_;
        }
        ImmutableList list1 = ImmutableList.of((Object)new c_1514_x(p_236339_6_.n_1700_B, 0, p_236339_6_.R_4764_Y), (Object)new c_1514_x(p_236339_6_.G_564_y, 0, p_236339_6_.R_4764_Y), (Object)new c_1514_x(p_236339_6_.n_1700_B, 0, p_236339_6_.u_1723_Y), (Object)new c_1514_x(p_236339_6_.G_564_y, 0, p_236339_6_.u_1723_Y));
        List list = list1.stream().map(p_236333_1_ -> p_236339_1_.n_1700_B(p_236333_1_.getX(), p_236333_1_.getZ())).collect(Collectors.toList());
        z_2963_s.n_1700_B heightmap$type = p_236339_2_ == h_4966_V.n_1700_B.R_4764_Y ? z_2963_s.n_1700_B.R_4764_Y : z_2963_s.n_1700_B.n_1700_B;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (k = i; k > 15; --k) {
            int l = 0;
            blockpos$mutable.n_1700_B(0, k, 0);
            for (BlockGetter iblockreader : list) {
                K_4074_S blockstate = iblockreader.getBlockState(blockpos$mutable);
                if (blockstate == null || !heightmap$type.P_1922_E().test(blockstate) || ++l != 3) continue;
                return k;
            }
        }
        return k;
    }

    private static int n_1700_B(Random p_236335_0_, int p_236335_1_, int p_236335_2_) {
        return p_236335_0_.nextInt(p_236335_2_ - p_236335_1_ + 1) + p_236335_1_;
    }

    private static int J_1907_R(Random p_236338_0_, int p_236338_1_, int p_236338_2_) {
        return p_236338_1_ < p_236338_2_ ? I_892_V.n_1700_B(p_236338_0_, p_236338_1_, p_236338_2_) : p_236338_2_;
    }

    public static class J_1907_R
    extends StructureStart<RuinedPortalConfiguration> {
        protected J_1907_R(StructureFeature<RuinedPortalConfiguration> p_i231985_1_, int p_i231985_2_, int p_i231985_3_, BoundingBox p_i231985_4_, int p_i231985_5_, long p_i231985_6_) {
            super(p_i231985_1_, p_i231985_2_, p_i231985_3_, p_i231985_4_, p_i231985_5_, p_i231985_6_);
        }

        @Override
        public void n_1700_B(r_4097_j p_230364_1_, z_1753_f p_230364_2_, b_2085_h p_230364_3_, int p_230364_4_, int p_230364_5_, k_594_Q p_230364_6_, RuinedPortalConfiguration p_230364_7_) {
            h_4966_V.n_1700_B ruinedportalpiece$location;
            h_4966_V.J_1907_R ruinedportalpiece$serializer = new h_4966_V.J_1907_R();
            if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.J_1907_R) {
                ruinedportalpiece$location = h_4966_V.n_1700_B.J_1907_R;
                ruinedportalpiece$serializer.G_564_y = false;
                ruinedportalpiece$serializer.R_4764_Y = 0.0f;
            } else if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.R_4764_Y) {
                ruinedportalpiece$location = h_4966_V.n_1700_B.n_1700_B;
                ruinedportalpiece$serializer.G_564_y = this.G_564_y.nextFloat() < 0.5f;
                ruinedportalpiece$serializer.R_4764_Y = 0.8f;
                ruinedportalpiece$serializer.P_1922_E = true;
                ruinedportalpiece$serializer.u_1723_Y = true;
            } else if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.G_564_y) {
                ruinedportalpiece$location = h_4966_V.n_1700_B.R_4764_Y;
                ruinedportalpiece$serializer.G_564_y = false;
                ruinedportalpiece$serializer.R_4764_Y = 0.5f;
                ruinedportalpiece$serializer.u_1723_Y = true;
            } else if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.P_1922_E) {
                boolean flag = this.G_564_y.nextFloat() < 0.5f;
                ruinedportalpiece$location = flag ? h_4966_V.n_1700_B.G_564_y : h_4966_V.n_1700_B.n_1700_B;
                ruinedportalpiece$serializer.G_564_y = flag || this.G_564_y.nextFloat() < 0.5f;
            } else if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.u_1723_Y) {
                ruinedportalpiece$location = h_4966_V.n_1700_B.R_4764_Y;
                ruinedportalpiece$serializer.G_564_y = false;
                ruinedportalpiece$serializer.R_4764_Y = 0.8f;
            } else if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.v_4262_N) {
                ruinedportalpiece$location = h_4966_V.n_1700_B.u_1723_Y;
                ruinedportalpiece$serializer.G_564_y = this.G_564_y.nextFloat() < 0.5f;
                ruinedportalpiece$serializer.R_4764_Y = 0.0f;
                ruinedportalpiece$serializer.v_4262_N = true;
            } else {
                boolean flag1 = this.G_564_y.nextFloat() < 0.5f;
                ruinedportalpiece$location = flag1 ? h_4966_V.n_1700_B.P_1922_E : h_4966_V.n_1700_B.n_1700_B;
                ruinedportalpiece$serializer.G_564_y = flag1 || this.G_564_y.nextFloat() < 0.5f;
            }
            g_2336_b resourcelocation = this.G_564_y.nextFloat() < 0.05f ? new g_2336_b(Q_2552_b[this.G_564_y.nextInt(Q_2552_b.length)]) : new g_2336_b(Y_259_p[this.G_564_y.nextInt(Y_259_p.length)]);
            a_2886_t template = p_230364_3_.n_1700_B(resourcelocation);
            W_2163_m rotation = j_3341_s.n_1700_B(W_2163_m.values(), (Random)this.G_564_y);
            q_4099_E mirror = this.G_564_y.nextFloat() < 0.5f ? q_4099_E.n_1700_B : q_4099_E.R_4764_Y;
            c_1514_x blockpos = new c_1514_x(template.n_1700_B().getX() / 2, 0, template.n_1700_B().getZ() / 2);
            c_1514_x blockpos1 = new Y_1387_d(p_230364_4_, p_230364_5_).s_956_w();
            BoundingBox mutableboundingbox = template.n_1700_B(blockpos1, rotation, blockpos, mirror);
            z_3539_x vector3i = mutableboundingbox.v_4262_N();
            int i = vector3i.getX();
            int j = vector3i.getZ();
            int k = p_230364_2_.n_1700_B(i, j, h_4966_V.n_1700_B(ruinedportalpiece$location)) - 1;
            int l = I_892_V.n_1700_B(this.G_564_y, p_230364_2_, ruinedportalpiece$location, ruinedportalpiece$serializer.G_564_y, k, mutableboundingbox.P_1922_E(), mutableboundingbox);
            c_1514_x blockpos2 = new c_1514_x(blockpos1.getX(), l, blockpos1.getZ());
            if (p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.P_1922_E || p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.u_1723_Y || p_230364_7_.J_1907_R == lightning.product.I_892_V$n_1700_B.n_1700_B) {
                ruinedportalpiece$serializer.J_1907_R = I_892_V.n_1700_B(blockpos2, p_230364_6_);
            }
            this.J_1907_R.add(new h_4966_V(blockpos2, ruinedportalpiece$location, ruinedportalpiece$serializer, resourcelocation, template, rotation, mirror, blockpos));
            this.J_1907_R();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements E_4700_p {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("standard");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("desert");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("jungle");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("swamp");
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B("mountain");
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B("ocean");
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B("nether");
        public static final Codec<n_1700_B> w_1484_f;
        private static final Map<String, n_1700_B> t_148_a;
        private final String s_956_w;
        private static final /* synthetic */ n_1700_B[] u_2550_I;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_2550_I.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i231986_3_) {
            this.s_956_w = p_i231986_3_;
        }

        public String J_1907_R() {
            return this.s_956_w;
        }

        public static n_1700_B n_1700_B(String p_236346_0_) {
            return t_148_a.get(p_236346_0_);
        }

        @Override
        public String n_1700_B() {
            return this.s_956_w;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            u_2550_I = lightning.product.I_892_V$n_1700_B.R_4764_Y();
            w_1484_f = E_4700_p.n_1700_B(n_1700_B::values, n_1700_B::n_1700_B);
            t_148_a = Arrays.stream(lightning.product.I_892_V$n_1700_B.values()).collect(Collectors.toMap(n_1700_B::J_1907_R, p_236345_0_ -> p_236345_0_));
        }
    }
}


