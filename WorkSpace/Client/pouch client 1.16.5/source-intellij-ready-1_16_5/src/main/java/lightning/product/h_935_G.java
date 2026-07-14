/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;

public class h_935_G
extends T_2915_h {
    private static final Map<T_2915_h, T_2915_h> h_1847_R = Maps.newHashMap();
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    private final T_2915_h Q_4569_t;

    public h_935_G(T_2915_h block, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Q_4569_t = block;
        h_1847_R.put(block, this);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        boolean flag1;
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        q_1613_l item = itemstack.J_1907_R();
        T_2915_h block = item instanceof v_1669_V ? h_1847_R.getOrDefault(((v_1669_V)item).v_4262_N(), a_3742_W.n_1700_B) : a_3742_W.n_1700_B;
        boolean flag = block == a_3742_W.n_1700_B;
        boolean bl = flag1 = this.Q_4569_t == a_3742_W.n_1700_B;
        if (flag != flag1) {
            if (flag1) {
                worldIn.n_1700_B(pos, block.multiplayerClientSuggestionProvider(), 3);
                player.J_1907_R(Stats.r_715_M);
                if (!player.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
            } else {
                Z_1993_T itemstack1 = new Z_1993_T(this.Q_4569_t);
                if (itemstack.n_1700_B()) {
                    player.n_1700_B(handIn, itemstack1);
                } else if (!player.v_4262_N(itemstack1)) {
                    player.n_1700_B(itemstack1, false);
                }
                worldIn.n_1700_B(pos, a_3742_W.r_4790_y.multiplayerClientSuggestionProvider(), 3);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return this.Q_4569_t == a_3742_W.n_1700_B ? super.n_1700_B(worldIn, pos, state) : new Z_1993_T(this.Q_4569_t);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public T_2915_h J_1907_R() {
        return this.Q_4569_t;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


