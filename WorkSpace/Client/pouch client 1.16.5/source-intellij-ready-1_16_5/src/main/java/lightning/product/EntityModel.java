/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Function;
import lightning.product.N_4263_v;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;

public abstract class EntityModel<T extends N_4263_v>
extends v_3569_v {
    public float h_1847_R;
    public boolean Q_4569_t;
    public boolean M_182_A = true;

    protected EntityModel() {
        this(o_2576_A::G_564_y);
    }

    protected EntityModel(Function<g_2336_b, o_2576_A> p_i225945_1_) {
        super(p_i225945_1_);
    }

    public abstract void n_1700_B(T var1, float var2, float var3, float var4, float var5, float var6);

    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
    }

    public void n_1700_B(EntityModel<T> p_217111_1_) {
        p_217111_1_.h_1847_R = this.h_1847_R;
        p_217111_1_.Q_4569_t = this.Q_4569_t;
        p_217111_1_.M_182_A = this.M_182_A;
    }
}


