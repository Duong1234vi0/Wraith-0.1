/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.PipeBlock;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.j_3341_s;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3546_P;
import lightning.product.x_268_Y;

public class v_4620_e
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = PipeBlock.P_4830_p;
    public static final U_1266_O h_1847_R = PipeBlock.h_1847_R;
    public static final U_1266_O Q_4569_t = PipeBlock.Q_4569_t;
    public static final U_1266_O M_182_A = PipeBlock.M_182_A;
    public static final U_1266_O t_1786_h = BlockStateProperties.A_4115_X;
    protected static final Map<b_257_Y, U_1266_O> multiplayerClientSuggestionProvider = PipeBlock.w_1457_N.entrySet().stream().filter(facingProperty -> ((b_257_Y)facingProperty.getKey()).h_1847_R().G_564_y()).collect(j_3341_s.n_1700_B());
    protected final s_1395_c[] w_1457_N;
    protected final s_1395_c[] Y_601_j;
    private final Object2IntMap<K_4074_S> Y_259_p = new Object2IntOpenHashMap();

    protected v_4620_e(float nodeWidth, float extensionWidth, float nodeHeight, float extensionHeight, float collisionY, q_4293_E.P_1922_E properties) {
        super(properties);
        this.w_1457_N = this.n_1700_B(nodeWidth, extensionWidth, collisionY, 0.0f, collisionY);
        this.Y_601_j = this.n_1700_B(nodeWidth, extensionWidth, nodeHeight, 0.0f, extensionHeight);
        for (K_4074_S blockstate : this.x_607_J.n_1700_B()) {
            this.w_1484_f(blockstate);
        }
    }

    protected s_1395_c[] n_1700_B(float nodeWidth, float extensionWidth, float nodeHeight, float extensionBottom, float extensionHeight) {
        float f = 8.0f - nodeWidth;
        float f1 = 8.0f + nodeWidth;
        float f2 = 8.0f - extensionWidth;
        float f3 = 8.0f + extensionWidth;
        s_1395_c voxelshape = T_2915_h.n_1700_B(f, 0.0, f, f1, nodeHeight, f1);
        s_1395_c voxelshape1 = T_2915_h.n_1700_B(f2, extensionBottom, 0.0, f3, extensionHeight, f3);
        s_1395_c voxelshape2 = T_2915_h.n_1700_B(f2, extensionBottom, f2, f3, extensionHeight, 16.0);
        s_1395_c voxelshape3 = T_2915_h.n_1700_B(0.0, extensionBottom, f2, f3, extensionHeight, f3);
        s_1395_c voxelshape4 = T_2915_h.n_1700_B(f2, extensionBottom, f2, 16.0, extensionHeight, f3);
        s_1395_c voxelshape5 = x_268_Y.n_1700_B(voxelshape1, voxelshape4);
        s_1395_c voxelshape6 = x_268_Y.n_1700_B(voxelshape2, voxelshape3);
        s_1395_c[] avoxelshape = new s_1395_c[]{x_268_Y.n_1700_B(), voxelshape2, voxelshape3, voxelshape6, voxelshape1, x_268_Y.n_1700_B(voxelshape2, voxelshape1), x_268_Y.n_1700_B(voxelshape3, voxelshape1), x_268_Y.n_1700_B(voxelshape6, voxelshape1), voxelshape4, x_268_Y.n_1700_B(voxelshape2, voxelshape4), x_268_Y.n_1700_B(voxelshape3, voxelshape4), x_268_Y.n_1700_B(voxelshape6, voxelshape4), voxelshape5, x_268_Y.n_1700_B(voxelshape2, voxelshape5), x_268_Y.n_1700_B(voxelshape3, voxelshape5), x_268_Y.n_1700_B(voxelshape6, voxelshape5)};
        for (int i = 0; i < 16; ++i) {
            avoxelshape[i] = x_268_Y.n_1700_B(voxelshape, avoxelshape[i]);
        }
        return avoxelshape;
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return state.R_4764_Y(t_1786_h) == false;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Y_601_j[this.w_1484_f(state)];
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.w_1457_N[this.w_1484_f(state)];
    }

    private static int n_1700_B(b_257_Y facing) {
        return 1 << facing.G_564_y();
    }

    protected int w_1484_f(K_4074_S state) {
        return this.Y_259_p.computeIntIfAbsent((Object)state, stateIn -> {
            int i = 0;
            if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
                i |= v_4620_e.n_1700_B(b_257_Y.R_4764_Y);
            }
            if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
                i |= v_4620_e.n_1700_B(b_257_Y.u_1723_Y);
            }
            if (stateIn.R_4764_Y(Q_4569_t).booleanValue()) {
                i |= v_4620_e.n_1700_B(b_257_Y.G_564_y);
            }
            if (stateIn.R_4764_Y(M_182_A).booleanValue()) {
                i |= v_4620_e.n_1700_B(b_257_Y.P_1922_E);
            }
            return i;
        });
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(t_1786_h) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(Q_4569_t))).n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(Q_4569_t, state.R_4764_Y(P_4830_p))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R));
            }
            case G_564_y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(h_1847_R))).n_1700_B(h_1847_R, state.R_4764_Y(Q_4569_t))).n_1700_B(Q_4569_t, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(P_4830_p));
            }
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(M_182_A))).n_1700_B(h_1847_R, state.R_4764_Y(P_4830_p))).n_1700_B(Q_4569_t, state.R_4764_Y(h_1847_R))).n_1700_B(M_182_A, state.R_4764_Y(Q_4569_t));
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        switch (mirrorIn) {
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(Q_4569_t))).n_1700_B(Q_4569_t, state.R_4764_Y(P_4830_p));
            }
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, state.R_4764_Y(M_182_A))).n_1700_B(M_182_A, state.R_4764_Y(h_1847_R));
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }
}


