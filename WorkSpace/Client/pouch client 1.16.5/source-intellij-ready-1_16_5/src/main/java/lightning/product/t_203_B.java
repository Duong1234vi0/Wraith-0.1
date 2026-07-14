/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.O_4844_u;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class t_203_B
extends O_4844_u {
    private final D_38_f J_1907_R;

    public t_203_B(MinecraftClient settings, int x, int y, D_38_f category, int width) {
        super(settings.P_4830_p, x, y, width, 20, (double)settings.P_4830_p.n_1700_B(category));
        this.J_1907_R = category;
        this.func_230979_b_();
    }

    @Override
    protected void func_230979_b_() {
        x_282_a itextcomponent = (float)this.sliderValue == (float)this.getYImage(false) ? CommonComponents.J_1907_R : new U_2871_b((int)(this.sliderValue * 100.0) + "%");
        this.setMessage(new F_2904_S("soundCategory." + this.J_1907_R.n_1700_B()).n_1700_B(": ").n_1700_B(itextcomponent));
    }

    @Override
    protected void func_230972_a_() {
        this.n_1700_B.n_1700_B(this.J_1907_R, (float)this.sliderValue);
        this.n_1700_B.J_1907_R();
    }
}



