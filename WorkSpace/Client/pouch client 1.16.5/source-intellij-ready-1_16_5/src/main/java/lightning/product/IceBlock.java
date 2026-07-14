/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.K_4719_o;
import lightning.product.HalfTransparentBlock;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.Enchantments;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.Material;
import lightning.product.w_1454_v;

public class IceBlock
extends HalfTransparentBlock {
    public IceBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, a_3913_L player, c_1514_x pos, K_4074_S state, @Nullable i_2154_H te, Z_1993_T stack) {
        super.n_1700_B(worldIn, player, pos, state, te, stack);
        if (K_4096_w.n_1700_B(Enchantments.Y_259_p, stack) == 0) {
            if (worldIn.G_624_v().G_564_y()) {
                worldIn.n_1700_B(pos, false);
                return;
            }
            Material material = worldIn.getBlockState(pos.down()).R_4764_Y();
            if (material.R_4764_Y() || material.n_1700_B()) {
                worldIn.J_1907_R(pos, a_3742_W.c_3005_b.multiplayerClientSuggestionProvider());
            }
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.getLightFor(K_4719_o.J_1907_R, pos) > 11 - state.J_1907_R((BlockGetter)worldIn, pos)) {
            this.R_4764_Y(state, worldIn, pos);
        }
    }

    protected void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        if (world.G_624_v().G_564_y()) {
            world.n_1700_B(pos, false);
        } else {
            world.J_1907_R(pos, a_3742_W.c_3005_b.multiplayerClientSuggestionProvider());
            world.n_1700_B(pos, a_3742_W.c_3005_b, pos);
        }
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.n_1700_B;
    }
}


