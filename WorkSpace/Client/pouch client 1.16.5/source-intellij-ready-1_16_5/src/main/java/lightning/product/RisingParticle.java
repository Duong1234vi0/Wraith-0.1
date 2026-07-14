/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.TextureSheetParticle;
import lightning.product.b_4507_u;

public abstract class RisingParticle
extends TextureSheetParticle {
    protected RisingParticle(b_4507_u world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.s_956_w = this.s_956_w * (double)0.01f + motionX;
        this.u_2550_I = this.u_2550_I * (double)0.01f + motionY;
        this.M_588_G = this.M_588_G * (double)0.01f + motionZ;
        this.v_4262_N += (double)((this.multiplayerClientSuggestionProvider.nextFloat() - this.multiplayerClientSuggestionProvider.nextFloat()) * 0.05f);
        this.w_1484_f += (double)((this.multiplayerClientSuggestionProvider.nextFloat() - this.multiplayerClientSuggestionProvider.nextFloat()) * 0.05f);
        this.t_148_a += (double)((this.multiplayerClientSuggestionProvider.nextFloat() - this.multiplayerClientSuggestionProvider.nextFloat()) * 0.05f);
        this.Y_601_j = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = this.v_4262_N;
        this.P_1922_E = this.w_1484_f;
        this.u_1723_Y = this.t_148_a;
        if (this.w_1457_N++ >= this.Y_601_j) {
            this.s_956_w();
        } else {
            this.n_1700_B(this.s_956_w, this.u_2550_I, this.M_588_G);
            this.s_956_w *= (double)0.96f;
            this.u_2550_I *= (double)0.96f;
            this.M_588_G *= (double)0.96f;
            if (this.P_4830_p) {
                this.s_956_w *= (double)0.7f;
                this.M_588_G *= (double)0.7f;
            }
        }
    }
}


