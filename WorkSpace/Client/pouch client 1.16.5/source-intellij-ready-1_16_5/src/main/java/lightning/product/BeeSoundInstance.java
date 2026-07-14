/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.N_4263_v;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundEvent;
import lightning.product.b_1913_J;
import lightning.product.MinecraftClient;
import lightning.product.u_530_F;

public abstract class BeeSoundInstance
extends AbstractTickableSoundInstance {
    protected final b_1913_J n_1700_B;
    private boolean Q_4569_t;

    public BeeSoundInstance(b_1913_J entity, SoundEvent event, D_38_f category) {
        super(event, category);
        this.n_1700_B = entity;
        this.v_4262_N = (float)entity.O_3598_v();
        this.w_1484_f = (float)entity.X_2960_b();
        this.t_148_a = (float)entity.l_2647_k();
        this.s_956_w = true;
        this.u_2550_I = 0;
        this.P_1922_E = 0.0f;
    }

    @Override
    public void R_4764_Y() {
        boolean flag = this.J_1907_R();
        if (flag && !this.multiplayerClientSuggestionProvider()) {
            MinecraftClient.A_4115_X().Z_976_R().n_1700_B(this.n_1700_B());
            this.Q_4569_t = true;
        }
        if (!this.n_1700_B.t_4219_U && !this.Q_4569_t) {
            this.v_4262_N = (float)this.n_1700_B.O_3598_v();
            this.w_1484_f = (float)this.n_1700_B.X_2960_b();
            this.t_148_a = (float)this.n_1700_B.l_2647_k();
            float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(this.n_1700_B.I_4348_c()));
            if ((double)f >= 0.01) {
                this.u_1723_Y = u_530_F.v_4262_N(u_530_F.n_1700_B(f, this.Y_601_j(), this.Y_259_p()), this.Y_601_j(), this.Y_259_p());
                this.P_1922_E = u_530_F.v_4262_N(u_530_F.n_1700_B(f, 0.0f, 0.5f), 0.0f, 1.2f);
            } else {
                this.u_1723_Y = 0.0f;
                this.P_1922_E = 0.0f;
            }
        } else {
            this.w_1457_N();
        }
    }

    private float Y_601_j() {
        return this.n_1700_B.d_() ? 1.1f : 0.7f;
    }

    private float Y_259_p() {
        return this.n_1700_B.d_() ? 1.5f : 1.1f;
    }

    @Override
    public boolean G_564_y() {
        return true;
    }

    @Override
    public boolean P_1922_E() {
        return !this.n_1700_B.y_1700_S();
    }

    protected abstract AbstractTickableSoundInstance n_1700_B();

    protected abstract boolean J_1907_R();
}



