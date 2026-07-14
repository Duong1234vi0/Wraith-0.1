/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.E_872_n;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.d_2484_X;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.SwordItem;

public class BambooSaplingBlock
extends T_2915_h
implements BonemealableBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 12.0, 12.0);

    public BambooSaplingBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.J_1907_R;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        e_2866_D vector3d = state.h_1847_R(worldIn, pos);
        return P_4830_p.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (random.nextInt(3) == 0 && worldIn.u_1723_Y(pos.up()) && worldIn.n_1700_B(pos.up(), 0) >= 9) {
            this.n_1700_B(worldIn, pos);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return worldIn.getBlockState(pos.down()).n_1700_B(BlockTags.s_2632_s);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        if (facing == b_257_Y.J_1907_R && facingState.n_1700_B(a_3742_W.t_1509_b)) {
            worldIn.n_1700_B(currentPos, a_3742_W.t_1509_b.multiplayerClientSuggestionProvider(), 2);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(Items.H_1883_T);
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).v_4262_N();
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        this.n_1700_B(worldIn, pos);
    }

    @Override
    public float n_1700_B(K_4074_S state, a_3913_L player, BlockGetter worldIn, c_1514_x pos) {
        return player.A_2714_y().J_1907_R() instanceof SwordItem ? 1.0f : super.n_1700_B(state, player, worldIn, pos);
    }

    protected void n_1700_B(b_4507_u world, c_1514_x state) {
        world.n_1700_B(state.up(), (K_4074_S)a_3742_W.t_1509_b.multiplayerClientSuggestionProvider().n_1700_B(E_872_n.t_1786_h, d_2484_X.J_1907_R), 3);
    }
}


