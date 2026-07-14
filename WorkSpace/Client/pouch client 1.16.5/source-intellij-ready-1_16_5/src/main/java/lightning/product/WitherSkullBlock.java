/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.I_3700_V;
import lightning.product.K_4074_S;
import lightning.product.BlockPattern;
import lightning.product.O_2639_P;
import lightning.product.R_2450_T;
import lightning.product.T_2915_h;
import lightning.product.BlockMaterialPredicate;
import lightning.product.U_3554_Q;
import lightning.product.SkullBlock;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_91_Z;
import lightning.product.g_3049_G;
import lightning.product.i_2154_H;
import lightning.product.BlockInWorld;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.Material;
import lightning.product.t_5_h;

public class WitherSkullBlock
extends SkullBlock {
    @Nullable
    private static BlockPattern Q_4569_t;
    @Nullable
    private static BlockPattern M_182_A;

    protected WitherSkullBlock(q_4293_E.P_1922_E properties) {
        super(SkullBlock.J_1907_R.J_1907_R, properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        super.n_1700_B(worldIn, pos, state, placer, stack);
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof O_2639_P) {
            WitherSkullBlock.n_1700_B(worldIn, pos, (O_2639_P)tileentity);
        }
    }

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, O_2639_P tileEntity) {
        if (!worldIn.Y_259_p) {
            BlockPattern blockpattern;
            BlockPattern.J_1907_R blockpattern$patternhelper;
            boolean flag;
            K_4074_S blockstate = tileEntity.e_4240_b();
            boolean bl = flag = blockstate.n_1700_B(a_3742_W.ModuleCategory) || blockstate.n_1700_B(a_3742_W.p_1458_L);
            if (flag && pos.getY() >= 0 && worldIn.x_607_J() != R_2450_T.n_1700_B && (blockpattern$patternhelper = (blockpattern = WitherSkullBlock.t_148_a()).n_1700_B(worldIn, pos)) != null) {
                for (int i = 0; i < blockpattern.R_4764_Y(); ++i) {
                    for (int j = 0; j < blockpattern.J_1907_R(); ++j) {
                        BlockInWorld cachedblockinfo = blockpattern$patternhelper.n_1700_B(i, j, 0);
                        worldIn.n_1700_B(cachedblockinfo.G_564_y(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 2);
                        worldIn.R_4764_Y(2001, cachedblockinfo.G_564_y(), T_2915_h.s_956_w(cachedblockinfo.n_1700_B()));
                    }
                }
                I_3700_V witherentity = t_5_h.r_3651_U.n_1700_B(worldIn);
                c_1514_x blockpos = blockpattern$patternhelper.n_1700_B(1, 2, 0).G_564_y();
                witherentity.J_1907_R((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.55, (double)blockpos.getZ() + 0.5, blockpattern$patternhelper.J_1907_R().h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? 0.0f : 90.0f, 0.0f);
                witherentity.C_1162_e = blockpattern$patternhelper.J_1907_R().h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? 0.0f : 90.0f;
                witherentity.u_1723_Y();
                for (B_4088_l serverplayerentity : worldIn.n_1700_B(B_4088_l.class, witherentity.i_601_W().grow(50.0))) {
                    U_3554_Q.h_1847_R.n_1700_B(serverplayerentity, witherentity);
                }
                worldIn.a_(witherentity);
                for (int k = 0; k < blockpattern.R_4764_Y(); ++k) {
                    for (int l = 0; l < blockpattern.J_1907_R(); ++l) {
                        worldIn.n_1700_B(blockpattern$patternhelper.n_1700_B(k, l, 0).G_564_y(), a_3742_W.n_1700_B);
                    }
                }
            }
        }
    }

    public static boolean J_1907_R(b_4507_u world, c_1514_x pos, Z_1993_T stack) {
        if (stack.J_1907_R() == Items.DropperBlock && pos.getY() >= 2 && world.x_607_J() != R_2450_T.n_1700_B && !world.Y_259_p) {
            return WitherSkullBlock.s_956_w().n_1700_B(world, pos) != null;
        }
        return false;
    }

    private static BlockPattern t_148_a() {
        if (Q_4569_t == null) {
            Q_4569_t = e_91_Z.n_1700_B().n_1700_B("^^^", "###", "~#~").n_1700_B('#', (BlockInWorld cachedInfo) -> cachedInfo.n_1700_B().n_1700_B(BlockTags.i_1637_u)).n_1700_B('^', BlockInWorld.n_1700_B(g_3049_G.n_1700_B(a_3742_W.ModuleCategory).or(g_3049_G.n_1700_B(a_3742_W.p_1458_L)))).n_1700_B('~', BlockInWorld.n_1700_B(BlockMaterialPredicate.n_1700_B(Material.n_1700_B))).J_1907_R();
        }
        return Q_4569_t;
    }

    private static BlockPattern s_956_w() {
        if (M_182_A == null) {
            M_182_A = e_91_Z.n_1700_B().n_1700_B("   ", "###", "~#~").n_1700_B('#', (BlockInWorld cachedInfo) -> cachedInfo.n_1700_B().n_1700_B(BlockTags.i_1637_u)).n_1700_B('~', BlockInWorld.n_1700_B(BlockMaterialPredicate.n_1700_B(Material.n_1700_B))).J_1907_R();
        }
        return M_182_A;
    }
}



