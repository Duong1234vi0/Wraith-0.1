/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4315_z;
import lightning.product.ZombieVillagerModel;
import lightning.product.ReloadableResourceManager;
import lightning.product.X_3615_B;
import lightning.product.HumanoidMobRenderer;
import lightning.product.g_2336_b;
import lightning.product.l_4140_i;
import lightning.product.r_4811_B;
import lightning.product.w_2040_b;

public class ZombieVillagerRenderer
extends HumanoidMobRenderer<l_4140_i, ZombieVillagerModel<l_4140_i>> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/zombie_villager/zombie_villager.png");

    public ZombieVillagerRenderer(w_2040_b renderManagerIn, ReloadableResourceManager resourceManagerIn) {
        super(renderManagerIn, new ZombieVillagerModel(0.0f, false), 0.5f);
        this.n_1700_B(new B_4315_z(this, new ZombieVillagerModel(0.5f, true), new ZombieVillagerModel(1.0f, true)));
        this.n_1700_B(new X_3615_B<l_4140_i, ZombieVillagerModel<l_4140_i>>(this, resourceManagerIn, "zombie_villager"));
    }

    @Override
    public g_2336_b n_1700_B(l_4140_i entity) {
        return n_1700_B;
    }

    @Override
    protected boolean J_1907_R(l_4140_i p_230495_1_) {
        return p_230495_1_.U_3758_B();
    }

    @Override
    protected /* synthetic */ boolean n_1700_B(r_4811_B r_4811_B2) {
        return this.J_1907_R((l_4140_i)r_4811_B2);
    }
}


