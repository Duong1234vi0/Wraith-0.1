/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.V_537_k;
import lightning.product.b_2037_V;
import lightning.product.g_221_o;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;

public class t_4433_T
extends N_4006_T {
    private final b_2037_V n_1700_B;

    public t_4433_T(b_2037_V setting) {
        this.n_1700_B = setting;
    }

    @Override
    public void n_1700_B(g_221_o matrixStack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(matrixStack, mouseX, mouseY, alpha);
        String displayName = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        float xPos = this.u_1723_Y() + (this.w_1484_f() - l_3370_o.G_564_y[17].n_1700_B(displayName)) / 2.0f;
        l_3370_o.G_564_y[17].n_1700_B(matrixStack, displayName, (double)xPos, (double)(this.v_4262_N() + 1.0f), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        this.G_564_y(11.0f + l_3370_o.R_4764_Y[14].h_1847_R());
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }
}

