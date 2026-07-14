/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.DoublePlantBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class E_3601_d
extends BushBlock
implements BonemealableBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);

    protected E_3601_d(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return true;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock)(this == a_3742_W.RetryCallException ? a_3742_W.PotionTracker : a_3742_W.Party);
        if (doubleplantblock.multiplayerClientSuggestionProvider().n_1700_B((T_1316_M)worldIn, pos) && worldIn.u_1723_Y(pos.up())) {
            doubleplantblock.n_1700_B((LevelAccessor)worldIn, pos, 2);
        }
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.R_4764_Y;
    }
}



