/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.M_1398_d;
import lightning.product.N_295_T;
import lightning.product.N_81_X;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.DirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.s_3401_U;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.w_1454_v;
import lightning.product.x_268_Y;
import lightning.product.y_1539_W;

public class h_4152_b
extends DirectionalBlock {
    public static final U_1266_O h_1847_R = BlockStateProperties.v_4262_N;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 12.0, 16.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(4.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 12.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 4.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 4.0, 0.0, 16.0, 16.0, 16.0);
    private final boolean Y_259_p;

    public h_4152_b(boolean sticky, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false));
        this.Y_259_p = sticky;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (state.R_4764_Y(h_1847_R).booleanValue()) {
            switch (state.R_4764_Y(P_4830_p)) {
                case n_1700_B: {
                    return Y_601_j;
                }
                default: {
                    return w_1457_N;
                }
                case R_4764_Y: {
                    return multiplayerClientSuggestionProvider;
                }
                case G_564_y: {
                    return t_1786_h;
                }
                case P_1922_E: {
                    return M_182_A;
                }
                case u_1723_Y: 
            }
            return Q_4569_t;
        }
        return x_268_Y.J_1907_R();
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        if (!worldIn.Y_259_p) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (!worldIn.Y_259_p) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R()) && !worldIn.Y_259_p && worldIn.getTileEntity(pos) == null) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.R_4764_Y().u_1723_Y())).n_1700_B(h_1847_R, false);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        boolean flag = this.n_1700_B(worldIn, pos, direction);
        if (flag && !state.R_4764_Y(h_1847_R).booleanValue()) {
            if (new M_1398_d(worldIn, pos, direction, true).n_1700_B()) {
                worldIn.n_1700_B(pos, this, 0, direction.R_4764_Y());
            }
        } else if (!flag && state.R_4764_Y(h_1847_R).booleanValue()) {
            N_295_T pistontileentity;
            i_2154_H tileentity;
            c_1514_x blockpos = pos.offset(direction, 2);
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            int i = 1;
            if (blockstate.n_1700_B(a_3742_W.O_2151_c) && blockstate.R_4764_Y(P_4830_p) == direction && (tileentity = worldIn.getTileEntity(blockpos)) instanceof N_295_T && (pistontileentity = (N_295_T)tileentity).v_4262_N() && (pistontileentity.n_1700_B(0.0f) < 0.5f || worldIn.X_933_l() == pistontileentity.h_1847_R() || ((e_3591_l)worldIn).P_1922_E())) {
                i = 2;
            }
            worldIn.n_1700_B(pos, this, i, direction.R_4764_Y());
        }
    }

    private boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, b_257_Y facing) {
        for (b_257_Y direction : b_257_Y.values()) {
            if (direction == facing || !worldIn.J_1907_R(pos.offset(direction), direction)) continue;
            return true;
        }
        if (worldIn.J_1907_R(pos, b_257_Y.n_1700_B)) {
            return true;
        }
        c_1514_x blockpos = pos.up();
        for (b_257_Y direction1 : b_257_Y.values()) {
            if (direction1 == b_257_Y.n_1700_B || !worldIn.J_1907_R(blockpos.offset(direction1), direction1)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, int id, int param) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        if (!worldIn.Y_259_p) {
            boolean flag = this.n_1700_B(worldIn, pos, direction);
            if (flag && (id == 1 || id == 2)) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, true), 2);
                return false;
            }
            if (!flag && id == 0) {
                return false;
            }
        }
        if (id == 0) {
            if (!this.n_1700_B(worldIn, pos, direction, true)) {
                return false;
            }
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, true), 67);
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.V_4557_X, D_38_f.P_1922_E, 0.5f, worldIn.w_1457_N.nextFloat() * 0.25f + 0.6f);
        } else if (id == 1 || id == 2) {
            i_2154_H tileentity1 = worldIn.getTileEntity(pos.offset(direction));
            if (tileentity1 instanceof N_295_T) {
                ((N_295_T)tileentity1).P_4830_p();
            }
            K_4074_S blockstate = (K_4074_S)((K_4074_S)a_3742_W.O_2151_c.multiplayerClientSuggestionProvider().n_1700_B(s_3401_U.P_4830_p, direction)).n_1700_B(s_3401_U.h_1847_R, this.Y_259_p ? y_1539_W.J_1907_R : y_1539_W.n_1700_B);
            worldIn.n_1700_B(pos, blockstate, 20);
            worldIn.n_1700_B(pos, s_3401_U.n_1700_B((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, b_257_Y.n_1700_B(param & 7)), direction, false, true));
            worldIn.n_1700_B(pos, blockstate.J_1907_R());
            blockstate.n_1700_B((LevelAccessor)worldIn, pos, 2);
            if (this.Y_259_p) {
                N_295_T pistontileentity;
                i_2154_H tileentity;
                c_1514_x blockpos = pos.add(direction.t_148_a() * 2, direction.s_956_w() * 2, direction.u_2550_I() * 2);
                K_4074_S blockstate1 = worldIn.getBlockState(blockpos);
                boolean flag1 = false;
                if (blockstate1.n_1700_B(a_3742_W.O_2151_c) && (tileentity = worldIn.getTileEntity(blockpos)) instanceof N_295_T && (pistontileentity = (N_295_T)tileentity).w_1484_f() == direction && pistontileentity.v_4262_N()) {
                    pistontileentity.P_4830_p();
                    flag1 = true;
                }
                if (!flag1) {
                    if (id != 1 || blockstate1.v_4262_N() || !h_4152_b.n_1700_B(blockstate1, worldIn, blockpos, direction.u_1723_Y(), false, direction) || blockstate1.u_2550_I() != w_1454_v.n_1700_B && !blockstate1.n_1700_B(a_3742_W.j_2266_I) && !blockstate1.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler)) {
                        worldIn.n_1700_B(pos.offset(direction), false);
                    } else {
                        this.n_1700_B(worldIn, pos, direction, false);
                    }
                }
            } else {
                worldIn.n_1700_B(pos.offset(direction), false);
            }
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.A_1603_w, D_38_f.P_1922_E, 0.5f, worldIn.w_1457_N.nextFloat() * 0.15f + 0.6f);
        }
        return true;
    }

    public static boolean n_1700_B(K_4074_S blockStateIn, b_4507_u worldIn, c_1514_x pos, b_257_Y facing, boolean destroyBlocks, b_257_Y direction) {
        if (pos.getY() >= 0 && pos.getY() <= worldIn.c_3005_b() - 1 && worldIn.H_2857_Y().n_1700_B(pos)) {
            if (blockStateIn.v_4262_N()) {
                return true;
            }
            if (!(blockStateIn.n_1700_B(a_3742_W.ClientBootstrap) || blockStateIn.n_1700_B(a_3742_W.MinMaxBounds) || blockStateIn.n_1700_B(a_3742_W.WrappedMinMaxBounds))) {
                if (facing == b_257_Y.n_1700_B && pos.getY() == 0) {
                    return false;
                }
                if (facing == b_257_Y.J_1907_R && pos.getY() == worldIn.c_3005_b() - 1) {
                    return false;
                }
                if (!blockStateIn.n_1700_B(a_3742_W.j_2266_I) && !blockStateIn.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler)) {
                    if (blockStateIn.w_1484_f(worldIn, pos) == -1.0f) {
                        return false;
                    }
                    switch (blockStateIn.u_2550_I()) {
                        case R_4764_Y: {
                            return false;
                        }
                        case J_1907_R: {
                            return destroyBlocks;
                        }
                        case P_1922_E: {
                            return facing == direction;
                        }
                    }
                } else if (blockStateIn.R_4764_Y(h_1847_R).booleanValue()) {
                    return false;
                }
                return !blockStateIn.J_1907_R().G_564_y();
            }
            return false;
        }
        return false;
    }

    private boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, b_257_Y directionIn, boolean extending) {
        M_1398_d pistonblockstructurehelper;
        c_1514_x blockpos = pos.offset(directionIn);
        if (!extending && worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.S_980_j)) {
            worldIn.n_1700_B(blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 20);
        }
        if (!(pistonblockstructurehelper = new M_1398_d(worldIn, pos, directionIn, extending)).n_1700_B()) {
            return false;
        }
        HashMap map = Maps.newHashMap();
        List<c_1514_x> list = pistonblockstructurehelper.J_1907_R();
        ArrayList list1 = Lists.newArrayList();
        for (int i = 0; i < list.size(); ++i) {
            c_1514_x blockpos1 = list.get(i);
            K_4074_S blockstate = worldIn.getBlockState(blockpos1);
            list1.add(blockstate);
            map.put(blockpos1, blockstate);
        }
        List<c_1514_x> list2 = pistonblockstructurehelper.R_4764_Y();
        K_4074_S[] ablockstate = new K_4074_S[list.size() + list2.size()];
        b_257_Y direction = extending ? directionIn : directionIn.u_1723_Y();
        int j = 0;
        for (int k = list2.size() - 1; k >= 0; --k) {
            c_1514_x blockpos2 = list2.get(k);
            K_4074_S k_4074_S = worldIn.getBlockState(blockpos2);
            i_2154_H tileentity = k_4074_S.J_1907_R().G_564_y() ? worldIn.getTileEntity(blockpos2) : null;
            h_4152_b.n_1700_B(k_4074_S, worldIn, blockpos2, tileentity);
            worldIn.n_1700_B(blockpos2, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 18);
            ablockstate[j++] = k_4074_S;
        }
        for (int l = list.size() - 1; l >= 0; --l) {
            c_1514_x blockpos3 = list.get(l);
            K_4074_S k_4074_S = worldIn.getBlockState(blockpos3);
            blockpos3 = blockpos3.offset(direction);
            map.remove(blockpos3);
            worldIn.n_1700_B(blockpos3, (K_4074_S)a_3742_W.O_2151_c.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, directionIn), 68);
            worldIn.n_1700_B(blockpos3, s_3401_U.n_1700_B((K_4074_S)list1.get(l), directionIn, extending, false));
            ablockstate[j++] = k_4074_S;
        }
        if (extending) {
            y_1539_W pistontype = this.Y_259_p ? y_1539_W.J_1907_R : y_1539_W.n_1700_B;
            K_4074_S blockstate4 = (K_4074_S)((K_4074_S)a_3742_W.S_980_j.multiplayerClientSuggestionProvider().n_1700_B(N_81_X.P_4830_p, directionIn)).n_1700_B(N_81_X.h_1847_R, pistontype);
            K_4074_S k_4074_S = (K_4074_S)((K_4074_S)a_3742_W.O_2151_c.multiplayerClientSuggestionProvider().n_1700_B(s_3401_U.P_4830_p, directionIn)).n_1700_B(s_3401_U.h_1847_R, this.Y_259_p ? y_1539_W.J_1907_R : y_1539_W.n_1700_B);
            map.remove(blockpos);
            worldIn.n_1700_B(blockpos, k_4074_S, 68);
            worldIn.n_1700_B(blockpos, s_3401_U.n_1700_B(blockstate4, directionIn, true, true));
        }
        K_4074_S blockstate3 = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        for (c_1514_x c_1514_x2 : map.keySet()) {
            worldIn.n_1700_B(c_1514_x2, blockstate3, 82);
        }
        for (Map.Entry entry : map.entrySet()) {
            c_1514_x blockpos5 = (c_1514_x)entry.getKey();
            K_4074_S blockstate2 = (K_4074_S)entry.getValue();
            blockstate2.J_1907_R(worldIn, blockpos5, 2);
            blockstate3.n_1700_B((LevelAccessor)worldIn, blockpos5, 2);
            blockstate3.J_1907_R(worldIn, blockpos5, 2);
        }
        j = 0;
        for (int i1 = list2.size() - 1; i1 >= 0; --i1) {
            K_4074_S k_4074_S = ablockstate[j++];
            c_1514_x blockpos6 = list2.get(i1);
            k_4074_S.J_1907_R(worldIn, blockpos6, 2);
            worldIn.J_1907_R(blockpos6, k_4074_S.J_1907_R());
        }
        for (int j1 = list.size() - 1; j1 >= 0; --j1) {
            worldIn.J_1907_R(list.get(j1), ablockstate[j++].J_1907_R());
        }
        if (extending) {
            worldIn.J_1907_R(blockpos, a_3742_W.S_980_j);
        }
        return true;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return state.R_4764_Y(h_1847_R);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}



