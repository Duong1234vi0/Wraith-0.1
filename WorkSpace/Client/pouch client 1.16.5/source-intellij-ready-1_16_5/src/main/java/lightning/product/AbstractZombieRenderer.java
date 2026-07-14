/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4315_z;
import lightning.product.F_4355_q;
import lightning.product.HumanoidMobRenderer;
import lightning.product.g_2336_b;
import lightning.product.o_4662_o;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;

public abstract class AbstractZombieRenderer<T extends F_4355_q, M extends o_4662_o<T>>
extends HumanoidMobRenderer<T, M> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie/zombie.png");

    protected AbstractZombieRenderer(w_2040_b p_i50974_1_, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_) {
        super(p_i50974_1_, p_i50974_2_, 0.5f);
        this.n_1700_B(new B_4315_z(this, p_i50974_3_, p_i50974_4_));
    }

    @Override
    public g_2336_b n_1700_B(F_4355_q entity) {
        return n_1700_B;
    }

    @Override
    protected boolean J_1907_R(T p_230495_1_) {
        return ((F_4355_q)p_230495_1_).P_2295_B();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(r_4811_B r_4811_B2) {
        return this.J_1907_R((T)((F_4355_q)r_4811_B2));
    }
}


