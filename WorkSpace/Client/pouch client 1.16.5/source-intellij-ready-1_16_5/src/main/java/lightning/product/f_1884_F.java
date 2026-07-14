/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lightning.product.E_3771_B;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.ServerLevelAccessor;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.o_4810_o;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.t_693_s;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class f_1884_F {
    private static final g_2336_b n_1700_B = new g_2336_b("igloo/top");
    private static final g_2336_b J_1907_R = new g_2336_b("igloo/middle");
    private static final g_2336_b R_4764_Y = new g_2336_b("igloo/bottom");
    private static final Map<g_2336_b, c_1514_x> G_564_y = ImmutableMap.of((Object)n_1700_B, (Object)new c_1514_x(3, 5, 5), (Object)J_1907_R, (Object)new c_1514_x(1, 3, 1), (Object)R_4764_Y, (Object)new c_1514_x(3, 6, 7));
    private static final Map<g_2336_b, c_1514_x> P_1922_E = ImmutableMap.of((Object)n_1700_B, (Object)c_1514_x.ZERO, (Object)J_1907_R, (Object)new c_1514_x(2, -3, 4), (Object)R_4764_Y, (Object)new c_1514_x(0, -3, -2));

    public static void n_1700_B(b_2085_h p_236991_0_, c_1514_x p_236991_1_, W_2163_m p_236991_2_, List<E_3771_B> p_236991_3_, Random p_236991_4_) {
        if (p_236991_4_.nextDouble() < 0.5) {
            int i = p_236991_4_.nextInt(8) + 4;
            p_236991_3_.add(new n_1700_B(p_236991_0_, R_4764_Y, p_236991_1_, p_236991_2_, i * 3));
            for (int j = 0; j < i - 1; ++j) {
                p_236991_3_.add(new n_1700_B(p_236991_0_, J_1907_R, p_236991_1_, p_236991_2_, j * 3));
            }
        }
        p_236991_3_.add(new n_1700_B(p_236991_0_, n_1700_B, p_236991_1_, p_236991_2_, 0));
    }

    public static class n_1700_B
    extends q_1616_l {
        private final g_2336_b G_564_y;
        private final W_2163_m P_1922_E;

        public n_1700_B(b_2085_h p_i49313_1_, g_2336_b p_i49313_2_, c_1514_x p_i49313_3_, W_2163_m p_i49313_4_, int p_i49313_5_) {
            super(StructurePieceType.d_2427_y, 0);
            this.G_564_y = p_i49313_2_;
            c_1514_x blockpos = P_1922_E.get(p_i49313_2_);
            this.R_4764_Y = p_i49313_3_.add(blockpos.getX(), blockpos.getY() - p_i49313_5_, blockpos.getZ());
            this.P_1922_E = p_i49313_4_;
            this.n_1700_B(p_i49313_1_);
        }

        public n_1700_B(b_2085_h p_i50566_1_, U_2912_j p_i50566_2_) {
            super(StructurePieceType.d_2427_y, p_i50566_2_);
            this.G_564_y = new g_2336_b(p_i50566_2_.M_588_G("Template"));
            this.P_1922_E = W_2163_m.valueOf(p_i50566_2_.M_588_G("Rot"));
            this.n_1700_B(p_i50566_1_);
        }

        private void n_1700_B(b_2085_h p_207614_1_) {
            a_2886_t template = p_207614_1_.n_1700_B(this.G_564_y);
            w_1748_S placementsettings = new w_1748_S().n_1700_B(this.P_1922_E).n_1700_B(q_4099_E.n_1700_B).n_1700_B(G_564_y.get(this.G_564_y)).n_1700_B(r_4719_P.J_1907_R);
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
            if ("chest".equals(function)) {
                worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 3);
                i_2154_H tileentity = worldIn.getTileEntity(pos.down());
                if (tileentity instanceof t_693_s) {
                    ((t_693_s)tileentity).n_1700_B(o_4810_o.A_4115_X, rand.nextLong());
                }
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            c_1514_x blockpos3;
            K_4074_S blockstate;
            w_1748_S placementsettings = new w_1748_S().n_1700_B(this.P_1922_E).n_1700_B(q_4099_E.n_1700_B).n_1700_B(G_564_y.get(this.G_564_y)).n_1700_B(r_4719_P.J_1907_R);
            c_1514_x blockpos = P_1922_E.get(this.G_564_y);
            c_1514_x blockpos1 = this.R_4764_Y.add(a_2886_t.n_1700_B(placementsettings, new c_1514_x(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            int i = p_230383_1_.n_1700_B(z_2963_s.n_1700_B.n_1700_B, blockpos1.getX(), blockpos1.getZ());
            c_1514_x blockpos2 = this.R_4764_Y;
            this.R_4764_Y = this.R_4764_Y.add(0, i - 90 - 1, 0);
            boolean flag = super.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
            if (this.G_564_y.equals(n_1700_B) && !(blockstate = p_230383_1_.getBlockState((blockpos3 = this.R_4764_Y.add(a_2886_t.n_1700_B(placementsettings, new c_1514_x(3, 0, 5)))).down())).v_4262_N() && !blockstate.n_1700_B(a_3742_W.L_3570_A)) {
                p_230383_1_.n_1700_B(blockpos3, a_3742_W.l_697_B.multiplayerClientSuggestionProvider(), 3);
            }
            this.R_4764_Y = blockpos2;
            return flag;
        }
    }
}


