/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.ShipwreckConfiguration;
import lightning.product.U_2912_j;
import lightning.product.V_4572_l;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.o_4810_o;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class N_2400_q {
    private static final c_1514_x n_1700_B = new c_1514_x(4, 0, 15);
    private static final g_2336_b[] J_1907_R = new g_2336_b[]{new g_2336_b("shipwreck/with_mast"), new g_2336_b("shipwreck/sideways_full"), new g_2336_b("shipwreck/sideways_fronthalf"), new g_2336_b("shipwreck/sideways_backhalf"), new g_2336_b("shipwreck/rightsideup_full"), new g_2336_b("shipwreck/rightsideup_fronthalf"), new g_2336_b("shipwreck/rightsideup_backhalf"), new g_2336_b("shipwreck/with_mast_degraded"), new g_2336_b("shipwreck/rightsideup_full_degraded"), new g_2336_b("shipwreck/rightsideup_fronthalf_degraded"), new g_2336_b("shipwreck/rightsideup_backhalf_degraded")};
    private static final g_2336_b[] R_4764_Y = new g_2336_b[]{new g_2336_b("shipwreck/with_mast"), new g_2336_b("shipwreck/upsidedown_full"), new g_2336_b("shipwreck/upsidedown_fronthalf"), new g_2336_b("shipwreck/upsidedown_backhalf"), new g_2336_b("shipwreck/sideways_full"), new g_2336_b("shipwreck/sideways_fronthalf"), new g_2336_b("shipwreck/sideways_backhalf"), new g_2336_b("shipwreck/rightsideup_full"), new g_2336_b("shipwreck/rightsideup_fronthalf"), new g_2336_b("shipwreck/rightsideup_backhalf"), new g_2336_b("shipwreck/with_mast_degraded"), new g_2336_b("shipwreck/upsidedown_full_degraded"), new g_2336_b("shipwreck/upsidedown_fronthalf_degraded"), new g_2336_b("shipwreck/upsidedown_backhalf_degraded"), new g_2336_b("shipwreck/sideways_full_degraded"), new g_2336_b("shipwreck/sideways_fronthalf_degraded"), new g_2336_b("shipwreck/sideways_backhalf_degraded"), new g_2336_b("shipwreck/rightsideup_full_degraded"), new g_2336_b("shipwreck/rightsideup_fronthalf_degraded"), new g_2336_b("shipwreck/rightsideup_backhalf_degraded")};

    public static void n_1700_B(b_2085_h p_204760_0_, c_1514_x p_204760_1_, W_2163_m p_204760_2_, List<E_3771_B> p_204760_3_, Random p_204760_4_, ShipwreckConfiguration p_204760_5_) {
        g_2336_b resourcelocation = j_3341_s.n_1700_B(p_204760_5_.J_1907_R ? J_1907_R : R_4764_Y, p_204760_4_);
        p_204760_3_.add(new n_1700_B(p_204760_0_, resourcelocation, p_204760_1_, p_204760_2_, p_204760_5_.J_1907_R));
    }

    public static class n_1700_B
    extends q_1616_l {
        private final W_2163_m G_564_y;
        private final g_2336_b P_1922_E;
        private final boolean u_1723_Y;

        public n_1700_B(b_2085_h p_i48904_1_, g_2336_b p_i48904_2_, c_1514_x p_i48904_3_, W_2163_m p_i48904_4_, boolean p_i48904_5_) {
            super(StructurePieceType.D_4792_h, 0);
            this.R_4764_Y = p_i48904_3_;
            this.G_564_y = p_i48904_4_;
            this.P_1922_E = p_i48904_2_;
            this.u_1723_Y = p_i48904_5_;
            this.n_1700_B(p_i48904_1_);
        }

        public n_1700_B(b_2085_h p_i50445_1_, U_2912_j p_i50445_2_) {
            super(StructurePieceType.D_4792_h, p_i50445_2_);
            this.P_1922_E = new g_2336_b(p_i50445_2_.M_588_G("Template"));
            this.u_1723_Y = p_i50445_2_.t_1786_h("isBeached");
            this.G_564_y = W_2163_m.valueOf(p_i50445_2_.M_588_G("Rot"));
            this.n_1700_B(p_i50445_1_);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Template", this.P_1922_E.toString());
            tagCompound.n_1700_B("isBeached", this.u_1723_Y);
            tagCompound.n_1700_B("Rot", this.G_564_y.name());
        }

        private void n_1700_B(b_2085_h p_204754_1_) {
            a_2886_t template = p_204754_1_.n_1700_B(this.P_1922_E);
            w_1748_S placementsettings = new w_1748_S().n_1700_B(this.G_564_y).n_1700_B(q_4099_E.n_1700_B).n_1700_B(n_1700_B).n_1700_B(r_4719_P.G_564_y);
            this.n_1700_B(template, this.R_4764_Y, placementsettings);
        }

        @Override
        protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
            if ("map_chest".equals(function)) {
                V_4572_l.n_1700_B(worldIn, rand, pos.down(), o_4810_o.n_3318_d);
            } else if ("treasure_chest".equals(function)) {
                V_4572_l.n_1700_B(worldIn, rand, pos.down(), o_4810_o.z_1737_N);
            } else if ("supply_chest".equals(function)) {
                V_4572_l.n_1700_B(worldIn, rand, pos.down(), o_4810_o.d_2427_y);
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            int i = 256;
            int j = 0;
            c_1514_x blockpos = this.n_1700_B.n_1700_B();
            z_2963_s.n_1700_B heightmap$type = this.u_1723_Y ? z_2963_s.n_1700_B.n_1700_B : z_2963_s.n_1700_B.R_4764_Y;
            int k = blockpos.getX() * blockpos.getZ();
            if (k == 0) {
                j = p_230383_1_.n_1700_B(heightmap$type, this.R_4764_Y.getX(), this.R_4764_Y.getZ());
            } else {
                c_1514_x blockpos1 = this.R_4764_Y.add(blockpos.getX() - 1, 0, blockpos.getZ() - 1);
                for (c_1514_x blockpos2 : c_1514_x.getAllInBoxMutable(this.R_4764_Y, blockpos1)) {
                    int l = p_230383_1_.n_1700_B(heightmap$type, blockpos2.getX(), blockpos2.getZ());
                    j += l;
                    i = Math.min(i, l);
                }
                j /= k;
            }
            int i1 = this.u_1723_Y ? i - blockpos.getY() / 2 - p_230383_4_.nextInt(3) : j;
            this.R_4764_Y = new c_1514_x(this.R_4764_Y.getX(), i1, this.R_4764_Y.getZ());
            return super.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
        }
    }
}


