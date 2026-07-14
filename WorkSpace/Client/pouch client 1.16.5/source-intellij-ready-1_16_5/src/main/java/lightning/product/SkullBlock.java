/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.AbstractSkullBlock;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.c_1514_x;
import lightning.product.g_88_D;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;

public class SkullBlock
extends AbstractSkullBlock {
    public static final g_88_D P_4830_p = BlockStateProperties.j_1564_a;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    protected SkullBlock(n_1700_B type, q_4293_E.P_1922_E properties) {
        super(type, properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, u_530_F.R_4764_Y((double)(context.getPlacementYaw() * 16.0f / 360.0f) + 0.5) & 0xF);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p), 16));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)state.n_1700_B(P_4830_p, mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p), 16));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    public static interface n_1700_B {
    }

    public static final class J_1907_R
    extends Enum<J_1907_R>
    implements n_1700_B {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R();
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R();
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            v_4262_N = lightning.product.SkullBlock$J_1907_R.n_1700_B();
        }
    }
}


