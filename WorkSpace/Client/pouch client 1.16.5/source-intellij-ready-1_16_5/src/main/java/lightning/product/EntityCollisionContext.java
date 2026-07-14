/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.N_4263_v;
import lightning.product.U_4243_e;
import lightning.product.CollisionContext;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.Fluid;

public class EntityCollisionContext
implements CollisionContext {
    protected static final CollisionContext n_1700_B = new EntityCollisionContext(false, -1.7976931348623157E308, Items.n_1700_B, fluid -> false){

        @Override
        public boolean n_1700_B(s_1395_c shape, c_1514_x pos, boolean p_216378_3_) {
            return p_216378_3_;
        }
    };
    private final boolean J_1907_R;
    private final double R_4764_Y;
    private final q_1613_l G_564_y;
    private final Predicate<Fluid> P_1922_E;

    protected EntityCollisionContext(boolean sneaking, double posY, q_1613_l item, Predicate<Fluid> fluidPredicate) {
        this.J_1907_R = sneaking;
        this.R_4764_Y = posY;
        this.G_564_y = item;
        this.P_1922_E = fluidPredicate;
    }

    @Deprecated
    protected EntityCollisionContext(N_4263_v entityIn) {
        this(entityIn.ClientBootstrap(), entityIn.X_2960_b(), entityIn instanceof r_4811_B ? ((r_4811_B)entityIn).A_2714_y().J_1907_R() : Items.n_1700_B, entityIn instanceof r_4811_B ? ((r_4811_B)entityIn)::n_1700_B : fluid -> false);
    }

    @Override
    public boolean n_1700_B(q_1613_l itemIn) {
        return this.G_564_y == itemIn;
    }

    @Override
    public boolean n_1700_B(FluidState p_230426_1_, U_4243_e p_230426_2_) {
        return this.P_1922_E.test(p_230426_2_) && !p_230426_1_.n_1700_B().n_1700_B(p_230426_2_);
    }

    @Override
    public boolean n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public boolean n_1700_B(s_1395_c shape, c_1514_x pos, boolean p_216378_3_) {
        return this.R_4764_Y > (double)pos.getY() + shape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R) - (double)1.0E-5f;
    }
}



