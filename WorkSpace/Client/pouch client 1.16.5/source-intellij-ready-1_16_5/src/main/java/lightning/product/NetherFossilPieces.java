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
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;

public class NetherFossilPieces {
    private static final g_2336_b[] n_1700_B = new g_2336_b[]{new g_2336_b("nether_fossils/fossil_1"), new g_2336_b("nether_fossils/fossil_2"), new g_2336_b("nether_fossils/fossil_3"), new g_2336_b("nether_fossils/fossil_4"), new g_2336_b("nether_fossils/fossil_5"), new g_2336_b("nether_fossils/fossil_6"), new g_2336_b("nether_fossils/fossil_7"), new g_2336_b("nether_fossils/fossil_8"), new g_2336_b("nether_fossils/fossil_9"), new g_2336_b("nether_fossils/fossil_10"), new g_2336_b("nether_fossils/fossil_11"), new g_2336_b("nether_fossils/fossil_12"), new g_2336_b("nether_fossils/fossil_13"), new g_2336_b("nether_fossils/fossil_14")};

    public static void n_1700_B(b_2085_h p_236994_0_, List<E_3771_B> p_236994_1_, Random p_236994_2_, c_1514_x p_236994_3_) {
        W_2163_m rotation = W_2163_m.n_1700_B(p_236994_2_);
        p_236994_1_.add(new n_1700_B(p_236994_0_, j_3341_s.n_1700_B(n_1700_B, p_236994_2_), p_236994_3_, rotation));
    }

    public static class n_1700_B
    extends q_1616_l {
        private final g_2336_b G_564_y;
        private final W_2163_m P_1922_E;

        public n_1700_B(b_2085_h p_i232108_1_, g_2336_b p_i232108_2_, c_1514_x p_i232108_3_, W_2163_m p_i232108_4_) {
            super(StructurePieceType.s_2632_s, 0);
            this.G_564_y = p_i232108_2_;
            this.R_4764_Y = p_i232108_3_;
            this.P_1922_E = p_i232108_4_;
            this.n_1700_B(p_i232108_1_);
        }

        public n_1700_B(b_2085_h p_i232107_1_, U_2912_j p_i232107_2_) {
            super(StructurePieceType.s_2632_s, p_i232107_2_);
            this.G_564_y = new g_2336_b(p_i232107_2_.M_588_G("Template"));
            this.P_1922_E = W_2163_m.valueOf(p_i232107_2_.M_588_G("Rot"));
            this.n_1700_B(p_i232107_1_);
        }

        private void n_1700_B(b_2085_h p_236997_1_) {
            a_2886_t template = p_236997_1_.n_1700_B(this.G_564_y);
            w_1748_S placementsettings = new w_1748_S().n_1700_B(this.P_1922_E).n_1700_B(q_4099_E.n_1700_B).n_1700_B(r_4719_P.G_564_y);
            this.n_1700_B(template, this.R_4764_Y, placementsettings);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Template", this.G_564_y.toString());
            tagCompound.n_1700_B("Rot", this.P_1922_E.name());
        }

        @Override
        protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            p_230383_5_.J_1907_R(this.n_1700_B.J_1907_R(this.J_1907_R, this.R_4764_Y));
            return super.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
        }
    }
}


