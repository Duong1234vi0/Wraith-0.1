/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.V_4824_J;
import lightning.product.Y_1835_y;
import lightning.product.Y_4489_t;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.i_2154_H;
import lightning.product.k_2789_z;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.u_782_h;
import lightning.product.ComparatorBlockEntity;
import lightning.product.x_1688_C;
import lightning.product.y_740_d;

public class ComparatorBlock
extends u_782_h
implements k_2789_z {
    public static final e_563_h<Y_4489_t> P_4830_p = BlockStateProperties.RealmsWorldResetDto;

    public ComparatorBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(Q_4569_t, false)).n_1700_B(P_4830_p, Y_4489_t.n_1700_B));
    }

    @Override
    protected int w_1484_f(K_4074_S state) {
        return 2;
    }

    @Override
    protected int J_1907_R(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof ComparatorBlockEntity ? ((ComparatorBlockEntity)tileentity).P_1922_E() : 0;
    }

    private int P_1922_E(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        return state.R_4764_Y(P_4830_p) == Y_4489_t.J_1907_R ? Math.max(this.J_1907_R(worldIn, pos, state) - this.J_1907_R((T_1316_M)worldIn, pos, state), 0) : this.J_1907_R(worldIn, pos, state);
    }

    @Override
    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        int i = this.J_1907_R(worldIn, pos, state);
        if (i == 0) {
            return false;
        }
        int j = this.J_1907_R((T_1316_M)worldIn, pos, state);
        if (i > j) {
            return true;
        }
        return i == j && state.R_4764_Y(P_4830_p) == Y_4489_t.n_1700_B;
    }

    @Override
    protected int J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        int i = super.J_1907_R(worldIn, pos, state);
        b_257_Y direction = state.R_4764_Y(w_612_n);
        c_1514_x blockpos = pos.offset(direction);
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.s_956_w()) {
            i = blockstate.n_1700_B(worldIn, blockpos);
        } else if (i < 15 && blockstate.v_4262_N(worldIn, blockpos)) {
            blockpos = blockpos.offset(direction);
            blockstate = worldIn.getBlockState(blockpos);
            y_740_d itemframeentity = this.n_1700_B(worldIn, direction, blockpos);
            int j = Math.max(itemframeentity == null ? Integer.MIN_VALUE : itemframeentity.M_182_A(), blockstate.s_956_w() ? blockstate.n_1700_B(worldIn, blockpos) : Integer.MIN_VALUE);
            if (j != Integer.MIN_VALUE) {
                i = j;
            }
        }
        return i;
    }

    @Nullable
    private y_740_d n_1700_B(b_4507_u worldIn, b_257_Y facing, c_1514_x pos) {
        List<y_740_d> list = worldIn.n_1700_B(y_740_d.class, new I_4817_s(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1), (? super T itemFrame) -> itemFrame != null && itemFrame.o_2767_H() == facing);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (!player.C_415_h.P_1922_E) {
            return m_3054_I.R_4764_Y;
        }
        float f = (state = (K_4074_S)state.n_1700_B(P_4830_p)).R_4764_Y(P_4830_p) == Y_4489_t.J_1907_R ? 0.55f : 0.5f;
        worldIn.n_1700_B(player, pos, SoundEvents.L_4248_u, D_38_f.P_1922_E, 0.3f, f);
        worldIn.n_1700_B(pos, state, 2);
        this.u_1723_Y(worldIn, pos, state);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    protected void R_4764_Y(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        if (!worldIn.u_2550_I().J_1907_R(pos, this)) {
            int j;
            int i = this.P_1922_E(worldIn, pos, state);
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            int n = j = tileentity instanceof ComparatorBlockEntity ? ((ComparatorBlockEntity)tileentity).P_1922_E() : 0;
            if (i != j || state.R_4764_Y(Q_4569_t).booleanValue() != this.n_1700_B(worldIn, pos, state)) {
                V_4824_J tickpriority = this.R_4764_Y((BlockGetter)worldIn, pos, state) ? V_4824_J.R_4764_Y : V_4824_J.G_564_y;
                worldIn.u_2550_I().n_1700_B(pos, this, 2, tickpriority);
            }
        }
    }

    private void u_1723_Y(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        int i = this.P_1922_E(worldIn, pos, state);
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        int j = 0;
        if (tileentity instanceof ComparatorBlockEntity) {
            ComparatorBlockEntity comparatortileentity = (ComparatorBlockEntity)tileentity;
            j = comparatortileentity.P_1922_E();
            comparatortileentity.n_1700_B(i);
        }
        if (j != i || state.R_4764_Y(P_4830_p) == Y_4489_t.n_1700_B) {
            boolean flag1 = this.n_1700_B(worldIn, pos, state);
            boolean flag = state.R_4764_Y(Q_4569_t);
            if (flag && !flag1) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, false), 2);
            } else if (!flag && flag1) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, true), 2);
            }
            this.G_564_y(worldIn, pos, state);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        this.u_1723_Y(worldIn, pos, state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, int id, int param) {
        super.n_1700_B(state, worldIn, pos, id, param);
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.a_(id, param);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new ComparatorBlockEntity();
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p, Q_4569_t);
    }
}


