/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.E_3771_B;
import lightning.product.BlockGetter;
import lightning.product.J_3017_d;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.S_3848_S;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.OceanRuinConfiguration;
import lightning.product.W_2163_m;
import lightning.product.Y_1387_d;
import lightning.product.a_2886_t;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.FluidState;
import lightning.product.ServerLevelAccessor;
import lightning.product.d_2489_R;
import lightning.product.d_862_x;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.BlockTags;
import lightning.product.o_4810_o;
import lightning.product.q_1616_l;
import lightning.product.q_4099_E;
import lightning.product.r_4719_P;
import lightning.product.t_5_h;
import lightning.product.t_693_s;
import lightning.product.u_530_F;
import lightning.product.v_3445_Z;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class OceanRuinPieces {
    private static final g_2336_b[] n_1700_B = new g_2336_b[]{new g_2336_b("underwater_ruin/warm_1"), new g_2336_b("underwater_ruin/warm_2"), new g_2336_b("underwater_ruin/warm_3"), new g_2336_b("underwater_ruin/warm_4"), new g_2336_b("underwater_ruin/warm_5"), new g_2336_b("underwater_ruin/warm_6"), new g_2336_b("underwater_ruin/warm_7"), new g_2336_b("underwater_ruin/warm_8")};
    private static final g_2336_b[] J_1907_R = new g_2336_b[]{new g_2336_b("underwater_ruin/brick_1"), new g_2336_b("underwater_ruin/brick_2"), new g_2336_b("underwater_ruin/brick_3"), new g_2336_b("underwater_ruin/brick_4"), new g_2336_b("underwater_ruin/brick_5"), new g_2336_b("underwater_ruin/brick_6"), new g_2336_b("underwater_ruin/brick_7"), new g_2336_b("underwater_ruin/brick_8")};
    private static final g_2336_b[] R_4764_Y = new g_2336_b[]{new g_2336_b("underwater_ruin/cracked_1"), new g_2336_b("underwater_ruin/cracked_2"), new g_2336_b("underwater_ruin/cracked_3"), new g_2336_b("underwater_ruin/cracked_4"), new g_2336_b("underwater_ruin/cracked_5"), new g_2336_b("underwater_ruin/cracked_6"), new g_2336_b("underwater_ruin/cracked_7"), new g_2336_b("underwater_ruin/cracked_8")};
    private static final g_2336_b[] G_564_y = new g_2336_b[]{new g_2336_b("underwater_ruin/mossy_1"), new g_2336_b("underwater_ruin/mossy_2"), new g_2336_b("underwater_ruin/mossy_3"), new g_2336_b("underwater_ruin/mossy_4"), new g_2336_b("underwater_ruin/mossy_5"), new g_2336_b("underwater_ruin/mossy_6"), new g_2336_b("underwater_ruin/mossy_7"), new g_2336_b("underwater_ruin/mossy_8")};
    private static final g_2336_b[] P_1922_E = new g_2336_b[]{new g_2336_b("underwater_ruin/big_brick_1"), new g_2336_b("underwater_ruin/big_brick_2"), new g_2336_b("underwater_ruin/big_brick_3"), new g_2336_b("underwater_ruin/big_brick_8")};
    private static final g_2336_b[] u_1723_Y = new g_2336_b[]{new g_2336_b("underwater_ruin/big_mossy_1"), new g_2336_b("underwater_ruin/big_mossy_2"), new g_2336_b("underwater_ruin/big_mossy_3"), new g_2336_b("underwater_ruin/big_mossy_8")};
    private static final g_2336_b[] v_4262_N = new g_2336_b[]{new g_2336_b("underwater_ruin/big_cracked_1"), new g_2336_b("underwater_ruin/big_cracked_2"), new g_2336_b("underwater_ruin/big_cracked_3"), new g_2336_b("underwater_ruin/big_cracked_8")};
    private static final g_2336_b[] w_1484_f = new g_2336_b[]{new g_2336_b("underwater_ruin/big_warm_4"), new g_2336_b("underwater_ruin/big_warm_5"), new g_2336_b("underwater_ruin/big_warm_6"), new g_2336_b("underwater_ruin/big_warm_7")};

    private static g_2336_b n_1700_B(Random rand) {
        return j_3341_s.n_1700_B(n_1700_B, rand);
    }

    private static g_2336_b J_1907_R(Random rand) {
        return j_3341_s.n_1700_B(w_1484_f, rand);
    }

    public static void n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn, List<E_3771_B> pieces, Random rand, OceanRuinConfiguration config) {
        boolean flag = rand.nextFloat() <= config.R_4764_Y;
        float f = flag ? 0.9f : 0.8f;
        OceanRuinPieces.n_1700_B(templateManagerIn, pos, rotationIn, pieces, rand, config, flag, f);
        if (flag && rand.nextFloat() <= config.G_564_y) {
            OceanRuinPieces.n_1700_B(templateManagerIn, rand, rotationIn, pos, config, pieces);
        }
    }

    private static void n_1700_B(b_2085_h p_204047_0_, Random p_204047_1_, W_2163_m p_204047_2_, c_1514_x p_204047_3_, OceanRuinConfiguration p_204047_4_, List<E_3771_B> p_204047_5_) {
        int i = p_204047_3_.getX();
        int j = p_204047_3_.getZ();
        c_1514_x blockpos = a_2886_t.n_1700_B(new c_1514_x(15, 0, 15), q_4099_E.n_1700_B, p_204047_2_, c_1514_x.ZERO).add(i, 0, j);
        BoundingBox mutableboundingbox = BoundingBox.n_1700_B(i, 0, j, blockpos.getX(), 0, blockpos.getZ());
        c_1514_x blockpos1 = new c_1514_x(Math.min(i, blockpos.getX()), 0, Math.min(j, blockpos.getZ()));
        List<c_1514_x> list = OceanRuinPieces.n_1700_B(p_204047_1_, blockpos1.getX(), blockpos1.getZ());
        int k = u_530_F.n_1700_B(p_204047_1_, 4, 8);
        for (int l = 0; l < k; ++l) {
            W_2163_m rotation;
            c_1514_x blockpos3;
            int k1;
            int i1;
            c_1514_x blockpos2;
            int j1;
            BoundingBox mutableboundingbox1;
            if (list.isEmpty() || (mutableboundingbox1 = BoundingBox.n_1700_B(j1 = (blockpos2 = list.remove(i1 = p_204047_1_.nextInt(list.size()))).getX(), 0, k1 = blockpos2.getZ(), (blockpos3 = a_2886_t.n_1700_B(new c_1514_x(5, 0, 6), q_4099_E.n_1700_B, rotation = W_2163_m.n_1700_B(p_204047_1_), c_1514_x.ZERO).add(j1, 0, k1)).getX(), 0, blockpos3.getZ())).n_1700_B(mutableboundingbox)) continue;
            OceanRuinPieces.n_1700_B(p_204047_0_, blockpos2, rotation, p_204047_5_, p_204047_1_, p_204047_4_, false, 0.8f);
        }
    }

    private static List<c_1514_x> n_1700_B(Random rand, int xIn, int zIn) {
        ArrayList list = Lists.newArrayList();
        list.add(new c_1514_x(xIn - 16 + u_530_F.n_1700_B(rand, 1, 8), 90, zIn + 16 + u_530_F.n_1700_B(rand, 1, 7)));
        list.add(new c_1514_x(xIn - 16 + u_530_F.n_1700_B(rand, 1, 8), 90, zIn + u_530_F.n_1700_B(rand, 1, 7)));
        list.add(new c_1514_x(xIn - 16 + u_530_F.n_1700_B(rand, 1, 8), 90, zIn - 16 + u_530_F.n_1700_B(rand, 4, 8)));
        list.add(new c_1514_x(xIn + u_530_F.n_1700_B(rand, 1, 7), 90, zIn + 16 + u_530_F.n_1700_B(rand, 1, 7)));
        list.add(new c_1514_x(xIn + u_530_F.n_1700_B(rand, 1, 7), 90, zIn - 16 + u_530_F.n_1700_B(rand, 4, 6)));
        list.add(new c_1514_x(xIn + 16 + u_530_F.n_1700_B(rand, 1, 7), 90, zIn + 16 + u_530_F.n_1700_B(rand, 3, 8)));
        list.add(new c_1514_x(xIn + 16 + u_530_F.n_1700_B(rand, 1, 7), 90, zIn + u_530_F.n_1700_B(rand, 1, 7)));
        list.add(new c_1514_x(xIn + 16 + u_530_F.n_1700_B(rand, 1, 7), 90, zIn - 16 + u_530_F.n_1700_B(rand, 4, 8)));
        return list;
    }

    private static void n_1700_B(b_2085_h templateManagerIn, c_1514_x p_204045_1_, W_2163_m p_204045_2_, List<E_3771_B> pieces, Random rand, OceanRuinConfiguration config, boolean shouldGenerateLargeVariant, float p_204045_7_) {
        if (config.J_1907_R == d_2489_R.J_1907_R.n_1700_B) {
            g_2336_b resourcelocation = shouldGenerateLargeVariant ? OceanRuinPieces.J_1907_R(rand) : OceanRuinPieces.n_1700_B(rand);
            pieces.add(new n_1700_B(templateManagerIn, resourcelocation, p_204045_1_, p_204045_2_, p_204045_7_, config.J_1907_R, shouldGenerateLargeVariant));
        } else if (config.J_1907_R == d_2489_R.J_1907_R.J_1907_R) {
            g_2336_b[] aresourcelocation2 = shouldGenerateLargeVariant ? P_1922_E : J_1907_R;
            g_2336_b[] aresourcelocation = shouldGenerateLargeVariant ? v_4262_N : R_4764_Y;
            g_2336_b[] aresourcelocation1 = shouldGenerateLargeVariant ? u_1723_Y : G_564_y;
            int i = rand.nextInt(aresourcelocation2.length);
            pieces.add(new n_1700_B(templateManagerIn, aresourcelocation2[i], p_204045_1_, p_204045_2_, p_204045_7_, config.J_1907_R, shouldGenerateLargeVariant));
            pieces.add(new n_1700_B(templateManagerIn, aresourcelocation[i], p_204045_1_, p_204045_2_, 0.7f, config.J_1907_R, shouldGenerateLargeVariant));
            pieces.add(new n_1700_B(templateManagerIn, aresourcelocation1[i], p_204045_1_, p_204045_2_, 0.5f, config.J_1907_R, shouldGenerateLargeVariant));
        }
    }

    public static class n_1700_B
    extends q_1616_l {
        private final d_2489_R.J_1907_R G_564_y;
        private final float P_1922_E;
        private final g_2336_b u_1723_Y;
        private final W_2163_m v_4262_N;
        private final boolean w_1484_f;

        public n_1700_B(b_2085_h templateManagerIn, g_2336_b templateNameIn, c_1514_x templatePositionIn, W_2163_m rotationIn, float integrityIn, d_2489_R.J_1907_R typeIn, boolean isLargeIn) {
            super(StructurePieceType.n_3318_d, 0);
            this.u_1723_Y = templateNameIn;
            this.R_4764_Y = templatePositionIn;
            this.v_4262_N = rotationIn;
            this.P_1922_E = integrityIn;
            this.G_564_y = typeIn;
            this.w_1484_f = isLargeIn;
            this.n_1700_B(templateManagerIn);
        }

        public n_1700_B(b_2085_h p_i50592_1_, U_2912_j p_i50592_2_) {
            super(StructurePieceType.n_3318_d, p_i50592_2_);
            this.u_1723_Y = new g_2336_b(p_i50592_2_.M_588_G("Template"));
            this.v_4262_N = W_2163_m.valueOf(p_i50592_2_.M_588_G("Rot"));
            this.P_1922_E = p_i50592_2_.s_956_w("Integrity");
            this.G_564_y = d_2489_R.J_1907_R.valueOf(p_i50592_2_.M_588_G("BiomeType"));
            this.w_1484_f = p_i50592_2_.t_1786_h("IsLarge");
            this.n_1700_B(p_i50592_1_);
        }

        private void n_1700_B(b_2085_h templateManagerIn) {
            a_2886_t template = templateManagerIn.n_1700_B(this.u_1723_Y);
            w_1748_S placementsettings = new w_1748_S().n_1700_B(this.v_4262_N).n_1700_B(q_4099_E.n_1700_B).n_1700_B(r_4719_P.G_564_y);
            this.n_1700_B(template, this.R_4764_Y, placementsettings);
        }

        @Override
        protected void n_1700_B(U_2912_j tagCompound) {
            super.n_1700_B(tagCompound);
            tagCompound.n_1700_B("Template", this.u_1723_Y.toString());
            tagCompound.n_1700_B("Rot", this.v_4262_N.name());
            tagCompound.n_1700_B("Integrity", this.P_1922_E);
            tagCompound.n_1700_B("BiomeType", this.G_564_y.toString());
            tagCompound.n_1700_B("IsLarge", this.w_1484_f);
        }

        @Override
        protected void n_1700_B(String function, c_1514_x pos, ServerLevelAccessor worldIn, Random rand, BoundingBox sbb) {
            if ("chest".equals(function)) {
                worldIn.n_1700_B(pos, (K_4074_S)a_3742_W.L_1362_X.multiplayerClientSuggestionProvider().n_1700_B(v_3445_Z.M_182_A, worldIn.getFluidState(pos).n_1700_B(FluidTags.J_1907_R)), 2);
                i_2154_H tileentity = worldIn.getTileEntity(pos);
                if (tileentity instanceof t_693_s) {
                    ((t_693_s)tileentity).n_1700_B(this.w_1484_f ? o_4810_o.x_607_J : o_4810_o.t_4043_B, rand.nextLong());
                }
            } else if ("drowned".equals(function)) {
                S_3848_S drownedentity = t_5_h.t_1786_h.n_1700_B(worldIn.J_1907_R());
                drownedentity.T_3594_S();
                drownedentity.n_1700_B(pos, 0.0f, 0.0f);
                drownedentity.n_1700_B(worldIn, worldIn.J_1907_R(pos), a_3160_D.G_564_y, (V_3157_k)null, null);
                worldIn.n_1700_B(drownedentity);
                if (pos.getY() > worldIn.d_2461_k()) {
                    worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
                } else {
                    worldIn.n_1700_B(pos, a_3742_W.c_3005_b.multiplayerClientSuggestionProvider(), 2);
                }
            }
        }

        @Override
        public boolean n_1700_B(WorldGenLevel p_230383_1_, J_3017_d p_230383_2_, z_1753_f p_230383_3_, Random p_230383_4_, BoundingBox p_230383_5_, Y_1387_d p_230383_6_, c_1514_x p_230383_7_) {
            this.J_1907_R.J_1907_R().n_1700_B(new d_862_x(this.P_1922_E)).n_1700_B(r_4719_P.G_564_y);
            int i = p_230383_1_.n_1700_B(z_2963_s.n_1700_B.R_4764_Y, this.R_4764_Y.getX(), this.R_4764_Y.getZ());
            this.R_4764_Y = new c_1514_x(this.R_4764_Y.getX(), i, this.R_4764_Y.getZ());
            c_1514_x blockpos = a_2886_t.n_1700_B(new c_1514_x(this.n_1700_B.n_1700_B().getX() - 1, 0, this.n_1700_B.n_1700_B().getZ() - 1), q_4099_E.n_1700_B, this.v_4262_N, c_1514_x.ZERO).add(this.R_4764_Y);
            this.R_4764_Y = new c_1514_x(this.R_4764_Y.getX(), this.n_1700_B(this.R_4764_Y, p_230383_1_, blockpos), this.R_4764_Y.getZ());
            return super.n_1700_B(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
        }

        private int n_1700_B(c_1514_x templatePos, BlockGetter blockReaderIn, c_1514_x templateTransformedPos) {
            int i = templatePos.getY();
            int j = 512;
            int k = i - 1;
            int l = 0;
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(templatePos, templateTransformedPos)) {
                int i1 = blockpos.getX();
                int j1 = blockpos.getZ();
                int k1 = templatePos.getY() - 1;
                c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(i1, k1, j1);
                K_4074_S blockstate = blockReaderIn.getBlockState(blockpos$mutable);
                FluidState fluidstate = blockReaderIn.getFluidState(blockpos$mutable);
                while ((blockstate.v_4262_N() || fluidstate.n_1700_B(FluidTags.J_1907_R) || blockstate.J_1907_R().n_1700_B(BlockTags.X_933_l)) && k1 > 1) {
                    blockpos$mutable.n_1700_B(i1, --k1, j1);
                    blockstate = blockReaderIn.getBlockState(blockpos$mutable);
                    fluidstate = blockReaderIn.getFluidState(blockpos$mutable);
                }
                j = Math.min(j, k1);
                if (k1 >= k - 2) continue;
                ++l;
            }
            int l1 = Math.abs(templatePos.getX() - templateTransformedPos.getX());
            if (k - j > 2 && l > l1 - 2) {
                i = j + 1;
            }
            return i;
        }
    }
}


