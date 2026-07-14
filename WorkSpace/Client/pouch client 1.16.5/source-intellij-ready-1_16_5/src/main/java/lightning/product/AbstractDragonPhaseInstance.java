/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.V_3354_l;
import lightning.product.a_3913_L;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.DragonPhaseInstance;
import lightning.product.u_530_F;

public abstract class AbstractDragonPhaseInstance
implements DragonPhaseInstance {
    protected final b_2971_b n_1700_B;

    public AbstractDragonPhaseInstance(b_2971_b dragonIn) {
        this.n_1700_B = dragonIn;
    }

    @Override
    public boolean u_() {
        return false;
    }

    @Override
    public void n_1700_B() {
    }

    @Override
    public void J_1907_R() {
    }

    @Override
    public void n_1700_B(V_3354_l crystal, c_1514_x pos, P_11_z dmgSrc, @Nullable a_3913_L plyr) {
    }

    @Override
    public void R_4764_Y() {
    }

    @Override
    public void v_4262_N() {
    }

    @Override
    public float P_1922_E() {
        return 0.6f;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return null;
    }

    @Override
    public float n_1700_B(P_11_z p_221113_1_, float p_221113_2_) {
        return p_221113_2_;
    }

    @Override
    public float t_148_a() {
        float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(this.n_1700_B.I_4348_c())) + 1.0f;
        float f1 = Math.min(f, 40.0f);
        return 0.7f / f1 / f;
    }
}


