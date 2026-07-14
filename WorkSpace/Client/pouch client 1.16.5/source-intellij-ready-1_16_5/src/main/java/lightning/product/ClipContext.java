/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.CollisionContext;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.x_268_Y;

public class ClipContext {
    private final e_2866_D n_1700_B;
    private final e_2866_D J_1907_R;
    private final n_1700_B R_4764_Y;
    private final J_1907_R G_564_y;
    private final CollisionContext P_1922_E;

    public ClipContext(e_2866_D startVecIn, e_2866_D endVecIn, n_1700_B blockModeIn, J_1907_R fluidModeIn, N_4263_v entityIn) {
        this.n_1700_B = startVecIn;
        this.J_1907_R = endVecIn;
        this.R_4764_Y = blockModeIn;
        this.G_564_y = fluidModeIn;
        this.P_1922_E = CollisionContext.n_1700_B(entityIn);
    }

    public e_2866_D n_1700_B() {
        return this.J_1907_R;
    }

    public e_2866_D J_1907_R() {
        return this.n_1700_B;
    }

    public s_1395_c n_1700_B(K_4074_S blockState, BlockGetter world, c_1514_x pos) {
        return this.R_4764_Y.get(blockState, world, pos, this.P_1922_E);
    }

    public s_1395_c n_1700_B(FluidState state, BlockGetter world, c_1514_x pos) {
        return this.G_564_y.n_1700_B(state) ? state.G_564_y(world, pos) : x_268_Y.n_1700_B();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B>
    implements R_4764_Y {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(q_4293_E.n_1700_B::R_4764_Y);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(q_4293_E.n_1700_B::n_1700_B);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(q_4293_E.n_1700_B::J_1907_R);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(q_4293_E.n_1700_B::G_564_y);
        private final R_4764_Y P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(R_4764_Y providerIn) {
            this.P_1922_E = providerIn;
        }

        @Override
        public s_1395_c get(K_4074_S p_get_1_, BlockGetter p_get_2_, c_1514_x p_get_3_, CollisionContext p_get_4_) {
            return this.P_1922_E.get(p_get_1_, p_get_2_, p_get_3_, p_get_4_);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            u_1723_Y = lightning.product.ClipContext$n_1700_B.n_1700_B();
        }
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(fluidState -> false);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(FluidState::J_1907_R);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(fluidState -> !fluidState.R_4764_Y());
        private final Predicate<FluidState> G_564_y;
        private static final /* synthetic */ J_1907_R[] P_1922_E;

        public static J_1907_R[] values() {
            return (J_1907_R[])P_1922_E.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(Predicate<FluidState> fluidTestIn) {
            this.G_564_y = fluidTestIn;
        }

        public boolean n_1700_B(FluidState state) {
            return this.G_564_y.test(state);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.ClipContext$J_1907_R.n_1700_B();
        }
    }

    public static interface R_4764_Y {
        public s_1395_c get(K_4074_S var1, BlockGetter var2, c_1514_x var3, CollisionContext var4);
    }
}


