/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;

public class PipeBlock
extends T_2915_h {
    private static final b_257_Y[] Y_259_p = b_257_Y.values();
    public static final U_1266_O P_4830_p = BlockStateProperties.d_2427_y;
    public static final U_1266_O h_1847_R = BlockStateProperties.z_1737_N;
    public static final U_1266_O Q_4569_t = BlockStateProperties.v_4276_D;
    public static final U_1266_O M_182_A = BlockStateProperties.d_2461_k;
    public static final U_1266_O t_1786_h = BlockStateProperties.e_4240_b;
    public static final U_1266_O multiplayerClientSuggestionProvider = BlockStateProperties.n_3318_d;
    public static final Map<b_257_Y, U_1266_O> w_1457_N = j_3341_s.n_1700_B(Maps.newEnumMap(b_257_Y.class), (T directions) -> {
        directions.put(b_257_Y.R_4764_Y, P_4830_p);
        directions.put(b_257_Y.u_1723_Y, h_1847_R);
        directions.put(b_257_Y.G_564_y, Q_4569_t);
        directions.put(b_257_Y.P_1922_E, M_182_A);
        directions.put(b_257_Y.J_1907_R, t_1786_h);
        directions.put(b_257_Y.n_1700_B, multiplayerClientSuggestionProvider);
    });
    protected final s_1395_c[] Y_601_j;

    protected PipeBlock(float apothem, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Y_601_j = this.n_1700_B(apothem);
    }

    private s_1395_c[] n_1700_B(float apothem) {
        float f = 0.5f - apothem;
        float f1 = 0.5f + apothem;
        s_1395_c voxelshape = T_2915_h.n_1700_B(f * 16.0f, f * 16.0f, f * 16.0f, f1 * 16.0f, f1 * 16.0f, f1 * 16.0f);
        s_1395_c[] avoxelshape = new s_1395_c[Y_259_p.length];
        for (int i = 0; i < Y_259_p.length; ++i) {
            b_257_Y direction = Y_259_p[i];
            avoxelshape[i] = x_268_Y.n_1700_B(0.5 + Math.min((double)(-apothem), (double)direction.t_148_a() * 0.5), 0.5 + Math.min((double)(-apothem), (double)direction.s_956_w() * 0.5), 0.5 + Math.min((double)(-apothem), (double)direction.u_2550_I() * 0.5), 0.5 + Math.max((double)apothem, (double)direction.t_148_a() * 0.5), 0.5 + Math.max((double)apothem, (double)direction.s_956_w() * 0.5), 0.5 + Math.max((double)apothem, (double)direction.u_2550_I() * 0.5));
        }
        s_1395_c[] avoxelshape1 = new s_1395_c[64];
        for (int k = 0; k < 64; ++k) {
            s_1395_c voxelshape1 = voxelshape;
            for (int j = 0; j < Y_259_p.length; ++j) {
                if ((k & 1 << j) == 0) continue;
                voxelshape1 = x_268_Y.n_1700_B(voxelshape1, avoxelshape[j]);
            }
            avoxelshape1[k] = voxelshape1;
        }
        return avoxelshape1;
    }

    @Override
    public boolean a_(K_4074_S state, BlockGetter reader, c_1514_x pos) {
        return false;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.Y_601_j[this.w_1484_f(state)];
    }

    protected int w_1484_f(K_4074_S state) {
        int i = 0;
        for (int j = 0; j < Y_259_p.length; ++j) {
            if (!((Boolean)state.R_4764_Y(w_1457_N.get(Y_259_p[j]))).booleanValue()) continue;
            i |= 1 << j;
        }
        return i;
    }
}


