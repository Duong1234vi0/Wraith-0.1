/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MutableComponent;
import lightning.product.L_3144_D;
import lightning.product.v_143_j;
import lightning.product.x_282_a;

public class U_2871_b
extends L_3144_D {
    public static final x_282_a R_4764_Y = new U_2871_b("");
    private final String G_564_y;

    public U_2871_b(String msg) {
        this.G_564_y = v_143_j.n_1700_B(msg);
    }

    public String v_4262_N() {
        return this.G_564_y;
    }

    @Override
    public String J_1907_R() {
        return this.G_564_y;
    }

    public U_2871_b w_1484_f() {
        return new U_2871_b(this.G_564_y);
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof U_2871_b)) {
            return false;
        }
        U_2871_b stringtextcomponent = (U_2871_b)p_equals_1_;
        return this.G_564_y.equals(stringtextcomponent.v_4262_N()) && super.equals(p_equals_1_);
    }

    @Override
    public String toString() {
        return "TextComponent{text='" + this.G_564_y + "', siblings=" + String.valueOf(this.u_1723_Y) + ", style=" + String.valueOf(this.n_1700_B()) + "}";
    }

    @Override
    public /* synthetic */ L_3144_D t_148_a() {
        return this.w_1484_f();
    }

    @Override
    public /* synthetic */ MutableComponent G_564_y() {
        return this.w_1484_f();
    }
}


